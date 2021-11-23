package com.tolo.springaws.profile;

import com.tolo.springaws.bucket.BucketName;
import com.tolo.springaws.filestore.AmazonFileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class ProfileService {


    private final ProfileRepository profileRepository;
    private final AmazonFileStore amazonFileStore;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, AmazonFileStore amazonFileStore) {
        this.profileRepository = profileRepository;
        this.amazonFileStore = amazonFileStore;
    }

//    List<Profile> getProfiles() {
//        return profileRepository.getProfiles();
//    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Profile getById(UUID id){
        return profileRepository.getById(id);
    }

    public void save(Profile profile){
        profileRepository.save(profile);
    }


    public void uploadImage(UUID profileId, MultipartFile file) {
        // Check if empty
        extracted(file);

        // if file is image
        extracted1(file);

        // User exists in DB
        Profile profile = getProfileOrThrow(profileId);
        // grab metadata from file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));

        // Store in AWS s3, update database with s3 link
        String path = String.format("%s/%s", BucketName.PROFILE.getBucketName(), profile.getProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            amazonFileStore.save(path, filename, file.getInputStream(),Optional.of(metadata));
            profile.setProfileAvatar(filename);
            save(profile);


        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void extracted1(MultipartFile file) {
        if(!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()
        ).contains(file.getContentType())) {
            throw  new IllegalStateException("File must be images [ " + file.getContentType()+ " ]");
        }
    }

    private void extracted(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize()+" ]");
        }
    }

    private Profile getProfileOrThrow(UUID profileId) {
        return findAll()
                .stream()
                .filter(profile -> profile.getProfileId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile $s not found", profileId)));
    }

    public byte[] downloadImage(UUID userProfileId) {

        Profile user = getProfileOrThrow(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE.getBucketName(),
                user.getProfileId());


        return user.getProfileAvatar()
                .map(key -> amazonFileStore.download(path, key))
                .orElse(new byte[0]);
    }
}

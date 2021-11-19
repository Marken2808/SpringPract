package com.tolo.springaws.profile;

import com.tolo.springaws.bucket.BucketName;
import com.tolo.springaws.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final FileStore fileStore;
    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, FileStore fileStore) {
        this.userProfileRepository = userProfileRepository;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileRepository.getUserProfiles();
    }

    public void uploadImage(UUID userProfileId, MultipartFile file) {
        // Check if empty
        extracted(file);

        // if file is image
        extracted1(file);

        // User exists in DB
        UserProfile user = getUserProfileOrThrow(userProfileId);
        // grab metadata from file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));

        // Store in AWS s3, update database with s3 link
        String path = String.format("%s/%s", BucketName.PROFILE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, file.getInputStream(),Optional.of(metadata));
            user.setUserProfileImgUrl(filename);
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

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileRepository
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile $s not found", userProfileId)));
    }

    public byte[] downloadImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE.getBucketName(),
                user.getUserProfileId());

        return user.getUserProfileImgUrl()
                .map(key -> fileStore.dowload(path, key))
                .orElse(new byte[0]);

    }
}

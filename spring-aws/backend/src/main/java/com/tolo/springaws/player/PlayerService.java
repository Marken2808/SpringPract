package com.tolo.springaws.player;

import com.tolo.springaws.service.bucket.BucketName;
import com.tolo.springaws.service.filestore.AmazonFileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class PlayerService {


    private final PlayerRepository playerRepository;
    private final AmazonFileStore amazonFileStore;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, AmazonFileStore amazonFileStore) {
        this.playerRepository = playerRepository;
        this.amazonFileStore = amazonFileStore;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player getById(UUID id){
        return playerRepository.getById(id);
    }

    public void save(Player player){
        playerRepository.save(player);
    }


    public void uploadImage(UUID playerId, MultipartFile file) {
        // Check if empty
        extracted(file);

        // if file is image
        extracted1(file);

        // User exists in DB
        Player player = getPlayerOrThrow(playerId);
        // grab metadata from file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));

        // Store in AWS s3, update database with s3 link
        String path = String.format("%s/%s", BucketName.FOOTBALL.getBucketName(), player.getPlayerId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            amazonFileStore.save(path, filename, file.getInputStream(),Optional.of(metadata));
            player.setPlayerAvatar(filename);
            save(player);


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

    private Player getPlayerOrThrow(UUID playerId) {
        return findAll()
                .stream()
                .filter(player -> player.getPlayerId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User player $s not found", playerId)));
    }

    public byte[] downloadImage(UUID playerId) {

        Player user = getPlayerOrThrow(playerId);
        String path = String.format("%s/%s",
                BucketName.FOOTBALL.getBucketName(),
                user.getPlayerId());


        return user.getPlayerAvatar()
                .map(key -> amazonFileStore.download(path, key))
                .orElse(new byte[0]);
    }
}

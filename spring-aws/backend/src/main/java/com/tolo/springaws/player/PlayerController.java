package com.tolo.springaws.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/players")
@CrossOrigin("*")
public class PlayerController {


    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.findAll();
    }

    @GetMapping("{playerId}")
    public Player getPlayerByName(@PathVariable("playerId") UUID playerId){
        return playerService.getById(playerId);
    }

    @PostMapping(
            path = "{playerId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImage(@PathVariable("playerId") UUID playerId,
                            @RequestParam("file")MultipartFile file) {

        playerService.uploadImage(playerId, file);
    }

    @GetMapping("{playerId}/image/download")
    public byte[] downloadImage(@PathVariable("playerId") UUID playerId) {
        return playerService.downloadImage(playerId);
    }
}

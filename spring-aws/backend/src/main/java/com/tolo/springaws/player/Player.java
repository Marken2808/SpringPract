package com.tolo.springaws.player;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "player_uid")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_avatar")
    private String playerAvatar;

    public Player() {}

    public Player(UUID playerId, String playerName, String playerAvatar) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerAvatar = playerAvatar;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Optional<String> getPlayerAvatar() {
        return Optional.ofNullable(playerAvatar);
    }

    public void setPlayerAvatar(String playerAvatar) {
        this.playerAvatar = playerAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId) && playerName.equals(player.playerName) && playerAvatar.equals(player.playerAvatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, playerName, playerAvatar);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", playerAvatar='" + playerAvatar + '\'' +
                '}';
    }
}

package com.tolo.springaws.profile;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "profile_id")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID profileId;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "profile_avatar")
    private String profileAvatar;

    public Profile() {}

    public Profile(UUID profileId, String profileName, String profileAvatar) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.profileAvatar = profileAvatar;
    }



    public UUID getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public Optional<String> getProfileAvatar() {
        return Optional.ofNullable(profileAvatar);
    }

    public void setProfileAvatar(String profileAvatar) {
        this.profileAvatar = profileAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile that = (Profile) o;
        return Objects.equals(profileId, that.profileId) &&
                Objects.equals(profileName, that.profileName) &&
                Objects.equals(profileAvatar, that.profileAvatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, profileName, profileAvatar);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", profileName='" + profileName + '\'' +
                ", profileAvatar='" + profileAvatar + '\'' +
                '}';
    }
}

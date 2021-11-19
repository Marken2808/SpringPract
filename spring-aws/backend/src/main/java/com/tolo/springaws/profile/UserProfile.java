package com.tolo.springaws.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private final UUID userProfileId;
    private final String username;
    private String userProfileImgUrl;

    public UserProfile(UUID userProfileId, String username, String userProfileImgUrl) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImgUrl = userProfileImgUrl;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getUserProfileImgUrl() {
        return Optional.ofNullable(userProfileImgUrl);
    }

    public void setUserProfileImgUrl(String userProfileImgUrl) {
        this.userProfileImgUrl = userProfileImgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfileImgUrl, that.userProfileImgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImgUrl);
    }
}

package com.tolo.springaws.datastore;

import com.tolo.springaws.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(
                UUID.fromString("c9bcc954-2231-44e5-9d8c-988a26c34e4c"), "Messi",null));
        USER_PROFILES.add(new UserProfile(
                UUID.fromString("5a4fb509-1fa1-4727-99b3-1999765e7ff6"), "DeJong",null));
        USER_PROFILES.add(new UserProfile(
                UUID.fromString("7f5af209-8914-48f9-81cd-88490223b299"), "Garcia",null));

    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}

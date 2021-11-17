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
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "qwe",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "asd",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "zxc",null));

    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}

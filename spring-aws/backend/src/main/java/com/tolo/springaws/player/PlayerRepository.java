package com.tolo.springaws.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID>{

//    private final FakeProfileDataStore fakeProfileDataStore;
//
//    @Autowired
//    public ProfileRepository(FakeProfileDataStore fakeProfileDataStore) {
//        this.fakeProfileDataStore = fakeProfileDataStore;
//    }
//
//    List<Profile> getProfiles() {
//        return fakeProfileDataStore.getProfiles();
//    }

}

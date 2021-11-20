package com.tolo.springaws.profile;

import com.tolo.springaws.datastore.FakeProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long>{

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

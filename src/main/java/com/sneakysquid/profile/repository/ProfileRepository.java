package com.sneakysquid.profile.repository;

import com.sneakysquid.profile.domain.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<ProfileDocument, String> {
    Optional<ProfileDocument> findByEmail(String email);
}

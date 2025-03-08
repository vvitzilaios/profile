package com.sneakysquid.profile.repository;

import com.sneakysquid.profile.domain.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends MongoRepository<ProfileDocument, String> {
    Optional<ProfileDocument> findByEmail(String email);
    List<ProfileDocument> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrderByIdAsc(String keyword1, String keyword2);
}

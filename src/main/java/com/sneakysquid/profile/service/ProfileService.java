package com.sneakysquid.profile.service;

import com.sneakysquid.profile.domain.ProfileDocument;
import com.sneakysquid.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public List<ProfileDocument> search(String keyword) {
        return profileRepository
                .findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrderByIdAsc(keyword, keyword);
    }

    @Transactional(readOnly = true)
    public ProfileDocument findById(String id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Transactional
    public ProfileDocument save(ProfileDocument profile) {
        return profileRepository.save(profile);
    }

    @Transactional
    public void follow(String currentUserId, String userIdToFollow) {
        ProfileDocument currentUser = profileRepository.findByEmail(currentUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        ProfileDocument userToFollow = profileRepository.findById(userIdToFollow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User to follow not found"));

        currentUser.getFollowing().add(userIdToFollow);
        userToFollow.getFollowers().add(currentUser.getId());

        profileRepository.save(currentUser);
        profileRepository.save(userToFollow);
    }

    @Transactional
    public void unfollow(String currentUserId, String userIdToUnfollow) {
        ProfileDocument currentUser = profileRepository.findByEmail(currentUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        ProfileDocument userToUnfollow = profileRepository.findById(userIdToUnfollow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User to unfollow not found"));

        currentUser.getFollowing().remove(userIdToUnfollow);
        userToUnfollow.getFollowers().remove(currentUser.getId());

        profileRepository.save(currentUser);
        profileRepository.save(userToUnfollow);
    }

    @Transactional(readOnly = true)
    public Set<String> getFollowers(String currentUserId) {
        ProfileDocument currentUser = profileRepository.findById(currentUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return currentUser.getFollowers();
    }
}
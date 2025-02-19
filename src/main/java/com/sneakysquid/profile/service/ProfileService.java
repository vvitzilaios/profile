package com.sneakysquid.profile.service;

import com.sneakysquid.profile.domain.ProfileDocument;
import com.sneakysquid.profile.domain.dto.ProfileDocumentDto;
import com.sneakysquid.profile.repository.ProfileRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDocument getProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public ProfileDocument saveProfile(ProfileDocument profile) {
        return profileRepository.save(profile);
    }

    @Transactional
    public void follow(String currentUserEmail, String userIdToFollow) {
        ProfileDocument currentUser = profileRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
        ProfileDocument userToFollow = profileRepository.findById(userIdToFollow)
                .orElseThrow(() -> new NotFoundException("User to follow not found"));

        // Update following list
        currentUser.getFollowing().add(userIdToFollow);
        userToFollow.getFollowers().add(currentUser.getId());

        profileRepository.save(currentUser);
        profileRepository.save(userToFollow);
    }

    @Transactional
    public void unfollow(String currentUserEmail, String userIdToUnfollow) {
        ProfileDocument currentUser = profileRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
        ProfileDocument userToUnfollow = profileRepository.findById(userIdToUnfollow)
                .orElseThrow(() -> new NotFoundException("User to unfollow not found"));

        // Update lists
        currentUser.getFollowing().remove(userIdToUnfollow);
        userToUnfollow.getFollowers().remove(currentUser.getId());

        profileRepository.save(currentUser);
        profileRepository.save(userToUnfollow);
    }

    public Set<String> getFollowers(String currentUserEmail) {
        ProfileDocument currentUser = profileRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return currentUser.getFollowers();
    }

}

package com.sneakysquid.profile.controller;


import com.sneakysquid.profile.domain.ProfileDocument;
import com.sneakysquid.profile.domain.dto.ProfileDocumentDto;
import com.sneakysquid.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileDocument> getProfileByEmail(@RequestHeader("X-User-Email") String email) {
        return ResponseEntity.ok(profileService.getProfileByEmail(email));
    }

    @PostMapping("/save")
    public ResponseEntity<ProfileDocument> saveProfile(@RequestBody ProfileDocument profile) {
        ProfileDocument savedProfile = profileService.saveProfile(profile);
        return ResponseEntity.ok(savedProfile);
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> follow(
            @RequestHeader("X-User-Email") String currentUserEmail,
            @PathVariable String userId) {
        profileService.follow(currentUserEmail, userId);
        return ResponseEntity.ok("You are now following this user.");
    }

    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollow(
            @RequestHeader("X-User-Email") String currentUserEmail,
            @PathVariable String userId) {
        profileService.unfollow(currentUserEmail, userId);
        return ResponseEntity.ok("You have unfollowed this user.");
    }
}

package com.sneakysquid.profile.controller;


import com.sneakysquid.profile.domain.ProfileDocument;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/search")
    public ResponseEntity<List<ProfileDocument>> search(@RequestParam String keyword) {
        List<ProfileDocument> list = profileService.search(keyword);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDocument> getById(@PathVariable String id) {
        ProfileDocument profile = profileService.findById(id);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/save")
    public ResponseEntity<ProfileDocument> save(@RequestBody ProfileDocument profile) {
        ProfileDocument savedProfile = profileService.save(profile);
        return ResponseEntity.ok(savedProfile);
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> follow(
            @RequestHeader("X-User-Username") String currentUserEmail,
            @PathVariable String userId) {
        profileService.follow(currentUserEmail, userId);
        return ResponseEntity.ok("You are now following this user.");
    }

    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollow(
            @RequestHeader("X-User-Username") String currentUserEmail,
            @PathVariable String userId) {
        profileService.unfollow(currentUserEmail, userId);
        return ResponseEntity.ok("You have unfollowed this user.");
    }
}

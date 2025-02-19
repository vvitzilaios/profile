package com.sneakysquid.profile.domain.dto;

import com.sneakysquid.profile.domain.enums.InterestType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProfileDocumentDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String bio;
    private String website;
    private String location;
    private String profilePicture;
    private String coverImage;
    private String profession;
    private Set<InterestType> interests;
    private Set<String> followers;
    private Set<String> following;
}

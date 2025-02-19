package com.sneakysquid.profile.domain;

import com.sneakysquid.profile.domain.enums.InterestType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document(collection = "profiles")
@Data
public class ProfileDocument {
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

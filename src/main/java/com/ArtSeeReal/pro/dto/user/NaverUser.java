package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverUser {

    private String resultcode;
    private String message;
    private Response response;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        private String id;
        private String nickname;
        private String email;
        private String mobile;

        @JsonProperty("mobile_e164")
        private String mobileE164;
        private String name;
    }

    public UserCreateRequestDTO naverToUserDTO() {
        return UserCreateRequestDTO.builder()
                .userId(response.getId())
                .name(response.getName())
                .password(UUID.randomUUID().toString().substring(0, 12))
                .nickname(response.getNickname())
                .email(response.getEmail())
                .emailSecret(true)
                .phone(response.getMobile())
                .phoneSecret(true)
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
    }
}


package com.ArtSeeReal.pro.dto.user;

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

        public UserCreateRequestDTO kakaoToUserDTO(){
            return UserCreateRequestDTO.builder()
                    .userId(id)
                    .name(name)
                    .password(UUID.randomUUID().toString().substring(12))
                    .nickname(nickname)
                    .email(email)
                    .emailSecret(true)
                    .phone(mobile)
                    .phoneSecret(true)
                    .userType(AUTHOR)
                    .regDate(LocalDateTime.now())
                    .build();
        }

    }
}


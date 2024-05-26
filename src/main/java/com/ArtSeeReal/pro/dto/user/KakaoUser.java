package com.ArtSeeReal.pro.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUser {

    private long id;

    @JsonProperty("connected_at")
    private ZonedDateTime connectedAt;

    private Properties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String nickname;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoAccount {
        @JsonProperty("profile_nickname_needs_agreement")
        private boolean profileNicknameNeedsAgreement;

        private Profile profile;

        @JsonProperty("has_email")
        private boolean hasEmail;

        @JsonProperty("email_needs_agreement")
        private boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private boolean isEmailVerified;

        private static String email;

        private static String getEmail(){
            return email;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Profile {
            private static String nickname;

            @JsonProperty("is_default_nickname")
            private boolean isDefaultNickname;

            private static String getNickname(){
                return nickname;
            }

        }
    }

    public UserCreateRequestDTO kakaoToUserDTO(){
        return UserCreateRequestDTO.builder()
                .userId(String.valueOf(id))
                .name(KakaoAccount.Profile.getNickname())
                .password(UUID.randomUUID().toString().substring(12))
                .nickname(KakaoAccount.Profile.getNickname())
                .email(KakaoAccount.getEmail())
                .emailSecret(true)
                .phone(UUID.randomUUID().toString().substring(12))
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
    }
}

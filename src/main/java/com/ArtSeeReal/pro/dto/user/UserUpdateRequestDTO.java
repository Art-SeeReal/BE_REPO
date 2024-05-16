package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.enums.UserType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserUpdateRequestDTO {
    private String uid;
    private String userId;
    private String name;
    private String password;
    private String nickname;
    private String email;
    private boolean emailSecret;
    private String phone;
    private boolean phoneSecret;
    private UserType userType;
    private String modUserUid;

    public User updateDTOTOEntity(){
        return User.
                builder()
                .uid(uid)
                .userId(userId)
                .name(name)
                .password(password)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .userType(userType)
                .regDate(LocalDateTime.now())
                .build();
    }
}
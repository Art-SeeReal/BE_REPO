package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.enums.UserType;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserCreateRequestDTO {
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
    private LocalDateTime regDate;

    public User from(String uid, BCryptPasswordEncoder bCryptPasswordEncoder){
        return User.
                builder()
                .uid(uid)
                .userId(userId)
                .name(name)
                .password(bCryptPasswordEncoder.encode(password))
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

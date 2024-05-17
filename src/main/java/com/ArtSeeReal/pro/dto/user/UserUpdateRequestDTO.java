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
public class UserUpdateRequestDTO {
    private String uid;
    private String name;
    private String password;
    private String nickname;
    private String email;
    private boolean emailSecret;
    private String phone;
    private boolean phoneSecret;
}
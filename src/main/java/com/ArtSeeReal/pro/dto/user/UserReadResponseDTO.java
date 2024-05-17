package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.enums.UserType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserReadResponseDTO {
    private String userId;
    private String name;
    private String nickname;
    private String email;
    private boolean emailSecret;
    private String phone;
    private boolean phoneSecret;
    private UserType userType;
    private LocalDateTime regDate;
}

package com.ArtSeeReal.pro.dto.user;

import lombok.*;

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
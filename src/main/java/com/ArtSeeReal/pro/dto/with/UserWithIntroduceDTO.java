package com.ArtSeeReal.pro.dto.with;

import com.ArtSeeReal.pro.dto.user.UserProfileReadResponseDTO;
import com.ArtSeeReal.pro.entity.main.Introduce;
import com.ArtSeeReal.pro.entity.main.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserWithIntroduceDTO {
    private Introduce introduce;
    private User user;

    public UserProfileReadResponseDTO entityToDTO(){
        return UserProfileReadResponseDTO.builder()
                .nickName(user.getNickname())
                .email(user.isEmailSecret()? null : user.getEmail())
                .phone(user.isPhoneSecret()? null : user.getPhone())
                .intro(introduce.getContent())
                .build();
    }
}

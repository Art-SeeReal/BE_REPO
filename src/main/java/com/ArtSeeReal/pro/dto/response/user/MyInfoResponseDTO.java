package com.ArtSeeReal.pro.dto.response.user;

import com.ArtSeeReal.pro.enums.UserType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MyInfoResponseDTO {
    private String nickname;
    private String email;
    private String phone;
    private String intro;
    private UserType userType;
}

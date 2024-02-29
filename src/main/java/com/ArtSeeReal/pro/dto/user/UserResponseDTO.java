package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDTO {
    private String uid;
    private String userId;
    private String name;
    private String nickname;
    private String email;
    private boolean emailSecret;
    private String phone;
    private boolean phoneSecret;
    private RegionType regionType;
    private UserType userType;
    private LocalDateTime regDate;

}

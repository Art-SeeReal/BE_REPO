package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.entity.module.UserModule;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "DEF_USER_HISTORY_TB")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistory extends UserModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 16, nullable = false)
    private String exName;

    @Column(length = 32, nullable = false)
    private String exPassword;

    @Column(length = 16, nullable = false) // unique = true
    private String exNickname;

    @Column(length = 64, nullable = false) // unique = true
    private String exEmail;

    @Column(nullable = false)
    private boolean exEmailSecret;

    @Column(length = 32, nullable = false)
    private String exPhone;

    @Column(nullable = false)
    private boolean exPhoneSecret;

    @Column(length = 16)
    private RegionType exRegionType;

    @Column(nullable = false)
    private UserType exUserType;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;


    public UserHistory of(String uid, User user, UserUpdateRequestDTO dto){
        return UserHistory.builder()
                .uid(uid)
                .userUid(user.getUid())
                .userId(user.getUserId())
                .name(dto.getName())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .emailSecret(dto.isEmailSecret())
                .phone(dto.getPhone())
                .phoneSecret(dto.isPhoneSecret())
                .regionType(dto.getRegionType())
                .userType(dto.getUserType())
                .exName(user.getName())
                .exPassword(user.getPassword())
                .exNickname(user.getNickname())
                .exEmail(user.getEmail())
                .exEmailSecret(user.isEmailSecret())
                .exPhone(user.getPhone())
                .exPhoneSecret(user.isPhoneSecret())
                .exRegionType(user.getRegionType())
                .exUserType(user.getUserType())
                .regDate(user.getRegDate())
                .modDate(LocalDateTime.now())
                .modUserUid(dto.getModUserUid())
                .build();
    }

}

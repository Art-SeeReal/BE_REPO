package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "DEF_USER_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 32, nullable = false, unique = true)
    private String userId;

    @Column(length = 16, nullable = false)
    private String oldName;

    @Column(length = 32, nullable = false)
    private String oldPassword;

    @Column(length = 16, nullable = false, unique = true)
    private String oldNickname;

    @Column(length = 64, nullable = false, unique = true)
    private String oldEmail;

    @Column(nullable = false)
    private boolean oldEmailSecret;

    @Column(length = 32, nullable = false)
    private String oldPhone;

    @Column(nullable = false)
    private boolean oldPhoneSecret;

    @Column(length = 16)
    private RegionType oldRegionType;

    @Column(nullable = false)
    private UserType oldUserType;

    @Column(length = 16, nullable = false)
    private String newName;

    @Column(length = 32, nullable = false)
    private String newPassword;

    @Column(length = 16, nullable = false, unique = true)
    private String newNickname;

    @Column(length = 64, nullable = false, unique = true)
    private String newEmail;

    @Column(nullable = false)
    private boolean newEmailSecret;

    @Column(length = 32, nullable = false)
    private String newPhone;

    @Column(nullable = false)
    private boolean newPhoneSecret;

    @Column(length = 16)
    private RegionType newRegionType;

    @Column(nullable = false)
    private UserType newUserType;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;


    public UserHistory of(String uid, User user, UserUpdateRequestDTO dto){
        return UserHistory.builder()
                .uid(uid)
                .userUid(user.getUid())
                .userId(user.getUserId())
                .oldName(user.getName())
                .newName(dto.getName())
                .oldPassword(user.getPassword())
                .newPassword(dto.getPassword())
                .oldNickname(user.getNickname())
                .newNickname(dto.getNickname())
                .oldNickname(user.getNickname())
                .newNickname(dto.getNickname())
                .oldEmail(user.getEmail())
                .newEmail(dto.getEmail())
                .oldEmailSecret(user.isEmailSecret())
                .newEmailSecret(dto.isEmailSecret())
                .oldPhone(user.getPhone())
                .newPhone(dto.getPhone())
                .oldPhoneSecret(user.isPhoneSecret())
                .newPhoneSecret(dto.isPhoneSecret())
                .oldRegionType(user.getRegionType())
                .newRegionType(dto.getRegionType())
                .oldUserType(user.getUserType())
                .newUserType(dto.getUserType())
                .modDate(LocalDateTime.now())
                .modUserUid(dto.getModUserUid())
                .build();
    }
}

package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.entity.delete.UserDelete;
import com.ArtSeeReal.pro.entity.history.UserHistory;
import com.ArtSeeReal.pro.entity.module.UserModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "DEF_USER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class User extends UserModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    public UserResponseDTO from(){
        return UserResponseDTO.
                builder()
                .uid(uid)
                .userId(userId)
                .name(name)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .regionType(regionType)
                .userType(userType)
                .regDate(regDate)
                .build();
    }

    public UserDelete of(String uid, String delUserUid){
        return UserDelete
                .builder()
                .uid(uid)
                .userUid(this.uid)
                .userId(userId)
                .name(name)
                .password(password)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .regionType(regionType)
                .userType(userType)
                .regDate(regDate)
                .delDate(LocalDateTime.now())
                .delUserUid(delUserUid)
                .build();
    }

    public UserHistory passwordOfUserHistory(String uid, String password){
        return UserHistory.builder()
                .uid(uid)
                .userUid(this.uid)
                .userId(userId)
                .name(name)
                .password(password)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .regionType(regionType)
                .userType(userType)
                .exName(name)
                .exPassword(this.password)
                .exNickname(nickname)
                .exEmail(email)
                .exEmailSecret(emailSecret)
                .exPhone(phone)
                .exPhoneSecret(phoneSecret)
                .exRegionType(regionType)
                .exUserType(userType)
                .regDate(regDate)
                .modDate(LocalDateTime.now())
                .modUserUid(this.uid)
                .build();
    }

    public void passwordChange(String newPassword){
        this.password = newPassword;
    }
}

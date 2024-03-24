package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.entity.delete.UserDelete;
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
import lombok.ToString;

@Entity(name = "DEF_USER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 32, nullable = false, unique = true)
    private String userId;

    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 16, nullable = false, unique = true)
    private String nickname;

    @Column(length = 64, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean emailSecret;

    @Column(length = 32, nullable = false)
    private String phone;

    @Column(nullable = false)
    private boolean phoneSecret;

    @Column(length = 16)
    private RegionType regionType;

    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private LocalDateTime regDate;

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

    public User passwordChange(String password){
        return User
                .builder()
                .uid(uid)
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
                .build();
    }
}

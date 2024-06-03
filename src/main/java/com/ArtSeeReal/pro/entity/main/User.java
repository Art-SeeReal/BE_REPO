package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.user.UserCreateResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.UserDelete;
import com.ArtSeeReal.pro.entity.history.UserHistory;
import com.ArtSeeReal.pro.entity.module.UserModule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity(name = "DEF_USER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Table(indexes = {
        @Index(name = "idx_user_nickname", columnList = "nickname"),
        @Index(name = "idx_user_userId", columnList = "userId"),
        @Index(name = "idx_user_email", columnList = "email")})
public class User extends UserModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    public UserCreateResponseDTO entityToCreateDTO(){
        return UserCreateResponseDTO.
                builder()
                .uid(uid)
                .userId(userId)
                .name(name)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .userType(userType)
                .regDate(regDate)
                .build();
    }
    public UserReadResponseDTO entityToReadDTO(){
        return UserReadResponseDTO.
                builder()
                .userId(userId)
                .name(name)
                .nickname(nickname)
                .email(email)
                .emailSecret(emailSecret)
                .phone(phone)
                .phoneSecret(phoneSecret)
                .userType(userType)
                .regDate(regDate)
                .build();
    }

    public UserDelete userOfDeleteEntity(String uid){
        return UserDelete.builder()
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
                .userType(userType)
                .regDate(regDate)
                .delDate(LocalDateTime.now())
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
                .userType(userType)
                .exName(name)
                .exPassword(this.password)
                .exNickname(nickname)
                .exEmail(email)
                .exEmailSecret(emailSecret)
                .exPhone(phone)
                .exPhoneSecret(phoneSecret)
                .regDate(regDate)
                .modDate(LocalDateTime.now())
                .build();
    }

    public UserHistory userOfHistoryEntity(String uid, UserUpdateRequestDTO dto){
        return UserHistory.builder()
                .uid(uid)
                .userUid(this.uid)
                .userId(userId)
                .name(dto.getName())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .emailSecret(dto.isEmailSecret())
                .phone(dto.getPhone())
                .phoneSecret(dto.isPhoneSecret())
                .exName(name)
                .exPassword(password)
                .exNickname(nickname)
                .exEmail(email)
                .exEmailSecret(emailSecret)
                .exPhone(phone)
                .exPhoneSecret(phoneSecret)
                .regDate(regDate)
                .userType(userType)
                .modDate(LocalDateTime.now())
                .build();
    }

    public void passwordChange(String newPassword){
        this.password = newPassword;
    }

    public void change(UserUpdateRequestDTO dto, BCryptPasswordEncoder bCryptPasswordEncoder){
        name = dto.getName();
        password = bCryptPasswordEncoder.encode(dto.getPassword());
        nickname = dto.getNickname();
        email = dto.getEmail();
        emailSecret = dto.isEmailSecret();
        phone = dto.getPhone();
        phoneSecret = dto.isPhoneSecret();
    }
}

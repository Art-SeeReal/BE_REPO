package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.UserModule;
import com.ArtSeeReal.pro.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    @Column(length = 64, nullable = false)
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
    @Column(nullable = false)
    private UserType exUserType;
    @Column(nullable = false)
    private LocalDateTime modDate;
    @Column(length = 64,nullable = false)
    private String modUserUid;

}

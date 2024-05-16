package com.ArtSeeReal.pro.entity.module;

import com.ArtSeeReal.pro.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class UserModule {
    @Column(length = 32, nullable = false) // unique = true
    protected String userId;
    @Column(length = 16, nullable = false)
    protected String name;
    @Column(length = 64, nullable = false)
    protected String password;
    @Column(length = 16, nullable = false) // unique = true
    protected String nickname;
    @Column(length = 64, nullable = false) // unique = true
    protected String email;
    @Column(nullable = false)
    protected boolean emailSecret;
    @Column(length = 32, nullable = false)
    protected String phone;
    @Column(nullable = false)
    protected boolean phoneSecret;
    @Column(nullable = false)
    protected UserType userType;
    @Column(nullable = false)
    protected LocalDateTime regDate;
}

package com.ArtSeeReal.pro.entity.delete;

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

@Entity(name = "DEF_USER_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDelete {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 32, nullable = false, unique = true)
    private String userId;

    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
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

    @Column(nullable = false)
    private LocalDateTime delDate;

    @Column(length = 64,nullable = false)
    private String delUserUid;

}

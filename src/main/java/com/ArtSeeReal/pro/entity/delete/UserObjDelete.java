package com.ArtSeeReal.pro.entity.delete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "USER_OBJECT_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserObjDelete {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userObjUid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Column(nullable = false)
    private Long targetType;

    @Column(nullable = false)
    private LocalDateTime delDate;

    @Column(length = 64,nullable = false)
    private String delUserUid;
}

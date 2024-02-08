package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.enums.ObjType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "USER_OBJECT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserObj {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Column(nullable = false)
    private ObjType targetType;

}

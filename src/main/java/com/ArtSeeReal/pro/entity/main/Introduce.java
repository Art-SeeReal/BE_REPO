package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "INTRODUCE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Introduce {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}

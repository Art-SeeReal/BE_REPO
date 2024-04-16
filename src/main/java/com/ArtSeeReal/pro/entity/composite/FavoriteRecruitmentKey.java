package com.ArtSeeReal.pro.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRecruitmentKey implements Serializable {
    @Column(length = 64,nullable = false)
    private String userUid;
    @Column(length = 64,nullable = false)
    private String recruitmentUid;
}

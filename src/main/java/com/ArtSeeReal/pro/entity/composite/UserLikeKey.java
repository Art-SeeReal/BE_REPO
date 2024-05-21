package com.ArtSeeReal.pro.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeKey implements Serializable {
    @Column(length = 64,nullable = false)
    private String myUserUid;
    @Column(length = 64,nullable = false)
    private String yourUserUid;
}

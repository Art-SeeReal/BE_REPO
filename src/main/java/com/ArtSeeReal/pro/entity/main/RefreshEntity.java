package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshEntity {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String username;
    @Column(length = 256,nullable = false)
    private String refresh;
    @Column(length = 64,nullable = false)
    private String expiration;

}
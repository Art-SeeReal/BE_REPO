package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.module.SnsModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "SNS_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Sns extends SnsModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
}

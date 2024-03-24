package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.module.RequestModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "REQUEST_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Request extends RequestModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

}

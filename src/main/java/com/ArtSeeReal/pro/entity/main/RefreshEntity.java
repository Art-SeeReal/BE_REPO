package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo user의 uid 값으로 저장
    private Long id;

    private String username;
    private String refresh;
    private String expiration;

}
package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.*;
import lombok.*;

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
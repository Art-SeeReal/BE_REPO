package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "BANNER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 256, nullable = false)
    private String url;

    @Column(length = 512, nullable = false)
    private String linkUrl;

    @Column(nullable = false)
    private LocalDateTime regDate;
}

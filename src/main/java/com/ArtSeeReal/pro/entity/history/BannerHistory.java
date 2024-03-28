package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.BannerModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "BANNER_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BannerHistory extends BannerModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String bannerUid;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String exUrl;

    @Column(length = 512, nullable = false)
    private String exLinkUrl;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;

}

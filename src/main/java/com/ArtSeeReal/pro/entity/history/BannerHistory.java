package com.ArtSeeReal.pro.entity.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "BANNER_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String bannerUid;

    @Column(length = 256, nullable = false)
    private String oldUrl;

    @Column(length = 512, nullable = false)
    private String oldLinkUrl;

    @Column(length = 256, nullable = false)
    private String newUrl;

    @Column(length = 512, nullable = false)
    private String newLinkUrl;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;

}

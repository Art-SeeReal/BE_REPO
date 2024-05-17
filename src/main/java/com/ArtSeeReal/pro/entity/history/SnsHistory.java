package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.SnsModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "SNS_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SnsHistory extends SnsModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String snsUid;
    @Column(length = 256, nullable = false)
    private String exUrl;
    @Column(nullable = false)
    private LocalDateTime modDate;
}

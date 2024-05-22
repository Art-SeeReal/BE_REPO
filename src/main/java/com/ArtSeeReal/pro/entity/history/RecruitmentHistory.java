package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.RecruitmentModule;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "RECRUITMENT_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RecruitmentHistory extends RecruitmentModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String boardUid;
    @Column(length = 128, nullable = false)
    private String exTitle;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String exContent;
    @Column(nullable = false)
    private RegionType exRegionType;
    @Column(nullable = false)
    private CategoryType exCategory;
    @Column(columnDefinition = "TEXT")
    private String exThumbnail;
    @Column(nullable = false)
    private LocalDateTime exDueDate;
    @Column(nullable = false)
    private Long exPayment;
    @Column(nullable = false)
    private LocalDateTime modDate;
}

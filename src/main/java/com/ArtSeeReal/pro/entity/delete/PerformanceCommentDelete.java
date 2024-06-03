package com.ArtSeeReal.pro.entity.delete;

import com.ArtSeeReal.pro.entity.module.PerformanceCommentModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "SHOW_COMMENT_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PerformanceCommentDelete extends PerformanceCommentModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String commentUid;
    @Column(nullable = false)
    private LocalDateTime delDate;
}

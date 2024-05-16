package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.RequestCommentModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "REQUEST_COMMENT_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RequestCommentHistory extends RequestCommentModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String requestCommentUid;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String exContent;
    @Column(nullable = false)
    private LocalDateTime modDate;
    @Column(length = 64,nullable = false)
    private String modUserUid;
}

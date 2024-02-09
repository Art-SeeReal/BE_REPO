package com.ArtSeeReal.pro.entity.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "REQUEST_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String requestUid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false)
    private boolean oldSecret;

    @Column(nullable = false)
    private Long oldType;

    @Column(length = 128, nullable = false)
    private String oldTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String oldContent;

    @Column(nullable = false)
    private boolean newSecret;

    @Column(nullable = false)
    private Long newType;

    @Column(length = 128, nullable = false)
    private String newTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String newContent;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;

}

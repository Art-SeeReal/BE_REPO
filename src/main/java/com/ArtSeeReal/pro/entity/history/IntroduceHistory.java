package com.ArtSeeReal.pro.entity.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "INTRODUCE_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntroduceHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String introduceUid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 2048, nullable = false)
    private String oldContent;

    @Column(length = 2048, nullable = false)
    private String newContent;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;
}

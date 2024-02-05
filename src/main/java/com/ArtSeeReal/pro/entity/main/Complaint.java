package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "COMPLAINT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Column(nullable = false)
    private Long type;

    @Column(nullable = false)
    private LocalDateTime regDate;
}

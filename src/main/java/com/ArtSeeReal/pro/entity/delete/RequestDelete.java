package com.ArtSeeReal.pro.entity.delete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "REQUEST_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDelete {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String requestUid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false)
    private boolean secret;

    @Column(nullable = false)
    private Long type;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Column(nullable = false)
    private LocalDateTime delDate;

    @Column(length = 64,nullable = false)
    private String delUserUid;
}

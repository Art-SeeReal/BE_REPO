package com.ArtSeeReal.pro.entity.delete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FILE_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDelete {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String fileUid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Lob
    @Column(length = 512,nullable = false)
    private byte[] imageData;

    @Column(nullable = false)
    private LocalDateTime delDate;

    @Column(length = 64,nullable = false)
    private String delUserUid;
}

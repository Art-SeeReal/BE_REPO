package com.ArtSeeReal.pro.entity.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FILE_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String fileUid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Lob
    @Column(length = 512,nullable = false)
    private byte[] oldImageData;

    @Lob
    @Column(length = 512,nullable = false)
    private byte[] newImageData;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;
}

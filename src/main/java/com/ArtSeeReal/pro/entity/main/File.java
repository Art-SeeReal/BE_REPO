package com.ArtSeeReal.pro.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FILE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String targetUid;

    @Lob
    @Column(length = 512,nullable = false)
    private byte[] imageData;
}

package com.ArtSeeReal.pro.entity.delete;

import com.ArtSeeReal.pro.entity.module.ComplaintModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "COMPLAINT_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ComplaintDelete extends ComplaintModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    @Column(length = 64,nullable = false)
    private String complaintUid;
    @Column(nullable = false)
    private LocalDateTime delDate;
    @Column(length = 64,nullable = false)
    private String delUserUid;
}

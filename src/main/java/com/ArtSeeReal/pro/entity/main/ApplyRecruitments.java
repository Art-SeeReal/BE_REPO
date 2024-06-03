package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.ApplyRecruitmentKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "APPLY_RECRUITMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRecruitments {
    @EmbeddedId
    private ApplyRecruitmentKey pk;
    @Column(nullable = false)
    private LocalDateTime regDate;

    public ApplyRecruitments(ApplyRecruitmentKey applyRecruitmentKey){
        this.pk = applyRecruitmentKey;
        this.regDate = LocalDateTime.now();
    }
}

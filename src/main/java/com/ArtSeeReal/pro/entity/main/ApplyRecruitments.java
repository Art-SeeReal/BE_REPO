package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.composite.ApplyRecruitmentKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "APPLY_RECRUITMENT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRecruitments {
    @EmbeddedId
    private ApplyRecruitmentKey pk;
}

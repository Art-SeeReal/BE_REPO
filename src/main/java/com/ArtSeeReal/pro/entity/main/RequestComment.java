package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.entity.module.RequestCommentModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "REQUEST_COMMENT_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RequestComment extends RequestCommentModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
}

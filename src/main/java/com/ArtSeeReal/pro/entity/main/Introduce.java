package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.introduce.IntroReadResponseDTO;
import com.ArtSeeReal.pro.entity.module.IntroduceModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "INTRODUCE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Introduce extends IntroduceModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    public IntroReadResponseDTO entityToReadDTO(){
        return IntroReadResponseDTO.builder()
                .uid(uid)
                .content(content)
                .build();
    }

    public void deleteContent(){
        content = "";
    }

}

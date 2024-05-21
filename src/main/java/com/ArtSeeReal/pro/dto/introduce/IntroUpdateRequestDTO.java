package com.ArtSeeReal.pro.dto.introduce;

import com.ArtSeeReal.pro.entity.main.Introduce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class IntroUpdateRequestDTO {
    private String uid;
    private final String content;

    public Introduce updateDTOToEntity(){
        return Introduce.builder()
                .uid(uid)
                .content(content)
                .build();
    }
}

package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "BANNER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 256, nullable = false)
    private byte[] imageData;

    @Column(length = 512, nullable = false)
    private String linkUrl;

    @Column(nullable = false)
    private LocalDateTime regDate;

    public BannerCreateResponseDTO toCreateDTO(){
        return BannerCreateResponseDTO.builder()
                .uid(uid)
                .imageData(imageData)
                .linkUrl(linkUrl)
                .regDate(regDate)
                .build();
    }

    public BannerReadResponseDTO toReadDTO(){
        return BannerReadResponseDTO.builder()
                .uid(uid)
                .imageData(imageData)
                .linkUrl(linkUrl)
                .regDate(regDate)
                .build();
    }

    public void change(BannerUpdateRequestDTO dto) throws IOException {
        imageData = dto.getImageData().getBytes();
        linkUrl = dto.getLinkUrl();
    }
}

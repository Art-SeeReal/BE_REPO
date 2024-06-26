package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.BannerDelete;
import com.ArtSeeReal.pro.entity.history.BannerHistory;
import com.ArtSeeReal.pro.entity.module.BannerModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.time.LocalDateTime;

@Entity(name = "BANNER_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Banner extends BannerModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    public BannerCreateResponseDTO toCreateDTO(){
        return BannerCreateResponseDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .regDate(regDate)
                .build();
    }

    public BannerReadResponseDTO toReadDTO(){
        return BannerReadResponseDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .regDate(regDate)
                .build();
    }

    public void change(BannerUpdateRequestDTO dto) throws IOException {
        imageUrl = dto.getImageUrl();
        linkUrl = dto.getLinkUrl();
    }

    public BannerHistory toHistoryEntity(String uid, BannerUpdateRequestDTO dto,String modUser) throws IOException {
        return BannerHistory.builder()
                .uid(uid)
                .bannerUid(this.uid)
                .imageUrl(dto.getImageUrl())
                .exImageUrl(imageUrl)
                .linkUrl(dto.getLinkUrl())
                .exLinkUrl(linkUrl)
                .regDate(regDate)
                .modDate(LocalDateTime.now())
                .build();
    }

    public BannerDelete toDeleteEntity(String uid, String delUser){
        return BannerDelete.builder()
                .uid(uid)
                .bannerUid(this.uid)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .regDate(regDate)
                .delDate(LocalDateTime.now())
                .build();
    }

}

package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecruitmentReadRequestDTO {
    private String userUid;
    private String nickname;
    private String title;
    private List<CategoryType> categories;
    private List<RegionType> regionTypes;
    private Integer pageNum;
    private Integer limit;
    private String sortField;
    private String sortType; // asc, desc
}

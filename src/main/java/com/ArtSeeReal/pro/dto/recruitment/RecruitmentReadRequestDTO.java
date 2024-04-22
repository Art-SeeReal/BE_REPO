package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import java.util.List;
import lombok.Builder;
import lombok.Data;

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

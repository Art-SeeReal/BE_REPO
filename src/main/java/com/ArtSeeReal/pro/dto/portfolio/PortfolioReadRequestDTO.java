package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioReadRequestDTO {
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

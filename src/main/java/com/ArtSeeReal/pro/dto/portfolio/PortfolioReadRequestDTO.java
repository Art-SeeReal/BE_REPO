package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.RegionType;
import java.util.List;
import java.util.Locale.Category;
import lombok.Data;

@Data
public class PortfolioReadRequestDTO {
    private String userUid;
    private String nickname;
    private String title;
    private List<Category> categories;
    private List<RegionType> regionTypes;
    private Integer pageNum;
    private Integer limit;
    private String sortField;
    private String sortType; // asc, desc
}

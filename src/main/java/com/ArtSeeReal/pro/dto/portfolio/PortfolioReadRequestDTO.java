package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
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
    private Integer pageNum;
    private Integer limit;
    private String sortField;
    private String sortType; // asc, desc
}

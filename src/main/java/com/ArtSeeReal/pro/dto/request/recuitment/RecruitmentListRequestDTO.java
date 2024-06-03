package com.ArtSeeReal.pro.dto.request.recuitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.util.List;

@Data
public class RecruitmentListRequestDTO {
    private final List<CategoryType> fields;
    private final List<RegionType> regions;
    private final String keyWords;
    private final Integer postCount;

    @ConstructorProperties({"fields", "regions", "keyWords", "postCount"})
    @JsonCreator
    public RecruitmentListRequestDTO(
            @JsonProperty("fields") List<CategoryType> fields,
            @JsonProperty("regions") List<RegionType> regions,
            @JsonProperty("keyWords") String keyWords,
            @JsonProperty("postCount") Integer postCount) {
        this.fields = (fields == null || fields.isEmpty()) ? List.of(CategoryType.values()) : fields;
        this.regions = (regions == null || regions.isEmpty()) ? List.of(RegionType.values()) : regions;
        this.keyWords = keyWords;
        this.postCount = (postCount == null) ? 20 : postCount;
    }
}

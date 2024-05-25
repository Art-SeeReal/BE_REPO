package com.ArtSeeReal.pro.dto.request.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.util.List;

@Data
public class PortfolioListRequestDTO {
    private final List<CategoryType> fields;
    private final String keyWords;
    private final Integer postCount;

    @ConstructorProperties({"fields", "keyWords", "postCount"})
    @JsonCreator
    public PortfolioListRequestDTO(
            @JsonProperty("fields") List<CategoryType> fields,
            @JsonProperty("keyWords") String keyWords,
            @JsonProperty("postCount") Integer postCount) {
        this.fields = (fields == null || fields.isEmpty()) ? List.of(CategoryType.values()) : fields;
        this.keyWords = keyWords;
        this.postCount = (postCount == null) ? 20 : postCount;
    }
}

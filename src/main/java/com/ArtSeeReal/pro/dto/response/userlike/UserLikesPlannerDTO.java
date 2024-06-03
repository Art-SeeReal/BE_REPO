package com.ArtSeeReal.pro.dto.response.userlike;

import com.ArtSeeReal.pro.dto.user.UserPlannerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserLikesPlannerDTO {
    private final List<UserPlannerDTO> result;
    private final Integer count;
}

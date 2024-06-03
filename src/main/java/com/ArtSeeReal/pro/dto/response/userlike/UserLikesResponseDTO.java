package com.ArtSeeReal.pro.dto.response.userlike;

import com.ArtSeeReal.pro.dto.user.UserLikesInfoDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserLikesResponseDTO {
    private List<UserLikesInfoDTO> result;
    private Integer count;
}

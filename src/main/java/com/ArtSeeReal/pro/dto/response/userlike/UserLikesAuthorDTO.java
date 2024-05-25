package com.ArtSeeReal.pro.dto.response.userlike;

import com.ArtSeeReal.pro.dto.user.UserAuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserLikesAuthorDTO {
    private final List<UserAuthorDTO> result;
    private final Integer count;
}

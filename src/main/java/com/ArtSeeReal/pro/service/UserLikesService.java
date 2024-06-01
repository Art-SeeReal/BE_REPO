package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.response.userlike.UserLikesAuthorDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesResponseDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesPlannerDTO;
import com.ArtSeeReal.pro.enums.UserType;

public interface UserLikesService {
        UserLikesAuthorDTO userLikesAuthor(String userUid);
        UserLikesPlannerDTO userLikesPlanner(String userUid);
        UserLikesResponseDTO userLikes(String userUid, UserType userType);
}

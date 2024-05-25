package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.response.userlike.UserLikesAuthorDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesPlannerDTO;

public interface UserLikesService {
        UserLikesAuthorDTO userLikesAuthor(String userUid);
        UserLikesPlannerDTO userLikesPlanner(String userUid);
}

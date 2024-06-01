package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.response.userlike.UserLikesAuthorDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesResponseDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesPlannerDTO;
import com.ArtSeeReal.pro.dto.user.UserAuthorDTO;
import com.ArtSeeReal.pro.dto.user.UserPlannerDTO;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.repository.querydsl.main.UserQueryDslRepository;
import com.ArtSeeReal.pro.service.UserLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikesServiceImpl implements UserLikesService {

    private final UserQueryDslRepository userQueryDslRepository;

    @Override
    public UserLikesAuthorDTO userLikesAuthor(String userUid) {
        List<UserAuthorDTO> result = userQueryDslRepository.findUserLikeAuthorByUserUid(userUid);
        return new UserLikesAuthorDTO(result,result.size());
    }

    @Override
    public UserLikesPlannerDTO userLikesPlanner(String userUid) {
        List<UserPlannerDTO> result = userQueryDslRepository.findUserLikePlannerByUserUid(userUid);
        return new UserLikesPlannerDTO(result, result.size());
    }

    @Override
    public UserLikesResponseDTO userLikes(String userUid, UserType userType) {
        return userQueryDslRepository.findUserLikesByUserUidAndUserType(userUid,userType);
    }
}

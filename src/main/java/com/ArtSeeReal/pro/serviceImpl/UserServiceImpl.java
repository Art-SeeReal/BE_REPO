package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.dto.response.user.ApplicantResponseDTO;
import com.ArtSeeReal.pro.dto.user.*;
import com.ArtSeeReal.pro.entity.composite.UserLikeKey;
import com.ArtSeeReal.pro.entity.delete.UserDelete;
import com.ArtSeeReal.pro.entity.history.UserHistory;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.entity.main.UserLikes;
import com.ArtSeeReal.pro.repository.jpa.delete.UserDeleteRepository;
import com.ArtSeeReal.pro.repository.jpa.history.UserHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserLikesRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.repository.querydsl.main.UserQueryDslRepository;
import com.ArtSeeReal.pro.service.IntroduceService;
import com.ArtSeeReal.pro.service.UserService;
import com.ArtSeeReal.pro.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.*;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {
    private final IntroduceService introduceService;
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final UserDeleteRepository userDeleteRepository;
    private final UserLikesRepository userLikesRepository;
    private final UserQueryDslRepository userQueryDslRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidateService validateService;

    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO dto){
        User createUser = dto.from(uidCreator(userRepository),bCryptPasswordEncoder);
        User saveduser = userRepository.save(createUser);
        introduceService.createIntro(saveduser.getUid());
        return saveduser.entityToCreateDTO();
    }
    @Override
    public UserReadResponseDTO readUser(String uid){
        return userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()))
                .entityToReadDTO();
    }
    @Override
    public UserReadResponseDTO updateUser(UserUpdateRequestDTO dto){
        User user = userRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));

        saveHistoryEntity(dto, user);
        user.change(dto,bCryptPasswordEncoder);
        userRepository.save(user);
        User result = userRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));
        return result.entityToReadDTO();
    }

    private void saveHistoryEntity(UserUpdateRequestDTO dto, User user) {
        UserHistory userHistory = user.userOfHistoryEntity(uidCreator(userHistoryRepository), dto);
        userHistoryRepository.save(userHistory);
    }

    @Override
    public String deleteUser(String userId, String delUserUid){
        String uid = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(NO_DATA_ERROR.getMessage()))
                .getUid();
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));
        validateService.roleCheck(delUserUid,uid);
        saveDeleteEntity(uid, user);
        userRepository.deleteById(uid);
        return uid;
    }

    private void saveDeleteEntity(String uid, User user) {
        UserDelete userDelete = user.userOfDeleteEntity(uid);
        userDeleteRepository.save(userDelete);
    }

    @Override
    public boolean checkDuplicateUserId(String id){
        if (userRepository.existsByUserId(id))
            throw new IllegalArgumentException(ID_DUPLICATE_ERROR.getMessage());
        else
            return false;
    }
    @Override
    public boolean checkDuplicateUserNickname(String nickname){
        if (userRepository.existsByNickname(nickname))
            throw new IllegalArgumentException(NICKNAME_DUPLICATE_ERROR.getMessage());
        else
            return false;
    }
    @Override
    public boolean checkDuplicateUserEmail(String email){
        if (userRepository.existsByEmail(email))
            throw new IllegalArgumentException(EMAIL_DUPLICATE_ERROR.getMessage());
        else
            return false;
    }
    @Override
    public void userLikesCreate(String myUserUid, String yourUserUid){
        UserLikeKey likes = new UserLikeKey(myUserUid,yourUserUid);
        if(userLikesRepository.existsById(likes))
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
        userLikesRepository.save(new UserLikes(likes));
    }
    @Override
    public void userLikesDelete(String myUserUid, String yourUserUid){
        UserLikeKey likes = new UserLikeKey(myUserUid,yourUserUid);
        if(userLikesRepository.existsById(likes))
            userLikesRepository.deleteById(likes);
        else
            throw new IllegalArgumentException(NO_DATA_ERROR.getMessage());
    }

    @Override
    public UserProfileReadResponseDTO readIntro(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));
        return userQueryDslRepository.findUserProfileByUserUid(user.getUid())
                .entityToDTO();
    }
    @Override
    public String getUserUid(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(NO_DATA_ERROR.getMessage()))
                .getUid();
    }

    @Override
    public ApplicantResponseDTO ApplicantList(String recruitmentUid) {
        return userQueryDslRepository.findApplicantUsersByRecruitmentUid(recruitmentUid);
    }

    @Override
    public Boolean checkPassword(String userUid ,String password) {
        User user = userRepository.findById(userUid)
                .orElseThrow(() -> new IllegalArgumentException(NO_DATA_ERROR.getMessage()));
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

}

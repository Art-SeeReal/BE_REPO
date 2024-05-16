package com.ArtSeeReal.pro.serviceImpl;

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
    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO dto){
        User createUser = dto.from(uidCreator(userRepository),bCryptPasswordEncoder);
        User saveduser = userRepository.save(createUser);
        introduceService.createIntro(saveduser.getUid());
        UserCreateResponseDTO result = saveduser.entityToCreateDTO();
        return result;
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
        userRepository.save(dto.updateDTOTOEntity());
        User result = userRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));
        return result.entityToReadDTO();
    }

    private void saveHistoryEntity(UserUpdateRequestDTO dto, User user) {
        UserHistory userHistory = user.userOfHistoryEntity(uidCreator(userHistoryRepository), dto);
        userHistoryRepository.save(userHistory);
    }

    @Override
    public String deleteUser(String uid, String delUserUid){
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(NO_USER_DATA_ERROR.getMessage()));
        saveDeleteEntity(uid, delUserUid, user);
        userRepository.deleteById(uid);
        return uid;
    }

    private void saveDeleteEntity(String uid, String delUserUid, User user) {
        UserDelete userDelete = user.userOfDeleteEntity(uid, delUserUid);
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
        // TODO : 검증로직을 만들 필요가 있지 않을까? EX) 유저 pk, 포트폴리오 pk의 유효성을 검사하는
        // TODO : 이거하다가 생각났는데 검증로직을 하나의 별도 서비스로 분리할 필요가 있지 않을까?
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

}

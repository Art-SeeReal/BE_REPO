package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.user.*;

public interface UserService {
    UserCreateResponseDTO createUser(UserCreateRequestDTO dto);
    UserReadResponseDTO readUser(String uid);
    UserReadResponseDTO updateUser(UserUpdateRequestDTO dto);
    String deleteUser(String uid, String delUserUid);
    boolean checkDuplicateUserId(String id);
    boolean checkDuplicateUserNickname(String nickname);
    boolean checkDuplicateUserEmail(String email);
    void userLikesCreate(String myUserUid, String yourUserUid);
    void userLikesDelete(String myUserUid, String yourUserUid);
    UserProfileReadResponseDTO readIntro(String userId);
    String getUserUid(String userId);
}

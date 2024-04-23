package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserUpdateRequestDTO;

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
}

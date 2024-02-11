package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.etc.Uid.UidCreator;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserResponseDTO;
import com.ArtSeeReal.pro.entity.delete.UserDelete;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.repository.delete.UserDeleteRepository;
import com.ArtSeeReal.pro.repository.history.UserHistoryRepository;
import com.ArtSeeReal.pro.repository.main.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final String ID_DUPLICATE_ERROR = "[ERROR] 아이디가 중복되었습니다.";
    private final String NICKNAME_DUPLICATE_ERROR = "[ERROR] 닉네임이 중복되었습니다.";
    private final String EMAIL_DUPLICATE_ERROR = "[ERROR] 이메일이 중복되었습니다.";
    private final UserRepository userRepository;
    private final UserHistoryRepository UserHistoryRepository;
    private final UserDeleteRepository userDeleteRepository;

    public UserResponseDTO createUser(UserRequestDTO dto){
        dto.setUid(userUidCreator());
        User user = userRepository.save(dto.from());
        return user.from();
    }
    private String userUidCreator(){
        boolean duplicate = true;
        String uid = UidCreator();

        while (duplicate) {
            if(userRepository.existsByUid(uid))
                uid = UidCreator();
            else
                duplicate = false;
        }

        return uid;
    }
    public String deleteUser(String uid, String delUserUid){
        Optional<User> user = userRepository.findById(uid);
        UserDelete userDelete = user.get().of(uid,delUserUid);
        userDeleteRepository.save(userDelete);
        userRepository.deleteById(uid);
        return uid;
    }

    public boolean checkDuplicateUserId(String id){
        if (userRepository.existsByUserId(id))
            throw new IllegalArgumentException(ID_DUPLICATE_ERROR);
        else
            return false;
    }

    public boolean checkDuplicateUserNickname(String nickname){
        if (userRepository.existsByNickname(nickname))
            throw new IllegalArgumentException(NICKNAME_DUPLICATE_ERROR);
        else
            return false;
    }

    public boolean checkDuplicateUserEmail(String email){
        if (userRepository.existsByEmail(email))
            throw new IllegalArgumentException(EMAIL_DUPLICATE_ERROR);
        else
            return false;
    }

}

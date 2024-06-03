package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    private final UserRepository userRepository;

    @Override
    public boolean adminCheck(String userUid) {
        return userRepository.findById(userUid)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_USER_DATA_ERROR.getMessage()))
                .getUserType()
                .equals(UserType.ADMIN);
    }

    @Override
    public boolean identification(String userUid, String targetUid) {
        return userUid.equals(targetUid);
    }
    /**
     * role을 확인하여 권한이 있는지 없는지 알려주는 메서드
     * @param userUid 주체인 유저의 Uid
     * @param targetUid 피사체인 유저의 Uid
     */
    @Override
    public void roleCheck(String userUid, String targetUid) {
        if (!adminCheck(userUid) && !identification(userUid, targetUid))
            throw new IllegalArgumentException(ErrorCode.NO_AUTHORITY_ERROR.getMessage());
    }

    @Override
    public void existsUser(String userUid) {
        if(!userRepository.existsByUid(userUid))
            throw new IllegalArgumentException(ErrorCode.NO_USER_DATA_ERROR.getMessage());
    }
}

package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class UserDeleteTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private String userUid;

    @Autowired
    public UserDeleteTest(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void 더미데이터_생성(){
        UserCreateRequestDTO dto = UserCreateRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        userUid = userService.createUser(dto).getUid();
    }
    @Test
    public void 유저_삭제_테스트(){
        userService.deleteUser(userUid,"승미니");
        assertThat(userRepository.existsByUid(userUid)).isFalse();
    }
}

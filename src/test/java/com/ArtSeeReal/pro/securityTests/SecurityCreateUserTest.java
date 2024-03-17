package com.ArtSeeReal.pro.securityTests;

import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SecurityCreateUserTest {
    private final UserService userService;

    private String userId;

    @Autowired
    public SecurityCreateUserTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void 회원가입(){
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .userId("jhy")
                .name("정효영")
                .password("1234")
                .nickname("효돌")
                .email("hh@daum.net")
                .emailSecret(false)
                .phone("01012345678")
                .phoneSecret(false)
                .regionType(RegionType.SEOUL)
                .userType(UserType.AUTHOR)
                .build();

        userId = userService.createUser(userRequestDTO).getUserId();

        assertThat(userId).isEqualTo("jhy");

    }
}

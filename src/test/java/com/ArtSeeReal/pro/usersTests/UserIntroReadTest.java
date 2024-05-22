package com.ArtSeeReal.pro.usersTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.dto.with.UserIntroduceDTO;
import com.ArtSeeReal.pro.service.IntroduceService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class UserIntroReadTest {

    private MockMvc mockMvc;
    private final WebApplicationContext webApplicationContext;
    private final UserService userService;
    private UserIntroduceDTO userIntroduceDTO;

    @Autowired
    public UserIntroReadTest(WebApplicationContext webApplicationContext, IntroduceService introduceService, UserService userService) {
        this.webApplicationContext = webApplicationContext;
        this.userService = userService;
    }

    @BeforeEach
    void 더미데이터_생성() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO
                .builder()
                .userId("testUserId")
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
        userService.createUser(userCreateRequestDTO);

        userIntroduceDTO = new UserIntroduceDTO();
        userIntroduceDTO.setNickname("testNickname");
        userIntroduceDTO.setEmail("test@gmail.com");
        userIntroduceDTO.setPhone("010-1234-5678");
        userIntroduceDTO.setIntro("");
    }

    @Test
    void 모든_데이터공개() throws Exception {
        mockMvc.perform(get("/users/{userId}/profile", "testUserId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value(userIntroduceDTO.getNickname()))
                .andExpect(jsonPath("$.email").value(userIntroduceDTO.getEmail()))
                .andExpect(jsonPath("$.phone").value(userIntroduceDTO.getPhone()))
                .andExpect(jsonPath("$.intro").value(userIntroduceDTO.getIntro()));
    }
}
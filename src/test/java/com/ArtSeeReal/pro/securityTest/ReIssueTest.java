package com.ArtSeeReal.pro.securityTest;

import com.ArtSeeReal.pro.controller.ReissueController;
import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.RefreshService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ReIssueTest {

    private final ReissueController reissueController;
    private final RefreshService refreshService;
    private final UserService userService;
    private final TokenService tokenService;
    private final MockMvc mockMvc;


    @Autowired
    public ReIssueTest(ReissueController reissueController,
                       RefreshService refreshService,
                       UserService userService,
                       TokenService tokenService,
                       MockMvc mockMvc) {
        this.reissueController = reissueController;
        this.refreshService = refreshService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void 더미데이터_생성(){
        UserRequestDTO dto = UserRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .regionType(RegionType.SEOUL)
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        userService.createUser(dto);
    }

    @Test
    void 리프레쉬토큰_재발급테스트() throws Exception {
        String RToken = tokenService.createToken("refresh", "test", "AUTHOR", 86400000L);
        refreshService.createRefreshToken("test","AUTHOR");
        Cookie cookie = new Cookie("refresh", RToken);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/reissue")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .cookie(cookie))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("access"))
                .andExpect(MockMvcResultMatchers.cookie().exists("refresh"));
    }
}

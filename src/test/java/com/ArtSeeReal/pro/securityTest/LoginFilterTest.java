package com.ArtSeeReal.pro.securityTest;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LoginFilterTest {

    @Autowired
    private MockMvc mockMvc;

    private final UserService userService;

    @Autowired
    public LoginFilterTest(UserService userService) {
        this.userService = userService;
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
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userService.createUser(dto);
    }

    @Test
    public void testSuccessfulAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "test")
                        .param("password", "test1234")) // 요청 파라미터에 username과 password 추가
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("access"))
                .andExpect(MockMvcResultMatchers.cookie().exists("refresh"));
    }

    @Test
    public void testUnsuccessfulAuthentication_InvalidUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "invalid_username") // 잘못된 아이디
                        .param("password", "test1234"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized()); // 예상되는 응답: 401 Unauthorized
    }

    @Test
    public void testUnsuccessfulAuthentication_InvalidPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "test") // 유효한 아이디
                        .param("password", "invalid_password")) // 잘못된 비밀번호
                .andExpect(MockMvcResultMatchers.status().isUnauthorized()); // 예상되는 응답: 401 Unauthorized
    }



}

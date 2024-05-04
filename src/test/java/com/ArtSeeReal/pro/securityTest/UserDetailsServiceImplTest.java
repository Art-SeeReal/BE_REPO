//package com.ArtSeeReal.pro.securityTest;
//
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
//import com.ArtSeeReal.pro.entity.main.User;
//import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
//import com.ArtSeeReal.pro.service.UserDetailsServiceImpl;
//import netscape.javascript.JSObject;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.swing.*;
//import java.util.Map;
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class UserDetailsServiceImplTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Test
//    public void testLoadUserByUsername_Success() {
//        // DTO 객체 생성 및 설정
//        UserRequestDTO mockUserDTO = new UserRequestDTO();
//        mockUserDTO.setUserId("test");
//        mockUserDTO.setPassword("1234");
//
//        // DTO를 User 엔티티로 변환
//        User mockUser = mockUserDTO.from();
//
//        when(userRepository.findByUserId("test")).thenReturn(Optional.of(mockUser));
//
//        // 메소드 실행
//        UserDetails userDetails = userDetailsService.loadUserByUsername("test");
//
//        // 결과 검증
//        assertNotNull(userDetails);
//        assertEquals("test", userDetails.getUsername());
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserNotFound() {
//        when(userRepository.findByUserId("test1")).thenReturn(Optional.empty());
//
//        // 예외가 발생하는지 확인
//        assertThrows(UsernameNotFoundException.class, () -> {
//            userDetailsService.loadUserByUsername("test1");
//        });
//    }
//
//
//}

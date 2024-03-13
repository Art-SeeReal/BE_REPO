package com.ArtSeeReal.pro.recruitmentTests;

import static com.ArtSeeReal.pro.enums.RegionType.SEOUL;
import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RecruitmentReadTest {

    private final RecruitmentService recruitService;
    private final UserService userService;
    private String uid;
    private String userUid;
    @Autowired
    public RecruitmentReadTest(RecruitmentService recruitService, UserService userService) {
        this.recruitService = recruitService;
        this.userService = userService;
    }
    @BeforeEach
    public void 더미데이터_생성(){

        UserRequestDTO userRequestDTO = UserRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .regionType(SEOUL)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        userUid = userService.createUser(userRequestDTO).getUid();

        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .regionType(RegionType.SEOUL)
                .category(0L)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .build();
        uid = recruitService.createRecruitment(dto).getUid();
    }
    @Test
    public void 공고_읽어오기(){
        RecruitmentReadResponseDTO dto = recruitService.readRecruitment(uid);
        assertThat(dto.getContent()).isEqualTo("testContent");
    }
}
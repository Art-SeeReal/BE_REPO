package com.ArtSeeReal.pro.recruitmentTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadRequestDTO;
import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RecruitmentPageTest {

    private final RecruitmentService recruitService;
    private final UserService userService;
    @Autowired
    public RecruitmentPageTest(RecruitmentService recruitService, UserService userService) {
        this.recruitService = recruitService;
        this.userService = userService;
    }
    @BeforeEach
    public void 더미데이터_생성() {
        String userUid;

        for (int i = 1; i <= 3; i++) {
            UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                    .userId("test" + i)
                    .name("테스트" + i)
                    .password("test1234" + i)
                    .nickname("testNickname" + i)
                    .email("test" + i + "@gmail.com")
                    .emailSecret(true)
                    .phone("010-1234-5678" + i)
                    .phoneSecret(true)
                    .regionType(RegionType.SEOUL)
                    .userType(UserType.AUTHOR)
                    .regDate(LocalDateTime.now())
                    .build();

            userUid = userService.createUser(userRequestDTO).getUid();

            for (int j = 1; j <= 3; j++) {
                for (CategoryType category : CategoryType.values()) {
                    for (RegionType region : RegionType.values()) {
                        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                                .userUid(userUid)
                                .title("testTitle" + i * j)
                                .content("testContent" + i * j)
                                .region(region)
                                .category(category)
                                .thumbnail("testThumbnail" + i * j)
                                .dueDate(LocalDateTime.now())
                                .build();
                        recruitService.createRecruitment(dto);
                    }
                }
            }
        }
    }
    @Test
    public void 공고_READ_NTCR(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .categories(categoryTypes)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            System.out.println(i+1+"번째 데이터 : " + result.getContent().get(i).toString());
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }
    @Test
    public void 공고_READ_NTC(){
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }

    @Test
    public void 공고_READ_NTR(){
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }

    @Test
    public void 공고_READ_NCR(){
        String nickname = appendRandomNumberToName("testNickname");
        List<CategoryType> categoryTypes = categoryListReturner();
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .categories(categoryTypes)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }

    @Test
    public void 공고_READ_TCR(){
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .title(title)
                .categories(categoryTypes)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }

    @Test
    public void 공고_READ_NT(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
        }
    }
    @Test
    public void 공고_READ_NC(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String nickname = appendRandomNumberToName("testNickname");
        List<CategoryType> categoryTypes = categoryListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 공고_READ_TC(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .title(title)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 공고_READ_NR(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String nickname = appendRandomNumberToName("testNickname");
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }
    @Test
    public void 공고_READ_TR(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String title = appendRandomNumberToName("testTitle");
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .title(title)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }
    @Test
    public void 공고_READ_CR(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        List<CategoryType> categoryTypes = categoryListReturner();
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .categories(categoryTypes)
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }
    @Test
    public void 공고_READ_N(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String nickname = appendRandomNumberToName("testNickname");

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .nickname(nickname)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
        }
    }
    @Test
    public void 공고_READ_T(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        String title = appendRandomNumberToName("testTitle");

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .title(title)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
        }
    }
    @Test
    public void 공고_READ_C(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        List<CategoryType> categoryTypes = categoryListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 공고_READ_R(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역
        List<RegionType> regionTypes = RegionListReturner();

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .regionTypes(regionTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(regionTypes.contains(result.getContent().get(i).getRegion())).isTrue();
        }
    }

    @Test
    public void 공고_READ_(){ // N : 닉네임 T : 타이틀 C : 카테고리 R : 지역

        RecruitmentReadRequestDTO dto = RecruitmentReadRequestDTO.builder()
                .pageNum(0)
                .limit(10)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<RecruitmentReadResponseDTO> result = recruitService.pageReadRecruitment(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            System.out.println(i+1+"번째 데이터 : " + result.getContent().get(i).toString());

        }
    }


    private List<CategoryType> categoryListReturner(){
        List<CategoryType> categoryTypes = new ArrayList<>(List.of(CategoryType.values()));

        // 랜덤한 갯수만큼 선택
        int numOfElementsToSelect = ThreadLocalRandom.current().nextInt(categoryTypes.size()) + 1;

        // 랜덤하게 선택된 요소들의 인덱스
        Set<Integer> selectedIndexes = new HashSet<>();
        while (selectedIndexes.size() < numOfElementsToSelect)
            selectedIndexes.add(ThreadLocalRandom.current().nextInt(categoryTypes.size()));

        // 선택된 요소들을 담을 List
        List<CategoryType> selectedCategories = new ArrayList<>();
        for (int index : selectedIndexes)
            selectedCategories.add(categoryTypes.get(index));

        return selectedCategories;
    }

    private List<RegionType> RegionListReturner(){
        List<RegionType> regionTypes = new ArrayList<>(List.of(RegionType.values()));

        // 랜덤한 갯수만큼 선택
        int numOfElementsToSelect = ThreadLocalRandom.current().nextInt(regionTypes.size()) + 1;

        // 랜덤하게 선택된 요소들의 인덱스
        Set<Integer> selectedIndexes = new HashSet<>();
        while (selectedIndexes.size() < numOfElementsToSelect)
            selectedIndexes.add(ThreadLocalRandom.current().nextInt(regionTypes.size()));

        // 선택된 요소들을 담을 List
        List<RegionType> selectedRegions = new ArrayList<>();
        for (int index : selectedIndexes)
            selectedRegions.add(regionTypes.get(index));

        return selectedRegions;
    }

    private String appendRandomNumberToName(String name) {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1; // 1, 2, 3 중에서 랜덤으로 선택

        return name + randomNumber;
    }
}

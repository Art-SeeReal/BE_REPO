package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class PortfolioPageTest {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private String userUid;
    private String selectedUid;
    @Autowired
    public PortfolioPageTest(PortfolioService portfolioService, UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }
    @BeforeEach
    public void 더미데이터_생성() {
        String userUid;

        for (int i = 1; i <= 3; i++) {
            UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                    .userId("test" + i)
                    .name("테스트" + i)
                    .password("test1234" + i)
                    .nickname("testNickname" + i)
                    .email("test" + i + "@gmail.com")
                    .emailSecret(true)
                    .phone("010-1234-5678" + i)
                    .phoneSecret(true)
                    .userType(UserType.AUTHOR)
                    .regDate(LocalDateTime.now())
                    .build();

            userUid = userService.createUser(userCreateRequestDTO).getUid();

            for (int j = 1; j <= 3; j++) {
                for (CategoryType category : CategoryType.values()) {
                    for (RegionType region : RegionType.values()) {
                        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                                .userUid(userUid)
                                .title("testTitle" + i * j)
                                .content("testContent" + i * j)
                                .category(category)
                                .thumbnail("testThumbnail" + i * j)
                                .build();
                        portfolioService.createPortfolio(dto);
                    }
                }
            }
        }
    }
    @Test
    public void 포트폴리오_READ_NTC(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            System.out.println(i+1+"번째 데이터 : " + result.getContent().get(i).toString());
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 포트폴리오_READ_NT(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(result.getContent().get(i).getTitle()).contains(title);
        }
    }
    @Test
    public void 포트폴리오_READ_NC(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String nickname = appendRandomNumberToName("testNickname");
        List<CategoryType> categoryTypes = categoryListReturner();

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .nickname(nickname)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 포트폴리오_READ_TC(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .title(title)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }
    @Test
    public void 포트폴리오_READ_N(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String nickname = appendRandomNumberToName("testNickname");

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .nickname(nickname)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getNickname()).contains(nickname);
        }
    }
    @Test
    public void 포트폴리오_READ_T(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String title = appendRandomNumberToName("testTitle");

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .title(title)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i).getTitle()).contains(title);
        }
    }
    @Test
    public void 포트폴리오_READ_C(){ // N : 닉네임 T : 타이틀 C : 카테고리
        List<CategoryType> categoryTypes = categoryListReturner();

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(categoryTypes.contains(result.getContent().get(i).getCategory())).isTrue();
        }
    }

    @Test
    public void 포트폴리오_READ_(){ // N : 닉네임 T : 타이틀 C : 카테고리
        String nickname = appendRandomNumberToName("testNickname");
        String title = appendRandomNumberToName("testTitle");
        List<CategoryType> categoryTypes = categoryListReturner();

        PortfolioReadRequestDTO dto = PortfolioReadRequestDTO.builder()
                .nickname(nickname)
                .title(title)
                .categories(categoryTypes)
                .pageNum(0)
                .limit(100)
                .sortField("regDate")
                .sortType("DESC")
                .build();

        Page<PortfolioReadResponseDTO> result = portfolioService.pageReadPortfolio(dto);
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

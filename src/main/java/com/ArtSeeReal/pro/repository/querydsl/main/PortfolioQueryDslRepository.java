package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import java.util.List;

public interface PortfolioQueryDslRepository {

    List<PortfolioWithUserDTO> findByUserAndPortfolioOrderByRegDateDesc(PortfolioReadRequestDTO dto);
    PortfolioWithUserDTO findUserAndPortfolioByUid(String uid);

}

package com.ArtSeeReal.pro.repository.querydsl.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadRequestDTO;
import com.ArtSeeReal.pro.dto.with.PortfolioWithUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PortfolioQueryDslRepository {

    Page<PortfolioWithUserDTO> findByUserAndPortfolioOrderByRegDateDesc(PortfolioReadRequestDTO dto);
    PortfolioWithUserDTO findUserAndPortfolioByUid(String uid);

}

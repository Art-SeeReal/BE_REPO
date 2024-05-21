package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.EtcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtcServiceImpl implements EtcService {

    @Override
    public List<RegionType> regionCodeRead() {
        return List.of(RegionType.values());
    }

    @Override
    public List<CategoryType> categoryCodeRead() {
        return List.of(CategoryType.values());
    }

    @Override
    public List<UserType> userCodeRead() {
        return List.of(UserType.values());
    }
}

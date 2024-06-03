package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import com.ArtSeeReal.pro.enums.enuminfo.UserInfo;
import com.ArtSeeReal.pro.service.EtcService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EtcServiceImpl implements EtcService {

    @Override
    public List<RegionInfo> regionCodeRead() {
        return Stream.of(RegionType.values())
                .map(RegionType::toRegionInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryInfo> categoryCodeRead() {
        return Stream.of(CategoryType.values())
                .map(CategoryType::toCategoryInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInfo> userCodeRead() {
        return Stream.of(UserType.values())
                .map(UserType::toUserInfo)
                .collect(Collectors.toList());
    }
}

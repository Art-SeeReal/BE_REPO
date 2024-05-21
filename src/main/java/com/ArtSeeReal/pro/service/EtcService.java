package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;

import java.util.List;

public interface EtcService {
    List<RegionType> regionCodeRead();
    List<CategoryType> categoryCodeRead();
    List<UserType> userCodeRead();
}

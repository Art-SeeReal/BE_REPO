package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import com.ArtSeeReal.pro.enums.enuminfo.UserInfo;

import java.util.List;

public interface EtcService {
    List<RegionInfo> regionCodeRead();
    List<CategoryInfo> categoryCodeRead();
    List<UserInfo> userCodeRead();
}

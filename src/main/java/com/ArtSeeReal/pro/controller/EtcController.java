package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import com.ArtSeeReal.pro.enums.enuminfo.UserInfo;
import com.ArtSeeReal.pro.service.EtcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EtcController {
    private final EtcService etcService;

    @GetMapping("/regions")
    public ResponseEntity<List<RegionInfo>> readRegions(){
        return new ResponseEntity<>(etcService.regionCodeRead(), HttpStatus.OK);
    }

    @GetMapping("/fields")
    public ResponseEntity<List<CategoryInfo>> readCategories(){
        return new ResponseEntity<>(etcService.categoryCodeRead(), HttpStatus.OK);
    }

    @GetMapping("/user/types")
    public ResponseEntity<List<UserInfo>> readUserTypes(){
        return new ResponseEntity<>(etcService.userCodeRead(), HttpStatus.OK);
    }
}

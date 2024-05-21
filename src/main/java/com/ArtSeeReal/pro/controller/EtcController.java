package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
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
    public ResponseEntity<List<RegionType>> readRegions(){
        return new ResponseEntity<>(etcService.regionCodeRead(), HttpStatus.OK);
    }

    @GetMapping("/fields")
    public ResponseEntity<List<CategoryType>> readCategories(){
        return new ResponseEntity<>(etcService.categoryCodeRead(), HttpStatus.OK);
    }

    @GetMapping("/user/types")
    public ResponseEntity<List<UserType>> readUserTypes(){
        return new ResponseEntity<>(etcService.userCodeRead(), HttpStatus.OK);
    }
}

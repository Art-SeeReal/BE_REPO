package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerUpdateRequestDTO;
import com.ArtSeeReal.pro.service.BannerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Tag(name = "Banner API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    @PostMapping
    public ResponseEntity<BannerCreateResponseDTO> createBanner(@RequestBody BannerCreateRequestDTO dto)
            throws IOException {
        return new ResponseEntity<>(bannerService.createBanner(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BannerReadResponseDTO> readBanner(@RequestParam String uid){
        return new ResponseEntity<>(bannerService.readBanner(uid),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<BannerReadResponseDTO> updateBanner(@RequestBody BannerUpdateRequestDTO dto)
            throws IOException {
        return new ResponseEntity<>(bannerService.updateBanner(dto),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBanner(@RequestParam String uid){
        return new ResponseEntity<>(bannerService.deleteBanner(uid),HttpStatus.OK);
    }

    @GetMapping("/main-banners")
    public ResponseEntity<List<BannerReadResponseDTO>> bannerLists(){
        return new ResponseEntity<>(bannerService.bannerList(),HttpStatus.OK);
    }
}

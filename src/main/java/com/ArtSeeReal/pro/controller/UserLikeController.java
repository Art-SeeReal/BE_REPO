package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.dto.response.userlike.UserLikesAuthorDTO;
import com.ArtSeeReal.pro.dto.response.userlike.UserLikesPlannerDTO;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/like")
public class UserLikeController {

    private final TokenService tokenService;
    private final UserLikesService userLikesService;

    @GetMapping("/author")
    public ResponseEntity<UserLikesAuthorDTO> userLikeAuthor(@RequestHeader("Authorization") String header){
        String userUid = tokenService.getUserUid(header);
        return new ResponseEntity<>(userLikesService.userLikesAuthor(userUid), HttpStatus.OK);
    }

    @GetMapping("/planner")
    public ResponseEntity<UserLikesPlannerDTO> userLikePlanner(@RequestHeader("Authorization") String header){
        String userUid = tokenService.getUserUid(header);
        return new ResponseEntity<>(userLikesService.userLikesPlanner(userUid), HttpStatus.OK);
    }
}

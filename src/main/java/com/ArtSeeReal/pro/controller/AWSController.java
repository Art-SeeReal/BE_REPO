package com.ArtSeeReal.pro.controller;

import com.ArtSeeReal.pro.service.AwsService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/image")
@RestController
public class AWSController {

    private final AwsService awsService;

    @GetMapping("/presigned-url/{filename}")
    public ResponseEntity<String> getFile(@PathVariable(value = "filename") String fileName) throws IOException {
        return new ResponseEntity<>(awsService.createPresignedUrl(fileName), HttpStatus.OK);
    }

}

package com.ArtSeeReal.pro.awsTests;

import com.ArtSeeReal.pro.service.AwsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CreatePresignTest {

    private final AwsService awsService;
    @Autowired
    public CreatePresignTest(AwsService awsService) {
        this.awsService = awsService;
    }

    @Test
    void 프리사인_발급(){
        String filename = "testFileName";
        String url = awsService.createPresignedUrl(filename);
        System.out.println("URL : " + url);
    }
}

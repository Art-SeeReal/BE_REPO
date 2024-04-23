package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.service.AwsService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@RequiredArgsConstructor
@Service
public class AwsServiceImpl implements AwsService {
    private final S3Presigner s3Presigner;
    private final String bucketName = System.getenv("AMAZON_S3_BUCKET");

    public String createPresignedUrl(String filename) {
        if(filename == null || filename.equals("")) {
            return null;
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(5)) // presignedURL 5분간 접근 허용
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner
                .presignGetObject(getObjectPresignRequest);

        String url = presignedGetObjectRequest.url().toString();

        s3Presigner.close(); // presigner를 닫고 획득한 모든 리소스를 해제
        return url;
    }
}

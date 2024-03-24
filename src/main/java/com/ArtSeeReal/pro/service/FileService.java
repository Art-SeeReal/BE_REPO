package com.ArtSeeReal.pro.service;

import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

import com.ArtSeeReal.pro.entity.main.File;
import com.ArtSeeReal.pro.etc.ImageUtils;
import com.ArtSeeReal.pro.repository.jpa.main.FileRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileService {

    private final FileRepository fileRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        log.info("upload file: {}", file);

        File saveFile = File.builder()
                .uid(uidCreator(fileRepository))
                .targetUid("temp")
                .imageData(file.getBytes())
                .build();

        File imageData = fileRepository.save(saveFile);
        if (imageData != null) {
            log.info("imageData: {}", imageData);
            return "file uploaded successfully : " + file.getOriginalFilename();
        }

        return null;
    }

    // 이미지 파일로 압축하기
    public byte[] downloadImage(String fileName) {
        File imageData = fileRepository.findById(fileName)
                .orElseThrow(() -> new IllegalArgumentException());

        log.info("download imageData: {}", imageData);

        return ImageUtils.decompressImage(imageData.getImageData());
    }
}

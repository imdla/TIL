package com.example.relation.global.common.service;

import com.example.relation.global.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public String saveFile(MultipartFile image) {
        try {
            // 현재 프로젝트 디렉토리
            String projectDir = Paths.get("").toAbsolutePath().toString();

            // 디렉토리 관련 객체 생성
            File directory = new File(projectDir, fileDir);

            // 디렉토리가 없으면 생성
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 원본 파일명
            String originalName = image.getOriginalFilename();

            // 저장할 파일명 (UUID + 원본파일명)
            String savedFileName = UUID.randomUUID() + "_" + originalName;

            // 파일 저장
            image.transferTo(new File(directory, savedFileName));

            return savedFileName;


        } catch (IOException e) {
            throw new FileUploadException();
        }
    }
}
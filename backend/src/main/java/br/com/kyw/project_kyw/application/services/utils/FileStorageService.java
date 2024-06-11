package br.com.kyw.project_kyw.application.services.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    String storageFile(MultipartFile file, String type);
}

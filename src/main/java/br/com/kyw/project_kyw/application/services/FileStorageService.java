package br.com.kyw.project_kyw.application.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    Path storageFile(MultipartFile file);
}
package br.com.kyw.project_kyw.application.services.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {
    String storageFile(MultipartFile file, String type);
    //String storageFiles(List<MultipartFile> files, String type);
}

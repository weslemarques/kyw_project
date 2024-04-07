package br.com.kyw.project_kyw.infra.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

}

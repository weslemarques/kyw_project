package br.com.kyw.project_kyw.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileStorageConfig {

    private String imageProject;
    private String imageUserAvatar;
    private String attachments;

}

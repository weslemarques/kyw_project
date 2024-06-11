package br.com.kyw.project_kyw.infra.sender;

import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.infra.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final FileStorageConfig fileStorageConfig;
    @Value("${app.base.path}")
    private String baseUrlApp;
    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;

    }
    public String storageFile(MultipartFile file, String type){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path fileStorageLocation = getFileStorageLocation(type);
        try {
            if(fileName.contains("..")){
                throw new RuntimeException("errr ao armazenar o arquivo, contem invalida path");
            }
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return targetLocation.toUri().toString().replace("file:///D:/",baseUrlApp);
        }catch (Exception e){
            throw new RuntimeException("errr ao armazenar o arquivo", e);
        }
    }

    private Path getFileStorageLocation(String type){

        try {
            if(type.equals("project")){
                var path = fileStorageConfig.getImageProject();
                Path fileStorageLocation = Paths.get(path)
                        .toAbsolutePath().normalize();
                Files.createDirectories(fileStorageLocation);
                return fileStorageLocation;
            }
                var path = fileStorageConfig.getImageUserAvatar();
                Path fileStorageLocation = Paths.get(path)
                        .toAbsolutePath().normalize();
                Files.createDirectories(fileStorageLocation);
                return fileStorageLocation;

        }catch (Exception e){
            throw new RuntimeException("erro ao criar o diretorio", e);
        }
    }

}

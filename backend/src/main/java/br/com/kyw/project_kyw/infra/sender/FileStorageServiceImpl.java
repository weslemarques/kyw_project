package br.com.kyw.project_kyw.infra.sender;

import br.com.kyw.project_kyw.application.services.utils.FileStorageService;
import br.com.kyw.project_kyw.infra.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Path fileStorageLocation;
    
    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        try {
            this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                    .toAbsolutePath().normalize();
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new RuntimeException("erro ao criar o diretorio", e);
        }
    }
    public Path storageFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(fileName.contains("..")){
                throw new RuntimeException("errr ao armazenar o arquivo, contem invalida path");
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return  targetLocation;
        }catch (Exception e){
            throw new RuntimeException("errr ao armazenar o arquivo", e);
        }
    }

}

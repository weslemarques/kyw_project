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
import java.util.List;
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
        if(file.isEmpty()){
            return null;
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path fileStorageLocation = getFileStorageLocation(type);
        return getString(file, fileName, fileStorageLocation);
    }

    private String getString(MultipartFile file, String fileName, Path fileStorageLocation) {
        try {
            if(fileName.contains("..")){
                throw new RuntimeException("errr ao armazenar o arquivo, contem invalida path");
            }
            Path targetLocation = fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            var urlImage = targetLocation.toUri().toString();
            int indice = urlImage.indexOf("/files");
            String conteudoAntes = urlImage.substring(0, indice);
            return urlImage.replace(conteudoAntes,baseUrlApp);
        }catch (Exception e){
            throw new RuntimeException("errr ao armazenar o arquivo", e);
        }
    }

//    public String storageFiles(List<MultipartFile> files, String type){
//        if(files.isEmpty()){
//            return null;
//        }
//        files.stream().map(file -> {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Path fileStorageLocation = this.getFileStorageLocation(type);
//            return getString(file, fileName, fileStorageLocation);
//        });
//        return null;
//    }
    private Path getFileStorageLocation(String type){

        try {
            if(type.equals("project")){
                var path = fileStorageConfig.getImageProject();
                Path fileStorageLocation = Paths.get(path)
                        .toAbsolutePath().normalize();
                Files.createDirectories(fileStorageLocation);
                return fileStorageLocation;
            }
            if(type.equals("attachments")){
                var path = fileStorageConfig.getAttachments();
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

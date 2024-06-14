package br.com.kyw.project_kyw.adapters.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${file.image-user-avatar}")
    private String uploadDirAvatar;

    @Value("${file.image-project}")
    private String uploadDirProject;

    @Value("${file.attachments}")
    private String uploadDirAttachments;



    @GetMapping("/images/projects/{filename:.+}")
    public ResponseEntity<Resource> getImageProject(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDirProject).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // Ajuste o tipo conforme necessário

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/images/avatars/{filename:.+}")
    public ResponseEntity<Resource> getImageAvatar(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDirAvatar).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // Ajuste o tipo conforme necessário

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/attachments/{filename:.+}")
    public ResponseEntity<Resource> getAttachment(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDirAttachments).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                // Determine the content type
                String contentType = null;
                try {
                    contentType = Files.probeContentType(file);
                } catch (Exception e) {
                    // Unable to determine content type, set to generic binary
                    contentType = "application/octet-stream";
                }

                // Set content type based on file extension if probeContentType failed
                if (contentType == null) {
                    if (filename.endsWith(".pdf")) {
                        contentType = "application/pdf";
                    } else if (filename.endsWith(".png")) {
                        contentType = "image/png";
                    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
                        contentType = "image/jpeg";
                    } else {
                        contentType = "application/octet-stream";
                    }
                }

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, contentType);

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


package br.com.kyw.project_kyw.adapters.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageDTO implements Serializable {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}

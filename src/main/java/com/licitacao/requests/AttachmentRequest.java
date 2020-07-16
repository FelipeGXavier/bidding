package com.licitacao.requests;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AttachmentRequest {

    @Size(min = 4)
    private String name;
    @NotNull
    private String url;
    private Long biddingId;
    private MultipartFile file;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(Long biddingId) {
        this.biddingId = biddingId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

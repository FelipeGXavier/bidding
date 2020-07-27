package com.licitacao.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.licitacao.core.StorageType;
import com.licitacao.core.UploadStorageInterface;
import com.licitacao.responses.DropboxSharedLinkPayload;
import com.licitacao.responses.DropboxUploadPayload;
import com.licitacao.requests.DropboxApiArgPayload;
import com.licitacao.requests.DropboxSettingsDetails;
import com.licitacao.requests.DropboxSettingsPayload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UploadFileToDropbox implements UploadStorageInterface {


    private RestTemplate client;

    @Autowired
    private Environment env;

    public UploadFileToDropbox() {
        this.client = new RestTemplate();
    }

    public HashMap<String, String> uploadStorage(MultiValueMap<String, MultipartFile> body) throws Exception {
        HashMap<String, String> uploadedFiles = new HashMap<>();
        for (Map.Entry<String, List<MultipartFile>> entry : body.entrySet()) {
            String path = this.uploadFile(entry.getValue().get(0), entry.getKey());
            String sharedPath = this.getSharedLink(path);
            uploadedFiles.put(entry.getKey(), sharedPath);
        }
        return uploadedFiles;
    }

    @Override
    public StorageType getStrategyName() {
        return StorageType.DROPBOX;
    }

    private String uploadFile(MultipartFile file, String name) throws IOException {
        DropboxApiArgPayload dropboxApiArgPayload = this.getPayloadUpload(file, name);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.env.getProperty("app.dropbox.env"));
        headers.set("Dropbox-API-Arg", this.mapObjectToJsonPayload(dropboxApiArgPayload));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);
        ResponseEntity<?> responseEntity = this.client.postForEntity("https://content.dropboxapi.com/2/files/upload", entity, DropboxUploadPayload.class);
        DropboxUploadPayload payload = (DropboxUploadPayload) responseEntity.getBody();
        if (payload == null || responseEntity.getStatusCode() != HttpStatus.OK || !payload.isDownloadable()) {
            throw new RuntimeException("");
        }
        return dropboxApiArgPayload.getPath();
    }


    private String getSharedLink(String path) throws JsonProcessingException {
        DropboxSettingsPayload dropboxSettings = this.getPayloadSharedLink(path);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.env.getProperty("app.dropbox.env"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(this.mapObjectToJsonPayload(dropboxSettings), headers);
        ResponseEntity<?> responseEntity = this.client.postForEntity("https://api.dropboxapi.com/2/sharing/create_shared_link_with_settings", entity, DropboxSharedLinkPayload.class);
        DropboxSharedLinkPayload payload = (DropboxSharedLinkPayload) responseEntity.getBody();
        if (payload == null || responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("");
        }
        return payload.getUrl();
    }


    private String mapObjectToJsonPayload(Object var1) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(var1);
    }

    private DropboxApiArgPayload getPayloadUpload(MultipartFile file, String name) {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        final String path = "/Apps/spring_bidding/" + name + "_" + UUID.randomUUID()
                .toString()
                .replace("-", "_")
                .substring(0, 6) + "." + ext;
        DropboxApiArgPayload dropboxApiArgPayload = new DropboxApiArgPayload();
        return dropboxApiArgPayload.setAutorename(true)
                .setMode("add")
                .setMute(false)
                .setPath(path)
                .setStrict_conflict(false);
    }

    private DropboxSettingsPayload getPayloadSharedLink(String path) {
        DropboxSettingsPayload dropboxSettings = new DropboxSettingsPayload();
        return dropboxSettings.setPath(path)
                .setSettings(new DropboxSettingsDetails().setAccess("viewer")
                        .setAudience("public")
                        .setRequestVisibility("public"));
    }


}

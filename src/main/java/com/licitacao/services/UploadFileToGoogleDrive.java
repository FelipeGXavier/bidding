package com.licitacao.services;

import com.licitacao.core.StorageType;
import com.licitacao.core.UploadStorageInterface;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service
public class UploadFileToGoogleDrive implements UploadStorageInterface {
    @Override
    public HashMap<String, String> uploadStorage(MultiValueMap<String, MultipartFile> body) throws Exception {
        return null;
    }

    @Override
    public StorageType getStrategyName() {
        return StorageType.GOOGLEDRIVE;
    }
}

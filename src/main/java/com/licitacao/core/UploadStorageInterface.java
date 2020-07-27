package com.licitacao.core;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface UploadStorageInterface {

    HashMap<String, String> uploadStorage(MultiValueMap<String, MultipartFile> body) throws Exception;

    StorageType getStrategyName();
}

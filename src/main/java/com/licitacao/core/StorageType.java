package com.licitacao.core;

import java.util.Arrays;

public enum StorageType {

    DROPBOX("box"), GOOGLEDRIVE("drive");

    private String type;

    StorageType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static StorageType find(String storage){
        return Arrays.stream(values())
                .filter(storageType -> storageType.type.equals(storage))
                .findFirst().orElse(StorageType.DROPBOX);
    }
}

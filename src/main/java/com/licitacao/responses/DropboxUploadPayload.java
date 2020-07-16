package com.licitacao.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DropboxUploadPayload implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("path_lower")
    private String pathLower;
    @JsonProperty("path_display")
    private String pathDisplay;
    @JsonProperty("id")
    private String id;
    @JsonProperty("client_modified")
    private String clientModified;
    @JsonProperty("server_modified")
    private String serverModified;
    @JsonProperty("rev")
    private String rev;
    @JsonProperty("size")
    private int size;
    @JsonProperty("is_downloadable")
    private boolean isDownloadable;
    @JsonProperty("content_hash")
    private String contentHash;

    public String getName() {
        return name;
    }

    public String getPathLower() {
        return pathLower;
    }

    public String getPathDisplay() {
        return pathDisplay;
    }

    public String getId() {
        return id;
    }

    public String getClientModified() {
        return clientModified;
    }

    public String getServerModified() {
        return serverModified;
    }

    public String getRev() {
        return rev;
    }

    public int getSize() {
        return size;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public String getContentHash() {
        return contentHash;
    }
}

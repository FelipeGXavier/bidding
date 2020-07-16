package com.licitacao.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxSharedLinkPayload {

    @JsonProperty("url")
    private String url;
    @JsonProperty("allow_download")
    private boolean allowDownload;

    public String getUrl() {
        return url;
    }

    public boolean isAllowDownload() {
        return allowDownload;
    }
}

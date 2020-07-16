package com.licitacao.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxSettingsPayload {

    @JsonProperty("path")
    private String path;
    @JsonProperty("settings")
    private DropboxSettingsDetails settings;

    public String getPath() {
        return path;
    }

    public DropboxSettingsPayload setPath(String path) {
        this.path = path;
        return this;
    }

    public DropboxSettingsDetails getSettings() {
        return settings;
    }

    public DropboxSettingsPayload setSettings(DropboxSettingsDetails settings) {
        this.settings = settings;
        return this;
    }
}

package com.licitacao.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxSettingsDetails {

    @JsonProperty("requested_visibility")
    private String requestVisibility;
    @JsonProperty("audience")
    private String audience;
    @JsonProperty("access")
    private String access;


    public String getRequestVisibility() {
        return requestVisibility;
    }

    public DropboxSettingsDetails setRequestVisibility(String requestVisibility) {
        this.requestVisibility = requestVisibility;
        return this;
    }

    public String getAudience() {
        return audience;
    }

    public DropboxSettingsDetails setAudience(String audience) {
        this.audience = audience;
        return this;
    }

    public String getAccess() {
        return access;
    }

    public DropboxSettingsDetails setAccess(String access) {
        this.access = access;
        return this;
    }


}

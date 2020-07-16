package com.licitacao.requests;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DropboxApiArgPayload {

    private String path;
    private String mode;
    private boolean autorename;
    private boolean mute;
    private boolean strict_conflict;

    public String getPath() {
        return path;
    }

    public DropboxApiArgPayload setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public DropboxApiArgPayload setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public boolean isAutorename() {
        return autorename;
    }

    public DropboxApiArgPayload setAutorename(boolean autorename) {
        this.autorename = autorename;
        return this;
    }

    public boolean isMute() {
        return mute;
    }

    public DropboxApiArgPayload setMute(boolean mute) {
        this.mute = mute;
        return this;
    }

    public boolean isStrict_conflict() {
        return strict_conflict;
    }

    public DropboxApiArgPayload setStrict_conflict(boolean strict_conflict) {
        this.strict_conflict = strict_conflict;
        return this;
    }


}

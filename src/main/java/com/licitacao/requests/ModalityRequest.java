package com.licitacao.requests;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModalityRequest {

    @NotNull
    @Size(min = 3)
    @JsonProperty("description")
    private String description;

    public ModalityRequest(@NotNull @Min(4) String description) {
        this.description = description;
    }

    public ModalityRequest(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

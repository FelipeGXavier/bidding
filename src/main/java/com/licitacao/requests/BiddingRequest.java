package com.licitacao.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.licitacao.domain.Bidding;
import com.licitacao.domain.Modality;

import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class BiddingRequest {

    private LocalDateTime publicationDate;
    @NotNull
    private LocalDateTime openingDate;
    @NotNull
    @Size(min = 3)
    private String object;
    @OneToOne
    @NotNull
    private Long modalityId;
    @NotNull
    @Size(min = 2)
    private String order;
    @NotNull
    @Size(min = 4)
    private String organName;

    public Bidding toModel(){
        Bidding bidding = new Bidding();
        return bidding.setPublicationDate(LocalDateTime.now())
                .setOpeningDate(this.openingDate)
                .setObject(this.object)
                .setModality(new Modality().setId(this.modalityId))
                .setBiddingNumber(this.order)
                .setOrganName(this.organName);
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getModalityId() {
        return modalityId;
    }

    public void setModalityId(Long modalityId) {
        this.modalityId = modalityId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }
}

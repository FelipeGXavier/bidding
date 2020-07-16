package com.licitacao.responses;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BiddingDetails {

    private String number;
    private String object;
    private String openingDate;
    private String publicationDate;
    private String organName;
    private String modality;

    private List<AttachmentDetails> attachmenList;
    private List<LotDetails> lotList;

    public BiddingDetails(){
        this.attachmenList = new ArrayList<>();
        this.lotList = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public List<AttachmentDetails> getAttachmenList() {
        return attachmenList;
    }

    public void setAttachmenList(List<AttachmentDetails> attachmenList) {
        this.attachmenList = attachmenList;
    }

    public List<LotDetails> getLotList() {
        return lotList;
    }

    public void setLotList(List<LotDetails> lotList) {
        this.lotList = lotList;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}

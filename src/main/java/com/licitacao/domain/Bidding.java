package com.licitacao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"bidding_number", "modality_id"})
})
public class Bidding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime publicationDate;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime openingDate;
    @NotNull
    private String object;
    @OneToOne
    private Modality modality;
    @NotNull
    @Column(name = "bidding_number")
    private String biddingNumber;
    @NotNull
    private String organName;


    public Bidding(){

    }

    public Long getId() {
        return id;
    }

    public Bidding setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public Bidding setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public Bidding setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public String getObject() {
        return object;
    }

    public Bidding setObject(String object) {
        this.object = object;
        return this;
    }

    public Modality getModality() {
        return modality;
    }

    public Bidding setModality(Modality modality) {
        this.modality = modality;
        return this;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public Bidding setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
        return this;
    }

    public String getOrganName() {
        return organName;
    }

    public Bidding setOrganName(String organName) {
        this.organName = organName;
        return this;
    }
}

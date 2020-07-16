package com.licitacao.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Modality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String description;


    public Modality(Long id, @NotNull String description) {
        this.id = id;
        this.description = description;
    }

    public Modality(){

    }

    public Long getId() {
        return id;
    }

    public Modality setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Modality setDescription(String description) {
        this.description = description;
        return this;
    }
}

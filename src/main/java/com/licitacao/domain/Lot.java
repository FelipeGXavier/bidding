package com.licitacao.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "code"})
})
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @ManyToOne
    private Bidding bidding;

    public Long getId() {
        return id;
    }

    public Lot setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Lot setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Lot setCode(String code) {
        this.code = code;
        return this;
    }

    public Bidding getBidding() {
        return bidding;
    }

    public Lot setBidding(Bidding bidding) {
        this.bidding = bidding;
        return this;
    }
}

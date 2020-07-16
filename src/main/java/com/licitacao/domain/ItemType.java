package com.licitacao.domain;

public enum ItemType {

    MATERIAL(1), SERVICE(2);

    public int value;

    ItemType(int value) {
        this.value = value;
    }
}

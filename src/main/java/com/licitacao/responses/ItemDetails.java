package com.licitacao.responses;

public class ItemDetails {

    private String description;
    private int quantity;
    private int type;
    private String unity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}

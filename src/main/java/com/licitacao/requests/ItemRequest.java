package com.licitacao.requests;

import com.licitacao.domain.ItemType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemRequest {

    @NotNull
    @Size(min = 3)
    private String description;
    @NotNull
    private int quantity;
    @NotNull
    private ItemType type;
    @NotNull
    @Size(min = 2)
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}

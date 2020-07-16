package com.licitacao.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class LotRequest {

    @NotNull
    @Size(min = 3)
    private String code;
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    private Long biddingId;
    @NotNull
    private List<ItemRequest> items;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public Long getBiddingId() {
        return biddingId;
    }
}

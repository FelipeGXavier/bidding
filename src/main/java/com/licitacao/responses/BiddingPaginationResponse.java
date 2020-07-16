package com.licitacao.responses;

import com.licitacao.domain.Bidding;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BiddingPaginationResponse {

    private Collection<?> content;
    private int totalPages;
    private Long totalRecords;

    public BiddingPaginationResponse toResponse(Page<Bidding> page){
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalRecords = page.getTotalElements();
        return this;
    }

    public Collection<?> getContent() {
        return content;
    }

    public void setContent(Collection<?> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}

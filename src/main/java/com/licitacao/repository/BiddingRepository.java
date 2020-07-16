package com.licitacao.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.licitacao.domain.Bidding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface BiddingRepository extends JpaRepository<Bidding, Long> {


    @Query(value = "select b from Bidding b")
    Page<Bidding> findPageableBidding(Pageable pageable);

}

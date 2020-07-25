package com.licitacao.repository;

import com.licitacao.domain.Bidding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface BiddingRepository extends JpaRepository<Bidding, Long> {


    @Query(value = "select b from Bidding b")
    Page<Bidding> findPageableBidding(Pageable pageable);

    @Query(value = "from Bidding b where b.openingDate BETWEEN :initialDate AND :finalDate and b.modality.description = :name")
    Page<Bidding> findPageableQueryWithModality(@Param("initialDate") LocalDateTime initialDate, @Param("finalDate") LocalDateTime finalDate, @Param("name") String name ,Pageable pageable);

    @Query(value = "from Bidding b where b.openingDate BETWEEN :initialDate AND :finalDate")
    Page<Bidding> findPageableQuery(@Param("initialDate") LocalDateTime initialDate, @Param("finalDate") LocalDateTime finalDate ,Pageable pageable);

}

package com.licitacao.repository;

import com.licitacao.domain.Lot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LotRepository extends CrudRepository<Lot, Long> {

    @Query(value = "select l.name, l.code, it.description, it.quantity, it.unity, it.type from lot l" +
            " inner join item it on l.id = it.lot_id where l.bidding_id = :id and l.id = :lid", nativeQuery = true)
    List<LotItemsRowsInterface> findLotItemsByBiddingId(Long id, Long lid);

    @Query(value = "select l.name, l.code, l.id from lot l " +
            "inner join item it on l.id = it.lot_id where l.bidding_id = :id " +
            "group by l.id", nativeQuery = true)
    List<LotRowsInterface> findLotsByBiddingId(Long id);

}

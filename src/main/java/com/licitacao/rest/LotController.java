package com.licitacao.rest;


import com.licitacao.domain.Bidding;
import com.licitacao.domain.Item;
import com.licitacao.domain.Lot;
import com.licitacao.repository.ItemRepository;
import com.licitacao.repository.LotRepository;
import com.licitacao.requests.LotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/lot")
public class LotController {

    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping(value = "")
    public void saveLotWithItems(@RequestBody List<LotRequest> lotRequestList){
        for (LotRequest lot : lotRequestList ) {
            Lot lotEntity = new Lot();
            lotEntity.setName(lot.getName())
                    .setCode(lot.getCode())
                    .setBidding(new Bidding().setId(lot.getBiddingId()));
        this.lotRepository.save(lotEntity);
        List<Item> items = lot.getItems().stream().map(item -> {
            Item newItem = new Item();
            newItem.setDescription(item.getDescription());
            newItem.setQuantity(item.getQuantity());
            newItem.setType(item.getType());
            newItem.setUnity(item.getUnity());
            newItem.setLot(lotEntity);
            return newItem;
        }).collect(Collectors.toList());
        this.itemRepository.saveAll(items);
        }
    }
}

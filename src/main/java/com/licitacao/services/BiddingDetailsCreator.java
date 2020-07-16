package com.licitacao.services;

import com.licitacao.core.Util;
import com.licitacao.domain.Bidding;
import com.licitacao.repository.*;
import com.licitacao.responses.AttachmentDetails;
import com.licitacao.responses.BiddingDetails;
import com.licitacao.responses.ItemDetails;
import com.licitacao.responses.LotDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BiddingDetailsCreator {

    @Autowired
    private BiddingRepository biddingRepository;
    @Autowired
    private ModalityRepository modalityRepository;
    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Optional<BiddingDetails> buildBiddingDetails(Long id){
        BiddingDetails biddingDetails = new BiddingDetails();
        Optional<Bidding> biddingOptional = this.biddingRepository.findById(id);
        if(biddingOptional.isPresent()){
            Bidding bidding = biddingOptional.get();
            this.setBiddingDetails(biddingDetails, bidding);
            this.setLotItems(biddingDetails, id);
            this.setAttachments(biddingDetails, id);
            return Optional.of(biddingDetails);
        }
        return Optional.empty();
    }

    private BiddingDetails setBiddingDetails(BiddingDetails biddingDetails, Bidding bidding){
        biddingDetails.setModality(bidding.getModality().getDescription());
        biddingDetails.setOrganName(bidding.getOrganName());
        biddingDetails.setObject(bidding.getObject());
        biddingDetails.setNumber(bidding.getBiddingNumber());
        biddingDetails.setOpeningDate(Util.formatTimestamp(bidding.getOpeningDate()));
        biddingDetails.setPublicationDate(Util.formatTimestamp(bidding.getPublicationDate()));
        return biddingDetails;
    }

    private BiddingDetails setLotItems(BiddingDetails biddingDetails,  Long id){
        List<LotRowsInterface> lots = this.lotRepository.findLotsByBiddingId(id);
        biddingDetails.setLotList(lots.stream().map(lot -> {
            LotDetails lotDetails = new LotDetails();
            lotDetails.setCode(lot.getCode());
            lotDetails.setName(lot.getName());
            lotDetails.setItems(this.lotRepository.findLotItemsByBiddingId(id, (long) lot.getId()).stream().map(lotItem -> {
                ItemDetails itemDetails = new ItemDetails();
                itemDetails.setDescription(lotItem.getDescription());
                itemDetails.setQuantity(lotItem.getQuantity());
                itemDetails.setUnity(lotItem.getUnity());
                itemDetails.setType(lotItem.getType());
                return itemDetails;
            }).collect(Collectors.toList()));
            return lotDetails;
        }).collect(Collectors.toList()));
        return biddingDetails;
    }

    private BiddingDetails setAttachments(BiddingDetails biddingDetails, Long id){
        biddingDetails.setAttachmenList(this.attachmentRepository.findAttachmentByBiddingId(id).stream().map(attachment -> {
            AttachmentDetails attachmentDetails = new AttachmentDetails();
            attachmentDetails.setName(attachment.getName());
            attachmentDetails.setUrl(attachment.getUrl());
            return attachmentDetails;
        }).collect(Collectors.toList()));
        return biddingDetails;
    }


}

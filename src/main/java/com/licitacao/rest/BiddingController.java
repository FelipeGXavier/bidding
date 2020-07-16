package com.licitacao.rest;


import com.licitacao.core.CustomExceptionMessage;
import com.licitacao.core.Util;
import com.licitacao.domain.Bidding;
import com.licitacao.domain.Modality;
import com.licitacao.repository.*;
import com.licitacao.requests.BiddingRequest;
import com.licitacao.responses.*;
import com.licitacao.services.BiddingDetailsCreator;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/bidding")
public class BiddingController {

    @Autowired
    private BiddingRepository biddingRepository;
    @Autowired
    private ModalityRepository modalityRepository;
    @Autowired
    private BiddingDetailsCreator biddingDetailsCreator;
    @Autowired
    private BiddingPaginationResponse biddingPaginationResponse;


    @PostMapping(value = "")
    public ResponseEntity<?> save(@Valid @RequestBody BiddingRequest biddingRequest){
        Optional<Modality> modaliteOptional=  this.modalityRepository.findById(biddingRequest.getModalityId());
        if(modaliteOptional.isPresent()){
            this.biddingRepository.save(biddingRequest.toModel());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CustomExceptionMessage().setMessage("Modalidade não existente").setSuccess(false), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "")
    public ResponseEntity<?> findAll(@RequestParam Map<String, String> params ){
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(this.biddingPaginationResponse.toResponse(this.biddingRepository.findPageableBidding(pageRequest)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        Optional<Bidding> biddingOptional = this.biddingRepository.findById(id);
        if(biddingOptional.isPresent()){
            return ResponseEntity.of(biddingOptional);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/details/{id}")
    public ResponseEntity<?> showDetailsFromBidding(@PathVariable Long id){
        Optional<BiddingDetails> biddingDetails = this.biddingDetailsCreator.buildBiddingDetails(id);
        if(biddingDetails.isPresent()){
            return new ResponseEntity<>(biddingDetails.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleViolationIntegrity(){
        return new ResponseEntity<>(new CustomExceptionMessage().setSuccess(false).setMessage("Licitação já existente"), HttpStatus.BAD_REQUEST);
    }


}

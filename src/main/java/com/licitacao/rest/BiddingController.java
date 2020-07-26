package com.licitacao.rest;


import com.licitacao.core.CustomExceptionMessage;
import com.licitacao.domain.Bidding;
import com.licitacao.domain.Modality;
import com.licitacao.repository.*;
import com.licitacao.requests.BiddingRequest;
import com.licitacao.responses.*;
import com.licitacao.services.BiddingDetailsCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BiddingRequest biddingRequest) {
        Optional<Modality> modalityOptional = this.modalityRepository.findById(biddingRequest.getModalityId());
        if (modalityOptional.isPresent()) {
            this.biddingRepository.save(biddingRequest.toModel());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CustomExceptionMessage().setMessage("Modalidade não existente").setSuccess(false), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(this.biddingPaginationResponse.toResponse(this.biddingRepository.findPageableBidding(pageRequest)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Bidding> biddingOptional = this.biddingRepository.findById(id);
        if (biddingOptional.isPresent()) {
            return ResponseEntity.of(biddingOptional);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/details/{id}")
    public ResponseEntity<?> showDetailsFromBidding(@PathVariable Long id) {
        Optional<BiddingDetails> biddingDetails = this.biddingDetailsCreator.buildBiddingDetails(id);
        if (biddingDetails.isPresent()) {
            return new ResponseEntity<>(biddingDetails.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/")
    public ResponseEntity<?> searchBiddingQuery(
            @RequestParam(name = "initialDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> initialDate,
            @RequestParam(name = "finalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> finalDate,
            @RequestParam(name = "modality", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String modality,
            Pageable pageable) {
        LocalDateTime initialDateQuery = LocalDateTime.now();
        LocalDateTime finalDateQuery = initialDateQuery.with(lastDayOfYear());
        String modalityQuery = "";
        if (initialDate.isPresent()) {
            initialDateQuery = initialDate.get().atStartOfDay();
        }
        if (finalDate.isPresent()) {
            finalDateQuery = finalDate.get().atStartOfDay();
        }
        if (modality != null) {
            modalityQuery = modality;
        }else{
            return new ResponseEntity<>(this.biddingPaginationResponse
                    .toResponse(this.biddingRepository.findPageableQuery(initialDateQuery, finalDateQuery, pageable)), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.biddingPaginationResponse
                .toResponse(this.biddingRepository.findPageableQueryWithModality(initialDateQuery, finalDateQuery, modalityQuery, pageable)), HttpStatus.OK);
    }



}

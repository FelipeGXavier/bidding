package com.licitacao.rest;

import com.licitacao.core.UploadStorageInterface;
import com.licitacao.domain.Attachment;
import com.licitacao.domain.Bidding;
import com.licitacao.repository.AttachmentRepository;
import com.licitacao.repository.BiddingRepository;
import com.licitacao.services.UploadFileToDropbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private BiddingRepository biddingRepository;
    @Autowired
    private UploadStorageInterface storage;

    @PostMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> teste(@RequestParam MultiValueMap<String, MultipartFile> body, @PathVariable Long id) throws Exception {
        if (biddingRepository.findById(id).isPresent()) {
            HashMap<String, String> uploadedPaths = storage.uploadStorage(body);
            List<Attachment> attachmentList = uploadedPaths.entrySet().stream().map(entry -> {
                Attachment attachment = new Attachment();
                attachment.setName(entry.getKey());
                attachment.setUrl(entry.getValue());
                attachment.setBidding(new Bidding().setId(id));
                return attachment;
            }).collect(Collectors.toList());
            this.attachmentRepository.saveAll(attachmentList);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

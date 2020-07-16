package com.licitacao.rest;


import com.licitacao.domain.Attachment;
import com.licitacao.repository.AttachmentRepository;
import com.licitacao.services.UploadFileToDropbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/v1/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UploadFileToDropbox storage;


    @PostMapping(value = "", consumes = "multipart/form-data")
    public void save(@RequestParam("file") MultipartFile file,
                     @RequestParam("name") String name,
                     @RequestParam("bidding_id") Long biddingId) throws IOException {
        Attachment attachment = new Attachment();
        storage.uploadFileAndGetSharedLink(file, name);
        //this.attachmentRepository.save(attachment);
    }
}

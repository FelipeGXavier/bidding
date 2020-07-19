package com.licitacao.rest;

import com.licitacao.domain.Attachment;
import com.licitacao.domain.Bidding;
import com.licitacao.repository.AttachmentRepository;
import com.licitacao.services.UploadFileToDropbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UploadFileToDropbox storage;

    @CrossOrigin
    @PostMapping(value = "/{id}", consumes = "multipart/form-data")
    public void teste(@RequestParam MultiValueMap<String, MultipartFile> body, @PathVariable Long id) throws IOException {
        HashMap<String, String> uploadedPaths = storage.uploadFileAndGetSharedLink(body);
        List<Attachment> attachmentList = uploadedPaths.entrySet().stream().map(entry -> {
            Attachment attachment = new Attachment();
            attachment.setName(entry.getKey());
            attachment.setUrl(entry.getValue());
            attachment.setBidding(new Bidding().setId(id));
            return attachment;
        }).collect(Collectors.toList());
        this.attachmentRepository.saveAll(attachmentList);
    }
}

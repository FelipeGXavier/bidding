package com.licitacao.repository;

import com.licitacao.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> findAttachmentByBiddingId(Long id);

}

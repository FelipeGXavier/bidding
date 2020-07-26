package com.licitacao.rest;


import com.licitacao.core.CustomExceptionMessage;
import com.licitacao.domain.Modality;
import com.licitacao.repository.ModalityRepository;
import com.licitacao.requests.ModalityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/modality")
public class ModalityController {

    @Autowired
    private ModalityRepository modalityRepository;

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody @Valid ModalityRequest modalityValidator){
        Modality modality = new Modality();
        modality.setDescription(modalityValidator.getDescription());
        if(this.modalityRepository.findByDescription(modality.getDescription()).isPresent()){
            return new ResponseEntity<>(new CustomExceptionMessage().setMessage("Modalidade já existente").setSuccess(false), HttpStatus.BAD_REQUEST);
        }
        this.modalityRepository.save(modality);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public Collection<Modality> findAll(){
        return this.modalityRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        this.modalityRepository.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        Optional<Modality> modalityOptional = this.modalityRepository.findById(id);
        if(modalityOptional.isPresent()){
            return new ResponseEntity<>(modalityOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomExceptionMessage().setMessage("Usuário não encontrado").setSuccess(false), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid ModalityRequest modalityValidator){
        this.modalityRepository.updateModality(modalityValidator.getDescription(), id);
    }

}

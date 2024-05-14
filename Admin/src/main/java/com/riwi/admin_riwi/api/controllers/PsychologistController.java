package com.riwi.admin_riwi.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.admin_riwi.api.dto.request.PsychologistReq;
import com.riwi.admin_riwi.api.dto.response.PsychologistResponse;
import com.riwi.admin_riwi.infrastructure.abstract_services.IPsychologistService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/psychologist")
@AllArgsConstructor
public class PsychologistController {
    private final IPsychologistService service;

    @GetMapping
    public ResponseEntity<Page<PsychologistResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(service.getAll(page - 1, size));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<PsychologistResponse>getId(@PathVariable String id){

            return ResponseEntity.ok(service.get(id));
    }
    @PostMapping
    public ResponseEntity<PsychologistResponse> create(@Validated @RequestBody PsychologistReq request){

        return ResponseEntity.ok(service.create(request));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping(path="/{id}")
    public ResponseEntity<PsychologistResponse> put(@PathVariable String id,@Validated @RequestBody PsychologistReq request){

        return ResponseEntity.ok(service.update(request, id));
    }

}

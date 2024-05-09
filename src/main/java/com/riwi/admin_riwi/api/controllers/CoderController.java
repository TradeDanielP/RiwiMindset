package com.riwi.admin_riwi.api.controllers;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.admin_riwi.api.dto.reponse.CoderResponse;
import com.riwi.admin_riwi.api.dto.request.CoderRequest;
import com.riwi.admin_riwi.infrastructure.abastract_services.ICoderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/coder")
@AllArgsConstructor
public class CoderController {
    private final ICoderService service;

    @GetMapping
    public ResponseEntity<Page<CoderResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(this.service.getAll(page-1, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<CoderResponse> getID(@PathVariable String id) {

        return ResponseEntity.ok(this.service.get(id));
    }

    @PostMapping
    public ResponseEntity<CoderResponse> create(@Validated @RequestBody CoderRequest request) {

        return ResponseEntity.ok(this.service.create(request));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CoderResponse> update(@PathVariable String id, @Validated @RequestBody CoderRequest request) {
        return ResponseEntity.ok(this.service.update(request, id));
    }
}

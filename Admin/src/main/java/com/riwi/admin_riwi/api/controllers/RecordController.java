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

import com.riwi.admin_riwi.api.dto.request.RecordRequest;
import com.riwi.admin_riwi.api.dto.response.RecordResponse;
import com.riwi.admin_riwi.infrastructure.abstract_services.IRecordService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/records")
@AllArgsConstructor
public class RecordController {
    
    private final IRecordService recordService;

    @GetMapping
    public ResponseEntity<Page<RecordResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size){

            return ResponseEntity.ok(this.recordService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RecordResponse> get(
        @PathVariable Long id){
            return ResponseEntity.ok(this.recordService.get(id));
    }

    @PostMapping
    public ResponseEntity<RecordResponse> insert(
        @Validated @RequestBody RecordRequest request){
            return ResponseEntity.ok(this.recordService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RecordResponse> update(
        @Validated @RequestBody RecordRequest request,
        @PathVariable Long id){
            return ResponseEntity.ok(this.recordService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.recordService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

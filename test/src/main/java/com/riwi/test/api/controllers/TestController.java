package com.riwi.test.api.controllers;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.test.api.dto.request.TestReq;
import com.riwi.test.api.dto.response.TestResp;
import com.riwi.test.infraestructure.abstract_service.ITestService;
import com.riwi.test.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/test")
@AllArgsConstructor
public class TestController {
    
    @Autowired
    private final ITestService testService;

    @GetMapping
    public ResponseEntity<Page<TestResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
                if (Objects.isNull(sortType)) sortType = SortType.NONE;

                return ResponseEntity.ok(this.testService.getAll(page -1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TestResp> get(
            @PathVariable Integer id) {
        return ResponseEntity.ok(this.testService.get(id));
    }

    @PostMapping
    public ResponseEntity<TestResp> insert(
            @Validated @RequestBody TestReq request) {
        return ResponseEntity.ok(this.testService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TestResp> update(
            @Validated @RequestBody TestReq request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(this.testService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.testService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

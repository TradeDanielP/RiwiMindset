package com.riwi.test.api.controllers;

import java.util.Objects;

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

import com.riwi.test.api.dto.request.TestScoreResq;
import com.riwi.test.api.dto.response.TestScoreResp;
import com.riwi.test.infraestructure.abstract_service.ITestScoreService;
import com.riwi.test.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/testScore")
@AllArgsConstructor
public class TestScoreController {
    private final ITestScoreService testScoreService;

    @GetMapping
    public ResponseEntity<Page<TestScoreResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
            if (Objects.isNull(sortType)) sortType = SortType.NONE;
        return ResponseEntity.ok(this.testScoreService.getAll(page - 1, size, sortType));
    }

    @PostMapping
    public ResponseEntity<TestScoreResp> insert(
            @Validated @RequestBody TestScoreResq request) {
        return ResponseEntity.ok(this.testScoreService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TestScoreResp> update(
            @Validated @RequestBody TestScoreResq request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(this.testScoreService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.testScoreService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

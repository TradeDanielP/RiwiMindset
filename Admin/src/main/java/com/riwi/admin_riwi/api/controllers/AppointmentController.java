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

import com.riwi.admin_riwi.api.dto.request.AppointmentRequest;
import com.riwi.admin_riwi.api.dto.response.AppointmenResponse;
import com.riwi.admin_riwi.infrastructure.abstract_services.IAppointmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/appointments")
@AllArgsConstructor
public class AppointmentController {
    
    private final IAppointmentService appointment;

    @GetMapping
    public ResponseEntity<Page<AppointmenResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(this.appointment.getAll(page - 1, size));
    }

    
    @GetMapping(path = "/{id}")
    public ResponseEntity<AppointmenResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.appointment.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.appointment.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AppointmenResponse> update(
        @Validated @RequestBody AppointmentRequest request,
        @PathVariable Long id
    ){
        return ResponseEntity.ok(this.appointment.update(request, id));
    }

    @PostMapping
    public ResponseEntity<AppointmenResponse> insert(
        @Validated @RequestBody AppointmentRequest request
    ){
        return ResponseEntity.ok(this.appointment.create(request));
    }

}

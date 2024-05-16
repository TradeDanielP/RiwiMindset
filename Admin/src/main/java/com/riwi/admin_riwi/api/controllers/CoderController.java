package com.riwi.admin_riwi.api.controllers;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import com.riwi.admin_riwi.api.dto.request.CoderRequest;
import com.riwi.admin_riwi.api.dto.response.CoderResponse;
import com.riwi.admin_riwi.infrastructure.abstract_services.ICoderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/coders")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Coders")
public class CoderController {
    private final ICoderService service;

    @Operation(summary = "Lista todos los coders dentro de la api ", description = "Se debe enviar la pagina y el tamaño de la pagina para recibir todas la vacantes correspondientes")
    @GetMapping
    public ResponseEntity<Page<CoderResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(this.service.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @Operation(summary = "Lista Coder por ID", description = "Se debe enviar el ID del coder a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CoderResponse> getID(@PathVariable String id) {

        return ResponseEntity.ok(this.service.get(id));
    }
    @Operation(summary = "Crea un Coder", description = "Crea un coder se debe enviar la informacion en formato JSON")
    @PostMapping
    public ResponseEntity<CoderResponse> create(@Validated @RequestBody CoderRequest request) {

        return ResponseEntity.ok(this.service.create(request));

    }
    @Operation(summary = "Elimina un Coder", description = "Se debe enviar el ID del coder a eliminar por URL")

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Edita la informacion de un coder por ID", description = "El ID del coder a editar de debe enviar por URL Y la informacion a enviar por body en formato Json")

    @PutMapping(path = "/{id}")
    public ResponseEntity<CoderResponse> update(@PathVariable String id, @Validated @RequestBody CoderRequest request) {
        return ResponseEntity.ok(this.service.update(request, id));
    }
}

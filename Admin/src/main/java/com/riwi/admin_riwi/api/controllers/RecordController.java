package com.riwi.admin_riwi.api.controllers;

import java.util.List;
import com.riwi.admin_riwi.domain.entities.Record;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.riwi.admin_riwi.infrastructure.services.RecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/records")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Records")
public class RecordController {

    private final IRecordService recordService;
    private final RecordService record;
    @Operation(summary = "Lista de todos los registros dentro de la api ", description = "Se debe enviar la pagina y el tamaño de la pagina para recibir todas la vacantes correspondientes")

    @GetMapping
    public ResponseEntity<Page<RecordResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.recordService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Lista un Registro por ID", description = "Se debe enviar el ID a buscar")

    @GetMapping(path = "/{id}")
    public ResponseEntity<RecordResponse> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.recordService.get(id));
    }
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<List<RecordResponse>> getIdCoder(@PathVariable String id) {


            return ResponseEntity.ok(this.record.findByIdcoder(id));

    }   



    
    @Operation(summary = "Crea un Registro", description = "Crea un Registro se debe enviar la informacion en formato JSON")

    @PostMapping
    public ResponseEntity<RecordResponse> insert(
            @Validated @RequestBody RecordRequest request) {
        return ResponseEntity.ok(this.recordService.create(request));
    }

    @Operation(summary = "Edita la informacion de un registro por ID", description = "El ID a editar de debe enviar por URL Y la informacion a enviar por body en formato Json")

    @PutMapping(path = "/{id}")
    public ResponseEntity<RecordResponse> update(
            @Validated @RequestBody RecordRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.recordService.update(request, id));
    }

    @Operation(summary = "Elimina un Registro", description = "Se debe enviar el ID del Psicologa a eliminar por URL")

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.recordService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

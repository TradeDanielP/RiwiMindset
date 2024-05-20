package com.riwi.admin_riwi.api.controllers;

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

import com.riwi.admin_riwi.api.dto.request.AppointmentRequest;
import com.riwi.admin_riwi.api.dto.response.AppointmenResponse;
import com.riwi.admin_riwi.infrastructure.abstract_services.IAppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/appointments")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Appointments")
public class AppointmentController {

    private final IAppointmentService appointment;

    @Operation(summary = "Lista de todas las citas dentro de la api ", description = "Se debe enviar la pagina y el tamaño de la pagina para recibir todas la vacantes correspondientes")

    @GetMapping
    public ResponseEntity<Page<AppointmenResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.appointment.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Lista una cita por ID", description = "Se debe enviar el ID a buscar")

    @GetMapping(path = "/{id}")
    public ResponseEntity<AppointmenResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.appointment.get(id));
    }
    @Operation(summary = "Elimina una cita", description = "Se debe enviar el ID del Psicologa a eliminar por URL")

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.appointment.delete(id);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Edita la informacion de una cita por ID", description = "El ID a editar de debe enviar por URL Y la informacion a enviar por body en formato Json")

    @PutMapping(path = "/{id}")
    public ResponseEntity<AppointmenResponse> update(
            @Validated @RequestBody AppointmentRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.appointment.update(request, id));
    }
    @Operation(summary = "Crea un cita", description = "Crea un Registro se debe enviar la informacion en formato JSON")

    @PostMapping
    public ResponseEntity<AppointmenResponse> insert(
            @Validated @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(this.appointment.create(request));
    }

}

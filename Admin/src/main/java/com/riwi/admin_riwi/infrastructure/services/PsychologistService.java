package com.riwi.admin_riwi.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.request.PsychologistReq;
import com.riwi.admin_riwi.api.dto.response.AppointmentBasicResp;
import com.riwi.admin_riwi.api.dto.response.CoderBasicResponse;
import com.riwi.admin_riwi.api.dto.response.PsychologistResponse;
import com.riwi.admin_riwi.domain.entities.Appointment;
import com.riwi.admin_riwi.domain.entities.Psychologist;
import com.riwi.admin_riwi.domain.repositories.PsychologistRepository;
import com.riwi.admin_riwi.infrastructure.abstract_services.IPsychologistService;
import com.riwi.admin_riwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PsychologistService implements IPsychologistService {

    @Autowired
    private final PsychologistRepository service;

    @Override
    public PsychologistResponse create(PsychologistReq request) {
        Psychologist psychologist = this.requestToentity(request);
        psychologist.setAppointments(new ArrayList<>());
        return this.entityToResponse(this.service.save(psychologist));
    }

    @Override
    public void delete(String id) {
        Psychologist response = this.find(id);
        this.service.delete(response);

    }

    @Override
    public PsychologistResponse get(String id) {

        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<PsychologistResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest request = PageRequest.of(page, size);
        return this.service.findAll(request).map(this::entityToResponse);

    }

    @Override
    public PsychologistResponse update(PsychologistReq request, String id) {
        Psychologist psyco = this.find(id);
        psyco = this.requestToentity(request);
        psyco.set_id(id);
        psyco.setAppointments(new ArrayList<>());
        return this.entityToResponse(this.service.save(psyco));
    }

    // metodos propios
    private PsychologistResponse entityToResponse(Psychologist entity) {

        List<AppointmentBasicResp> appointments = entity.getAppointments()
                .stream()
                .map(this::entityToResponseAppointment)
                .collect(Collectors.toList());

        return PsychologistResponse.builder()
                ._id(entity.get_id())
                .name(entity.getName())
                .photo(entity.getPhoto())
                .document(entity.getDocument())
                .email(entity.getEmail())
                .role(entity.getRole())
                .appointments(appointments)
                .build();
    }

    private AppointmentBasicResp entityToResponseAppointment(Appointment entity) {

        CoderBasicResponse coder = new CoderBasicResponse();
            BeanUtils.copyProperties(entity.getCoder(), coder);

        return AppointmentBasicResp.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .start(entity.getStart())
                .end(entity.getEnd())
                .reason(entity.getReason())
                .date(entity.getDate())
                .time(entity.getTime())
                .coder(coder)
                .build();
    }

    private Psychologist requestToentity(PsychologistReq entity) {

        return Psychologist.builder()
                ._id(entity.get_id())
                .name(entity.getName())
                .password(entity.getPassword())
                .photo(entity.getPhoto())
                .document(entity.getDocument())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();

    }

    private Psychologist find(String id) {
        return this.service.findById(id)
                .orElseThrow(() -> new BadRequestException("No hay registros con el id suministrado"));

    }
}

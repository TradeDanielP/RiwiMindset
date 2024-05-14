package com.riwi.admin_riwi.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.request.AppointmentRequest;
import com.riwi.admin_riwi.api.dto.response.AppointmenResponse;
import com.riwi.admin_riwi.api.dto.response.CoderBasicResponse;
import com.riwi.admin_riwi.api.dto.response.CoderResponse;
import com.riwi.admin_riwi.api.dto.response.PsychologistBasicResponse;
import com.riwi.admin_riwi.api.dto.response.PsychologistResponse;
import com.riwi.admin_riwi.domain.entities.Appointment;
import com.riwi.admin_riwi.domain.entities.Coder;
import com.riwi.admin_riwi.domain.entities.Psychologist;
import com.riwi.admin_riwi.domain.repositories.AppointmentRepository;
import com.riwi.admin_riwi.domain.repositories.CoderRepository;
import com.riwi.admin_riwi.domain.repositories.PsychologistRepository;
import com.riwi.admin_riwi.infrastructure.abstract_services.IAppointmentService;
import com.riwi.admin_riwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    @Autowired
    private final AppointmentRepository objAppointmentRepository;
    @Autowired
    private final CoderRepository coderRepository;
    @Autowired
    private final PsychologistRepository psychologistRepository;

    @Override
    public AppointmenResponse create(AppointmentRequest request) {

        Coder coder = this.coderRepository.findById(request.getCoderId())
                .orElseThrow(() -> new BadRequestException("No hay coders con el id suministrado"));
        Psychologist psychologist = this.psychologistRepository.findById(request.getPyschologistId())
                .orElseThrow(() -> new BadRequestException("No hay psicologas con el id suministrado"));

        Appointment appointment = this.requestToEntity(request);

        appointment.setCoder(coder);
        appointment.setPsychologist(psychologist);

        return this.entityToResponse(this.objAppointmentRepository.save(appointment));
    }

    @Override
    public AppointmenResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public AppointmenResponse update(AppointmentRequest request, Long id) {

        Appointment appointment = this.find(id);

        Coder coder = this.coderRepository.findById(request.getCoderId())
                .orElseThrow(() -> new BadRequestException("No hay coders con el id suministrado"));
        Psychologist psychologist = this.psychologistRepository.findById(request.getPyschologistId())
                .orElseThrow(() -> new BadRequestException("No hay psicologas con el id suministrado"));

        appointment = this.requestToEntity(request);

        appointment.setCoder(coder);
        appointment.setPsychologist(psychologist);
        appointment.setId(id);

        return this.entityToResponse(this.objAppointmentRepository.save(appointment));
    }

    @Override
    public void delete(Long id) {
        this.objAppointmentRepository.delete(this.find(id));
    }

    @Override
    public Page<AppointmenResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.objAppointmentRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    private AppointmenResponse entityToResponse(Appointment entity) {

        PsychologistBasicResponse psychologist = new PsychologistBasicResponse();
            BeanUtils.copyProperties(entity.getPsychologist(), psychologist); 

        CoderBasicResponse coder = new CoderBasicResponse();
            BeanUtils.copyProperties(entity.getCoder(), coder);

        return AppointmenResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .start(entity.getStart())
                .end(entity.getEnd())
                .reason(entity.getReason())
                .date(entity.getDate())
                .time(entity.getTime())
                .pyschologist(psychologist)
                .coder(coder)
                .build();
    }

    private Appointment requestToEntity(AppointmentRequest request) {

        Coder coder = new Coder();
            BeanUtils.copyProperties(request.getCoderId(), coder);

        return Appointment.builder()
                .title(request.getTitle())
                .start(request.getStart())
                .end(request.getEnd())
                .reason(request.getReason())
                .date(request.getDate())
                .time(request.getTime())
                .coder(coder)
                .build();
    }

    private Appointment find(Long id) {
        return this.objAppointmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No hay citas con el id suministrado"));
    }

}

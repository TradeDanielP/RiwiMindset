package com.riwi.admin_riwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.request.AppointmentRequest;
import com.riwi.admin_riwi.api.dto.response.AppointmenResponse;
import com.riwi.admin_riwi.domain.entities.Appointment;
import com.riwi.admin_riwi.domain.repositories.AppointmentRepository;
import com.riwi.admin_riwi.infrastructure.abastract_services.IAppointmentService;
import com.riwi.admin_riwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    @Autowired
    private final AppointmentRepository objAppointmentRepository;
    
    @Override
    public AppointmenResponse create(AppointmentRequest request) {
        Appointment appointment = this.requestToEntity(request);
        return this.entityToResponse(this.objAppointmentRepository.save(appointment));
    }

    @Override
    public AppointmenResponse get(Long id) {
        return this.entityToResponse(find(id));
    }

    @Override
    public AppointmenResponse update(AppointmentRequest request, Long id) {
      
        Appointment appointment = this.find(id);

        appointment = this.requestToEntity(request);
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

        PageRequest pagination = null;

        return this.objAppointmentRepository.findAll(pagination)
            .map(this::entityToResponse);

    }

    private AppointmenResponse entityToResponse(Appointment entity){
        return AppointmenResponse.builder()
            .id(entity.getId())
            .coderName(entity.getCoderName())
            .start(entity.getStart())
            .end(entity.getEnd())
            .reason(entity.getReason())
            .date(entity.getDate())
            .time(entity.getTime())
            .coder(entity.getCoder())
            .pyschologist(entity.getPyschologist())
            .build();
    }

    private Appointment requestToEntity(AppointmentRequest request){
        return Appointment.builder()
            .coderName(request.getCoderName())
            .start(request.getStart())
            .end(request.getEnd())
            .reason(request.getReason())
            .date(request.getDate())
            .time(request.getTime())
            .coder(request.getCoder())
            .pyschologist(request.getPyschologist())
            .build();
    }

    private Appointment find(Long id){
        return this.objAppointmentRepository.findById(id)
            .orElseThrow(()-> new BadRequestException ("No hay registros con el id suministrado"));
    }
    
}
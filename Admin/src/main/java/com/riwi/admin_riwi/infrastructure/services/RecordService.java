package com.riwi.admin_riwi.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.request.RecordRequest;
import com.riwi.admin_riwi.api.dto.response.CoderResponse;
import com.riwi.admin_riwi.api.dto.response.RecordResponse;
import com.riwi.admin_riwi.domain.entities.Coder;
import com.riwi.admin_riwi.domain.entities.Record;
import com.riwi.admin_riwi.domain.repositories.CoderRepository;
import com.riwi.admin_riwi.domain.repositories.RecordRepository;
import com.riwi.admin_riwi.infrastructure.abstract_services.IRecordService;
import com.riwi.admin_riwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecordService implements IRecordService {

    @Autowired
    private final RecordRepository recordRepository;
    @Autowired 
    private final CoderRepository coderRepository;

    @Override
    public RecordResponse create(RecordRequest request) {  

        Coder coder = this.coderRepository.findById(request.getCoderId())
            .orElseThrow(() -> new BadRequestException("No Hay Coders con el id suministrado"));

        Record record = this.requestToEntity(request);

        record.setCoder(coder);

        return this.entityToResponse(this.recordRepository.save(record));

    }

    @Override
    public RecordResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public RecordResponse update(RecordRequest request, Long id) {
       
        Record record = this.find(id);

        Coder coder = this.coderRepository.findById(request.getCoderId())
            .orElseThrow(() -> new BadRequestException("No Hay Coders con el id suministrado"));
    
        record = this.requestToEntity(request);

        record.setCoder(coder);

        return this.entityToResponse(this.recordRepository.save(record));
    }

    @Override
    public void delete(Long id) {
        this.recordRepository.delete(this.find(id));
    }

    @Override
    public Page<RecordResponse> getAll(int page, int size) {
       
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.recordRepository.findAll(pagination)
            .map(this::entityToResponse);

    }
    
    
    private RecordResponse entityToResponse(Record entity) {

        CoderResponse coder = new CoderResponse();
        BeanUtils.copyProperties(entity.getCoder(), coder);

        return RecordResponse.builder()
                .id(entity.getId())
                .dateRecord(entity.getDateRecord())
                .registration(entity.getRegistration())
                .observation(entity.getObservation())
                .coder(coder)
                .build();

    }

    private Record requestToEntity(RecordRequest request) {

        return Record.builder()
            .dateRecord(request.getDateRecord())
            .registration(request.getRegistration())
            .observation(request.getObservation())
            .build();

    }

    private Record find(Long id){
        return this.recordRepository.findById(id)
            .orElseThrow(()-> new BadRequestException("No hay registros con el id suministrado"));
    }

}

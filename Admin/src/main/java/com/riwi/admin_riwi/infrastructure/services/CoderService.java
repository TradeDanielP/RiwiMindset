package com.riwi.admin_riwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.request.CoderRequest;
import com.riwi.admin_riwi.api.dto.response.CoderResponse;
import com.riwi.admin_riwi.domain.entities.Coder;
import com.riwi.admin_riwi.domain.repositories.CoderRepository;
import com.riwi.admin_riwi.infrastructure.abstract_services.ICoderService;
import com.riwi.admin_riwi.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CoderService implements ICoderService {

    @Autowired
    private final CoderRepository coderRep;

    @Override
    public CoderResponse create(CoderRequest request) {
        Coder response = this.requestToCoder(request);
        return this.entityToResp(this.coderRep.save(response));
    }

    @Override
    public void delete(String id) {
        
        Coder coder = this.find(id);
        this.coderRep.delete(coder);

    }

    @Override
    public CoderResponse get(String id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public Page<CoderResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest request = PageRequest.of(page, size);
        return this.coderRep.findAll(request).map(this::entityToResp);
    }

    @Override
    public CoderResponse update(CoderRequest request, String id) {
        Coder coder =this.find(id);
        coder=this.requestToCoder(request);
        coder.setId(id);
        return this.entityToResp(this.coderRep.save(coder));

    }

    private CoderResponse entityToResp(Coder entity) {

        return CoderResponse.builder().id(entity.getId()).name(entity.getName()).clan(entity.getClan())
                .phone(entity.getPhone())
                .email(entity.getEmail()).dateborn(entity.getDateborn()).phone(entity.getPhoto()).cc(entity.getCc()).build();

    }
    
    private Coder requestToCoder(CoderRequest entity) {
        return Coder.builder().name(entity.getName()).clan(entity.getClan()).phone(entity.getPhone())
                .email(entity.getEmail()).dateborn(entity.getDateborn()).phone(entity.getPhoto()).cc(entity.getCc()).build();

    }

    private Coder find(String id) {

        return this.coderRep.findById(id).orElseThrow(()-> new BadRequestException ("No hay registros con el id suministrado"));
    }
    
}

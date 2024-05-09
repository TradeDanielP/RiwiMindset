package com.riwi.admin_riwi.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.admin_riwi.api.dto.reponse.PsychologistResponse;
import com.riwi.admin_riwi.api.dto.request.PsychologistReq;
import com.riwi.admin_riwi.domain.entities.Psychologist;
import com.riwi.admin_riwi.domain.repositories.PsychologistRepository;
import com.riwi.admin_riwi.infrastructure.abastract_services.IPsychologistService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PsychologistService implements IPsychologistService {

    @Autowired
    private final PsychologistRepository service;

    @Override
    public PsychologistResponse create(PsychologistReq request) {
        Psychologist response = this.requestToentity(request);
        return this.entityToResponse(this.service.save(response));
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
        if(page<0) page=0;
        PageRequest request = PageRequest.of(page, size);
        return this.service.findAll(request).map(this::entityToResponse);

    }

    @Override
    public PsychologistResponse update(PsychologistReq request, String id) {
        Psychologist psyco= this.find(id);
        psyco=this.requestToentity(request);
        psyco.setId(id);
        return this.entityToResponse(this.service.save(psyco));
    }

    // metodos propios
    private PsychologistResponse entityToResponse(Psychologist entity) {

        return PsychologistResponse.builder().id(entity.getId()).name(entity.getName()).password(entity.getPassword()).photo(entity.getPhoto()).email(entity.getEmail()).build();

}
    private Psychologist requestToentity (PsychologistReq entity){

        return Psychologist.builder().name(entity.getName()).password(entity.getPassword()).photo(entity.getPhoto()).email(entity.getEmail()).build();

    }
    private Psychologist find(String id){
        return this.service.findById(id).orElseThrow();

    }
}

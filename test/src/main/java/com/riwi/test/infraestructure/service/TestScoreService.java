package com.riwi.test.infraestructure.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.test.api.dto.request.TestScoreResq;
import com.riwi.test.api.dto.response.TestScoreResp;
import com.riwi.test.domain.entities.TestScore;
import com.riwi.test.domain.repositories.TestScoreRepository;
import com.riwi.test.infraestructure.abstract_service.ITestScoreService;
import com.riwi.test.utils.enums.SortType;

import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestScoreService implements ITestScoreService{
    @Autowired
    private final TestScoreRepository testScoreRepository;

    @Override
    public TestScoreResp create(TestScoreResq request) {
        TestScore testScore = this.requestToEntity(request);
        testScore.setIdCoder(request.getIdCoder());
        testScore.setScore(request.getScore());
        testScore.setTest(request.test);

        return this.entityToResp(this.testScoreRepository.save(testScore));


    }

    @Override
    public void delete(Integer id) {
        this.testScoreRepository.delete(this.find(id));
        
    }

    @Override
    public TestScoreResp get(Integer id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public Page<TestScoreResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.testScoreRepository.findAll(pagination)
                .map(this::entityToResp);
    }

    @Override
    public TestScoreResp update(TestScoreResq request, Integer id) {
        TestScore testScore = this.find(id);
        testScore = this.requestToEntity(request);

        testScore.setIdScore(id);
        testScore.setIdCoder(request.getIdCoder());
        testScore.setScore(request.getScore());
        testScore.setTest(request.getTest());

        return this.entityToResp(this.testScoreRepository.save(testScore));

    }
    private TestScore requestToEntity(TestScoreResq request) {

        return TestScore.builder()
                .idCoder(request.getIdCoder())
                .score(request.getScore())
                .test(request.getTest())
                .build();
    }
    
    private TestScoreResp entityToResp(TestScore entity){

        return TestScoreResp.builder()
                    .idScore(entity.getIdScore())
                    .idCoder(entity.getIdCoder())
                    .score(entity.getScore())
                    .test(entity.getTest())
                    .build();

    }    
  
    private TestScore find(Integer id) {
        return this.testScoreRepository.findById(id)
        .orElseThrow(()-> new BadRequestException("No hay citas con el id suministrado"));
    }
            
}

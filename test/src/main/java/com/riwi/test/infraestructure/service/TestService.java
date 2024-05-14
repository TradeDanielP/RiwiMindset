package com.riwi.test.infraestructure.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.test.api.dto.request.TestReq;
import com.riwi.test.api.dto.response.TestResp;
import com.riwi.test.domain.entities.TestEntity;
import com.riwi.test.domain.repositories.TestRepository;
import com.riwi.test.infraestructure.abstract_service.ITestService;
import com.riwi.test.utils.enums.SortType;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TestService implements ITestService{
    @Autowired
    private final TestRepository testRepository;

    @Override
    public TestResp create(TestReq request) {
        TestEntity test = this.requestToEntity(request);
        test.setTestScore(new ArrayList<>());
        return this.entityToResp(this.testRepository.save(test));
    }

    @Override
    public void delete(Integer id) {
       this.testRepository.delete(this.find(id));
    }

    @Override
    public TestResp get(Integer id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public Page<TestResp> getAll(int page, int size, SortType sortType) {
        if (page <0) page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        
        return this.testRepository.findAll(pagination)
                .map(this::entityToResp);
    }

    @Override
    public TestResp update(TestReq request, Integer id) {
        TestEntity test = this.find(id);
        test = this.requestToEntity(request);
        test.setIdTest(test.getIdTest());
        test.setTypeTest(test.getTypeTest());
        test.setQuestion(test.getQuestion());
        test.setAnswers(test.getAnswers());
        test.setAnswerCorrectIndex(test.getAnswerCorrectIndex());
        return this.entityToResp(this.testRepository.save(test));
    }


    private TestEntity requestToEntity(TestReq request) {

        return TestEntity.builder()
                .typeTest(request.getTypeTest())
                .question(request.getQuestion())
                .answers(request.getAnswers())
                .answerCorrectIndex(request.getAnswerCorrectIndex())
                .build();
    }

    private TestResp entityToResp(TestEntity entity){

        return TestResp.builder()
                .idTest(entity.getIdTest())
                .typeTest(entity.getTypeTest())
                .question(entity.getQuestion())
                .answers(entity.getAnswers())
                .answerCorrectIndex(entity.getAnswerCorrectIndex())
                .build();

    }

    private TestEntity find(Integer id) {
        return this.testRepository.findById(id)
        .orElseThrow(()-> new BadRequestException("No hay citas con el id suministrado"));
    }
        
    
}

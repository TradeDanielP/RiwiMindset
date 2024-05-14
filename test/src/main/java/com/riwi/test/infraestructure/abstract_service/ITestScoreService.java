package com.riwi.test.infraestructure.abstract_service;

import com.riwi.test.api.dto.request.TestScoreResq;
import com.riwi.test.api.dto.response.TestScoreResp;

public interface ITestScoreService extends CrudService<TestScoreResq, TestScoreResp, Integer>{
    public String FIELD_BY_SORT = "Score";
}

package com.riwi.test.infraestructure.abstract_service;

import com.riwi.test.api.dto.request.TestReq;
import com.riwi.test.api.dto.response.TestResp;

public interface ITestService extends CrudService<TestReq, TestResp, Integer>{
    public final String FIELD_BY_SORT = "test";
}

package com.riwi.admin_riwi.infrastructure.abstract_services;

import com.riwi.admin_riwi.api.dto.request.CoderRequest;
import com.riwi.admin_riwi.api.dto.response.CoderResponse;

public interface ICoderService extends CrudService<CoderRequest,CoderResponse,String> {
    
}

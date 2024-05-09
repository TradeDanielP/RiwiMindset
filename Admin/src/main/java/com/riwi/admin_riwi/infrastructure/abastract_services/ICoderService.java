package com.riwi.admin_riwi.infrastructure.abastract_services;

import com.riwi.admin_riwi.api.dto.reponse.CoderResponse;
import com.riwi.admin_riwi.api.dto.request.CoderRequest;

public interface ICoderService extends CrudService<CoderRequest,CoderResponse,String> {
    
}

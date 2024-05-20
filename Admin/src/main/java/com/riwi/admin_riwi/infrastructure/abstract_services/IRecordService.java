package com.riwi.admin_riwi.infrastructure.abstract_services;

import com.riwi.admin_riwi.api.dto.request.RecordRequest;
import com.riwi.admin_riwi.api.dto.response.RecordResponse;

public interface IRecordService 
    extends CrudService<RecordRequest,RecordResponse, Long> {

}

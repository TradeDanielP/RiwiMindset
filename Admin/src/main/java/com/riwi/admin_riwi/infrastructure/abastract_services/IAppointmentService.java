package com.riwi.admin_riwi.infrastructure.abastract_services;

import com.riwi.admin_riwi.api.dto.request.AppointmentRequest;
import com.riwi.admin_riwi.api.dto.response.AppointmenResponse;

public interface IAppointmentService extends CrudService<AppointmentRequest,AppointmenResponse, Long>  {
    
}

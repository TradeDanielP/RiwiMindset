package com.riwi.admin_riwi.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PsychologistResponse {
    private String id;
    private String name;
    private String photo;
    private String document;
    private String email;
    private List<AppointmentBasicResp> appointments;
}
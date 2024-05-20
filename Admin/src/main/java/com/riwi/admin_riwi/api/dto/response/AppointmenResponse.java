package com.riwi.admin_riwi.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmenResponse {
    
    private Long id;
    private String title;
    private String start;
    private String end;
    private String reason;
    private LocalDate date;
    private String time;
    private CoderBasicResponse coder;
    private PsychologistBasicResponse pyschologist;

}

package com.riwi.admin_riwi.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PsychologistBasicResponse {
    
    private String _id;
    private String name;
    private String photo;
    private String document;
    private String email;

}

package com.riwi.admin_riwi.api.dto.reponse;

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
    private String password;

    private String photo;
    private String email;

}

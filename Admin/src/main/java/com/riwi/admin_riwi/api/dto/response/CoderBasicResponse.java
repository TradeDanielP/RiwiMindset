package com.riwi.admin_riwi.api.dto.response;

import java.math.BigInteger;
import java.time.LocalDate;

import com.riwi.admin_riwi.util.enums.ClanEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoderBasicResponse {
    private String _id;
    private String name;
    private ClanEnum clan;
    private String phone;
    private String email;    
    private String photo;
    private LocalDate dateBirth;
    private String document;
}

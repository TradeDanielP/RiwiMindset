package com.riwi.test.api.dto.response;

import com.riwi.test.domain.entities.TestEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestScoreResp {
    private int idScore;
    public String idCoder;
    public String score;
    private TestEntity test;
}

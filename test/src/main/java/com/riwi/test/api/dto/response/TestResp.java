package com.riwi.test.api.dto.response;
import java.util.List;

import com.riwi.test.domain.entities.TestScore;
import com.riwi.test.utils.enums.TypeTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResp {
    private int idTest;
    private TypeTest typeTest;
    private String question;
    private List<String> answers;
    private int answerCorrectIndex;
    private List<TestScore> testScore;
}

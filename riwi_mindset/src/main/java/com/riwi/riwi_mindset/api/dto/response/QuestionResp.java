package com.riwi.riwi_mindset.api.dto.response;

import java.util.List;

import com.riwi.riwi_mindset.utils.enums.TypeQuestion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResp {
    private int idQuestion;
    private TypeQuestion typeQuestion;
    private String question;
    private List<String> answers;
}

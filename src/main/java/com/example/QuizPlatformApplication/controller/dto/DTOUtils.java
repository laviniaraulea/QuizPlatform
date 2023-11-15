package com.example.QuizPlatformApplication.controller.dto;

import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.model.QuizOptions;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DTOUtils {
    static public QuestionInfoDTO getQuestionInfoDTO(QuizEntry quizEntry) {
        List<Pair<Long, String>> possibleAnswers = new ArrayList<>();
        for(QuizOptions quizOptions : quizEntry.getOptionAndExplanation()) {
            possibleAnswers.add(Pair.of(quizOptions.getId(), quizOptions.getOption()));
        }
        return new QuestionInfoDTO(quizEntry.getId(), quizEntry.isMultipleChoice(), quizEntry.getQuestion(), possibleAnswers);
    }

}

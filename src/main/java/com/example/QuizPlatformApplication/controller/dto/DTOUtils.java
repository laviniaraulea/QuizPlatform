package com.example.QuizPlatformApplication.controller.dto;

import com.example.QuizPlatformApplication.model.QuizEntry;
import com.example.QuizPlatformApplication.model.QuizOptions;
import com.example.QuizPlatformApplication.model.QuizProgress;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing methods to convert objects
 * to DTO objects and vice versa
 */
public class DTOUtils {
    /**
     * Converts a QuizEntry object to a QuestionInfoDTO object
     * @param quizEntry QuizEntry object to be converted
     * @return QuestionInfoDTO object
     */
    static public QuestionInfoDTO getQuestionInfoDTO(QuizEntry quizEntry) {
        List<Pair<Long, String>> possibleAnswers = new ArrayList<>();
        for(QuizOptions quizOptions : quizEntry.getOptionAndExplanation()) {
            possibleAnswers.add(Pair.of(quizOptions.getId(), quizOptions.getOption()));
        }
        return new QuestionInfoDTO(quizEntry.getId(), quizEntry.isMultipleChoice(), quizEntry.getQuestion(), possibleAnswers);
    }

    /**
     * Converts a QuizProgress object to a QuizProgressDTO object
     * @param quizProgress QuizProgress object to be converted
     * @return QuizProgressDTO object
     */
    static public QuizProgressDTO toDTO(QuizProgress quizProgress) {
        return new QuizProgressDTO(quizProgress.getQuiz().getId(), quizProgress.getQuiz().getOwner().getUsername(), quizProgress.getHasEnded(), quizProgress.getStartTime(), quizProgress.getEndTime(), quizProgress.getScore());
    }
}

package com.example.QuizPlatformApplication.service.implementation;

import com.example.QuizPlatformApplication.controller.dto.AnswerDTO;
import com.example.QuizPlatformApplication.controller.dto.FailedAndPassDTO;
import com.example.QuizPlatformApplication.controller.dto.GeneralStatsDTO;
import com.example.QuizPlatformApplication.model.*;
import com.example.QuizPlatformApplication.model.validator.MyException;
import com.example.QuizPlatformApplication.model.validator.Validator;
import com.example.QuizPlatformApplication.repository.*;
import com.example.QuizPlatformApplication.service.ServiceException;
import com.example.QuizPlatformApplication.service.interfaces.QuizServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the QuizServiceInterface and provides the business logic
 * for managing quizzes, questions, and user progress in the quiz platform application.
 */
@Service
public class QuizServiceImpl implements QuizServiceInterface {
    /**
     * The validator used for input validation.
     */
    @Autowired
    private Validator validator;
    /**
     * The repository for managing Quiz entities.
     */
    @Autowired
    private QuizRepoInterface quizRepoInterface;

    /**
     * The repository for managing QuizEntry entities.
     */
    @Autowired
    private QuizEntryRepoInterface quizEntryRepoInterface;

    /**
     * The repository for managing quiz progress.
     */
    @Autowired
    private QuizProgressRepoInterface quizProgressRepoInterface;

    /**
     * The repository for managing user's answers to the quiz.
     */
    @Autowired
    private QuizUserAnswerRepoInterface quizUserAnswerRepoInterface;

    /**
     * The repository for managing QuizOptions entities.
     */
    @Autowired
    private QuizOptionRepoInterface quizOptionRepoInterface;

    /**
     * Creates a new quiz.
     * @param quiz The Quiz object to be created.
     * @throws MyException If validation of the quiz fails.
     */
    @Override
    public void createQuiz(Quiz quiz) throws MyException {
        validator.validateNewQuiz(quiz);
        quizRepoInterface.save(quiz);
    }

    /**
     * Creates a new quiz entry.
     * @param quizEntry The QuizEntry object representing the question.
     * @throws MyException If validation of the question fails.
     */
    @Override
    public void createQuestion(QuizEntry quizEntry) throws MyException {
        validator.validateNewQuestion(quizEntry);
        quizEntryRepoInterface.save(quizEntry);
        for(QuizOptions quizOptions: quizEntry.getOptionAndExplanation()){
            quizOptions.setQuizEntry(quizEntry);
            quizOptionRepoInterface.save(quizOptions);
        }
    }

    /**
     * Adds a quiz entry to the specified quiz.
     * @param quiz The Quiz object to which the quiz entry will be added.
     * @param quizEntry The QuizEntry object representing the question to be added.
     * @throws MyException If validation of the quiz entry addition fails.
     */
    @Override
    public void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws MyException {
        validator.validateAddQuestionToQuiz(quiz,quizEntry);
        quiz.getQuizEntries().add(quizEntry);
        quizEntry.setQuiz(quiz);
        quizRepoInterface.save(quiz);
        quizEntryRepoInterface.save(quizEntry);
    }

    /**
     * Deletes a quiz entry from the specified quiz.
     * @param quiz The Quiz object from which the quiz entry will be deleted.
     * @param quizEntry The QuizEntry object representing the question to be deleted.
     * @throws ServiceException If an error occurs during the deletion process.
     */
    @Override
    public void deleteQuestionFromQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException {
        // Validation to ensure quiz and quizEntry are not null
        if (quiz == null || quizEntry == null) {
            throw new ServiceException("Invalid quiz or question.");
        }

        // Validation to ensure the question exists in the quiz before attempting to remove it
        if (!quiz.getQuizEntries().contains(quizEntry)) {
            throw new ServiceException("The question does not exist in the quiz.");
        }
        quiz.getQuizEntries().remove(quizEntry);
        quizRepoInterface.save(quiz);
    }

    /**
     * Retrieves a Quiz object by its ID.
     * @param id The ID of the quiz to retrieve.
     * @return The Quiz object.
     * @throws ServiceException If the quiz with the specified ID is not found.
     */
    @Override
    public Quiz getQuizById(Long id) throws ServiceException {
        // Validation to ensure id is not null or a negative number
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz ID provided.");
        }
        Optional<Quiz> quizOptional = quizRepoInterface.findById(id);
        if (quizOptional.isPresent()) {
            return quizOptional.get();
        } else {
            throw new ServiceException("Quiz not found for ID: " + id);
        }
    }

    /**
     * Retrieves a QuizEntry object by its ID.
     * @param id The ID of the quiz entry to retrieve.
     * @return The QuizEntry object.
     * @throws ServiceException If the quiz entry with the specified ID is not found.
     */
    @Override
    public QuizEntry getQuizEntryById(Long id) throws ServiceException {
        // Validation to ensure id is not null or a negative number
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz entry ID provided.");
        }
        Optional<QuizEntry> quizEntryOptional = quizEntryRepoInterface.findById(id);
        if (quizEntryOptional.isPresent()) {
            return quizEntryOptional.get();
        } else {
            throw new ServiceException("Quiz entry not found for ID: " + id);
        }
    }

    /**
     * Retrieves a list of all quizzes.
     * @return List of Quiz objects.
     */
    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepoInterface.findAll();
    }

    @Override
    public List<Quiz> getQuizzesByCategory(String category) {

        QuizCategory quizCategory = QuizCategory.valueOf(category);

        return new ArrayList<>(quizRepoInterface.findAllByCategory(quizCategory));
    }

    @Override
    public List<Quiz> getQuizzesByDifficulty(String difficulty) {

        QuizDifficulty quizDifficulty = QuizDifficulty.valueOf(difficulty);

        return new ArrayList<>(quizRepoInterface.findAllByDifficulty(quizDifficulty));
    }

    @Override
    public boolean isQuizStarted(Quiz quiz, User user) {
        List<QuizProgress> quizProgresses = quizProgressRepoInterface.findByQuizAndUser(quiz, user);
        return quizProgresses.size() != 0 && !quizProgresses.get(quizProgresses.size()-1).getHasEnded();
    }

    @Override
    public QuizProgress getLastQuizProgress(Quiz quiz, User user) {
        List<QuizProgress> quizProgresses = quizProgressRepoInterface.findByQuizAndUser(quiz, user);
        if (quizProgresses.size() == 0) {
            return null;
        }
        return quizProgresses.get(quizProgresses.size()-1);
    }

    @Override
    public void startQuiz(Quiz quiz, User user) throws ServiceException {
        if (quiz == null || user == null) {
            throw new ServiceException("Invalid quiz.");
        }

        if (isQuizStarted(quiz, user)) {
            throw new ServiceException("Quiz already started.");
        }

        QuizProgress quizProgress = new QuizProgress();
        quizProgress.setQuiz(quiz);
        quizProgress.setUser(user);
        quizProgress.setHasEnded(false);
        quizProgress.setStartTime(LocalDateTime.now());
        quizProgress.setScore(0F);
        quizProgressRepoInterface.save(quizProgress);
    }

    @Override
    public void endQuiz(Quiz quiz, User user, List<AnswerDTO> userAnswers) throws ServiceException {
        if(quiz == null || user == null || userAnswers == null) {
            throw new ServiceException("Invalid data.");
        }

        if (!isQuizStarted(quiz, user)) {
            throw new ServiceException("Quiz not started.");
        }

        QuizProgress quizProgress = getLastQuizProgress(quiz, user);
        LocalDateTime endTime = LocalDateTime.now();

        try {
            for(AnswerDTO answerDTO : userAnswers) {
                QuizUserAnswer quizUserAnswer = new QuizUserAnswer();
                quizUserAnswer.setQuizProgress(quizProgress);

                Long quizEntryId = Long.parseLong(answerDTO.getQuestionId());

                quizUserAnswer.setQuizEntry(getQuizEntryById(quizEntryId));

                List<QuizOptions> answers = new ArrayList<>();
                for (String answer : answerDTO.getAnswersIds()) {
                    Long quizOptionId = Long.parseLong(answer);

                    Optional<QuizOptions> quizOptions = quizOptionRepoInterface.findById(quizOptionId);
                    if (quizOptions.isEmpty()) {
                        throw new ServiceException("Invalid answer.");
                    }
                    answers.add(quizOptions.get());
                }
                quizUserAnswer.setAnswers(answers);
                quizUserAnswerRepoInterface.save(quizUserAnswer);
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Invalid answer.");
        }

        quizProgress.setHasEnded(true);
        quizProgress.setEndTime(endTime);
        quizProgress.setScore(5.0F); // TODO: calculate score
        quizProgressRepoInterface.save(quizProgress);
    }

    @Override
    public GeneralStatsDTO getGeneralStats(User user) {
        long totalQuizzesDone = quizProgressRepoInterface.countAllByUserAndHasEnded(user, true);
        long totalQuizzesCreated = quizRepoInterface.countAllByOwner(user);
        List<QuizProgress> lstQuizProgress= quizProgressRepoInterface.findByUserAndHasEnded(user, true);
        float average = 0;

        for(QuizProgress quizProgress : lstQuizProgress){
            average = average + quizProgress.getScore();
        }

        average = average / lstQuizProgress.size();

        return new GeneralStatsDTO(totalQuizzesCreated, totalQuizzesDone, average);
    }

    @Override
    public GeneralStatsDTO getStatsPerCategory(User user, String category) {
        long totalQuizzesDone = quizProgressRepoInterface.countAllByUserAndHasEndedAndQuizCategory(user, true, QuizCategory.valueOf(category));
        long totalQuizzesCreated = quizRepoInterface.countAllByOwnerAndCategory(user, QuizCategory.valueOf(category));

        List<QuizProgress> lstQuizProgress= quizProgressRepoInterface.findByUserAndHasEndedAndQuizCategory(user, true, QuizCategory.valueOf(category));
        float average = 0;

        for(QuizProgress quizProgress : lstQuizProgress){
            average = average + quizProgress.getScore();
        }

        average = average / lstQuizProgress.size();

        return new GeneralStatsDTO(totalQuizzesCreated, totalQuizzesDone, average);

    }

    @Override
    public FailedAndPassDTO getFailedAndPass(User user) {
        long noFailedQuizzes = 0;
        long noPassQuizzes = 0;

        List<QuizProgress> lstQuizProgress= quizProgressRepoInterface.findByUserAndHasEnded(user, true);

        for(QuizProgress quizProgress : lstQuizProgress){
            if(quizProgress.getScore() >= quizProgress.getQuiz().getPassingScore()){
                noPassQuizzes += 1;
            }
            else {
                noFailedQuizzes += 1;
            }
        }

        return new FailedAndPassDTO(noFailedQuizzes, noPassQuizzes);
    }

}

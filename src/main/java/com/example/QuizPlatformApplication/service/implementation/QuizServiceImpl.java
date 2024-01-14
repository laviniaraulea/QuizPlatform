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
import java.util.*;

@Service
public class QuizServiceImpl implements QuizServiceInterface {
    @Autowired
    private Validator validator;
    @Autowired
    private QuizRepoInterface quizRepoInterface;

    @Autowired
    private QuizEntryRepoInterface quizEntryRepoInterface;

    @Autowired
    private QuizProgressRepoInterface quizProgressRepoInterface;

    @Autowired
    private QuizUserAnswerRepoInterface quizUserAnswerRepoInterface;

    @Autowired
    private QuizOptionRepoInterface quizOptionRepoInterface;

    @Override
    public void createQuiz(Quiz quiz) throws ServiceException, MyException {
        // Validare pentru asigurarea ca quiz-ul nu este null si are toate informatiile necesare
//        if (quiz == null || quiz.getOwner() == null || quiz.getCategory() == null || quiz.getDifficulty() == null) {
//            throw new ServiceException("Invalid quiz. Make sure all the required information is provided.");
//        }
        validator.validateNewQuiz(quiz);
        quizRepoInterface.save(quiz);
    }

    @Override
    public void createQuestion(QuizEntry quizEntry) throws ServiceException, MyException {
//        if (quizEntry == null || quizEntry.getQuestion() == null || quizEntry.getOptionAndExplanation() == null || quizEntry.getHint() == null) {
//            throw new ServiceException("Invalid question. Make sure all the required information is provided.");
//        }
        validator.validateNewQuestion(quizEntry);
        quizEntryRepoInterface.save(quizEntry);
        for(QuizOptions quizOptions: quizEntry.getOptionAndExplanation()){
            quizOptions.setQuizEntry(quizEntry);
            quizOptionRepoInterface.save(quizOptions);
        }
    }

    @Override
    public void addQuestionToQuiz(Quiz quiz, QuizEntry quizEntry) throws ServiceException, MyException {
//        // Validation to ensure quiz and quizEntry are not null
//        if (quiz == null || quizEntry == null) {
//            throw new ServiceException("Invalid quiz or question.");
//        }
//
//        // Validation to ensure the question does not already exist in the quiz
//        if (quiz.getQuizEntries().contains(quizEntry)) {
//            throw new ServiceException("The question already exists in the quiz.");
//        }
        validator.validateAddQuestionToQuiz(quiz,quizEntry);
        quiz.getQuizEntries().add(quizEntry);
        quizEntry.setQuiz(quiz);
        quizRepoInterface.save(quiz);
        quizEntryRepoInterface.save(quizEntry);
    }

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

    @Override
    public Quiz getQuizById(Long id) throws ServiceException {
        // Validare pentru identificatorul null sau negativ
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz ID provided.");
        }

        // Cautarea quiz-ului in baza de date
        Optional<Quiz> quizOptional = quizRepoInterface.findById(id);
        if (quizOptional.isPresent()) {
            return quizOptional.get();
        } else {
            throw new ServiceException("Quiz not found for ID: " + id);
        }
    }

    @Override
    public QuizEntry getQuizEntryById(Long id) throws ServiceException {
        // Validare pentru identificatorul null sau negativ
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid quiz entry ID provided.");
        }

        // Cautarea intrarii de quiz in baza de date
        Optional<QuizEntry> quizEntryOptional = quizEntryRepoInterface.findById(id);
        if (quizEntryOptional.isPresent()) {
            return quizEntryOptional.get();
        } else {
            throw new ServiceException("Quiz entry not found for ID: " + id);
        }
    }

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

    /**
     * Returns if the quiz has been started by the user.
     * @param quiz the quiz to be checked
     * @param user the user that is taking the quiz
     * @return true if the quiz has been started by the user, false otherwise
     */
    @Override
    public boolean isQuizStarted(Quiz quiz, User user) {
        List<QuizProgress> quizProgresses = quizProgressRepoInterface.findByQuizAndUser(quiz, user);
        return quizProgresses.size() != 0 && !quizProgresses.get(quizProgresses.size()-1).getHasEnded();
    }

    /**
     * Returns the last quiz progress for the user.
     * @param quiz the quiz to be checked
     * @param user the user searched
     * @return the last quiz progress for the user, null if the user hasn't started the quiz
     */
    @Override
    public QuizProgress getLastQuizProgress(Quiz quiz, User user) {
        List<QuizProgress> quizProgresses = quizProgressRepoInterface.findByQuizAndUser(quiz, user);
        if (quizProgresses.size() == 0) {
            return null;
        }
        return quizProgresses.get(quizProgresses.size()-1);
    }

    /**
     * Starts the quiz for the user.
     *
     * @param quiz the quiz to be started
     * @param user the user that is taking the quiz
     * @throws ServiceException if the quiz is null, the user is null, the quiz is already started
     */
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

    /**
     * Ends the quiz for the user and calculates the score.
     *
     * @param quiz the quiz to be ended
     * @param user the user that is taking the quiz
     * @param userAnswers the answers provided by the user
     * @throws ServiceException if the quiz is null, the user is null, the quiz is not started, the quiz is already ended, the user answers are null or the user answers are invalid
     */
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
        quizProgress.setScore(5.0F); // TODO: use calculateScore(quiz, userAnswers)
        quizProgressRepoInterface.save(quizProgress);
    }

    /**
     * Calculates the score of a quiz based on the user's answers;
     * For every question, if all the correct answers are selected, the user gets 100% of the point;
     * If the user selects some but not all of the correct answers, the user gets a percent of the point;
     * If the user selects a wrong answer, the user gets 0 points.
     *
     * @param quiz The quiz
     * @param userAnswers The user's answers
     * @return The score, 0 if the quiz is not found
     */
    private float calculateScore(Quiz quiz, List<AnswerDTO> userAnswers) {
        int numberOfQuestions = quiz.getQuizEntries().size();

        Map<Long, Float> map = new HashMap<>();
        for(AnswerDTO answerDTO : userAnswers) {
            Long questionId = Long.parseLong(answerDTO.getQuestionId());
            QuizEntry quizEntry;
            try {
                quizEntry = getQuizEntryById(questionId);
            } catch (ServiceException e) {
                return 0F;
            }

            Set<Long> correctQuizOptions = new HashSet<>();
            for (QuizOptions quizOptions : quizEntry.getOptionAndExplanation()) {
                if (quizOptions.isCorrectOption()) {
                    correctQuizOptions.add(quizOptions.getId());
                }
            }

            Set<Long> userQuizOptions = new HashSet<>();
            for (String answer : answerDTO.getAnswersIds()) {
                Long quizOptionId = Long.parseLong(answer);
                userQuizOptions.add(quizOptionId);
            }

            if(correctQuizOptions.containsAll(userQuizOptions)) {
                map.put(questionId, (float) (correctQuizOptions.size() / userQuizOptions.size()));
            } else {
                map.put(questionId, 0F);
            }
        }

        float score = 0F;
        for (Map.Entry<Long, Float> entry : map.entrySet()) {
            score += entry.getValue();
        }
        return (score / numberOfQuestions) * 10;
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

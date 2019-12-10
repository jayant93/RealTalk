package com.realTalk.bot.helpers.Request;

import java.util.List;

public class QuestionRequest {

    private String Question;

    private List<AnswersRequest> Answers;

    private String validationType;

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public List<AnswersRequest> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<AnswersRequest> answers) {
        Answers = answers;
    }
}

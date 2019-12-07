package com.realTalk.bot.helpers.Request;

import java.util.List;

public class QuestionRequest {

    private String Question;

    private List<AnswersRequest> Answers;

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

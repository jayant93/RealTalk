package com.realTalk.bot.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Questions {

    @Id
    @GeneratedValue
    private Long id;

    private String Question;

    private String QuestionType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private  List<Answers> answers;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }
}

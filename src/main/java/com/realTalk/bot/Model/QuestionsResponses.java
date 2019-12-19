package com.realTalk.bot.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions_responses")
public class QuestionsResponses {

    @Id
    @GeneratedValue
    private long responseId;

    @ManyToOne
    private Questions question;

    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime answeredAt = LocalDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime questionSentAt = LocalDateTime.now();

    @ManyToOne
    private Company company;

    @ManyToOne
    private CompanyEmployeeDetails user;

    private long numericResponse;

    private String freeFormResponse;

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }

    public LocalDateTime getQuestionSentAt() {
        return questionSentAt;
    }

    public void setQuestionSentAt(LocalDateTime questionSentAt) {
        this.questionSentAt = questionSentAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyEmployeeDetails getUser() {
        return user;
    }

    public void setUser(CompanyEmployeeDetails user) {
        this.user = user;
    }

    public long getNumericResponse() {
        return numericResponse;
    }

    public void setNumericResponse(long numericResponse) {
        this.numericResponse = numericResponse;
    }

    public String getFreeFormResponse() {
        return freeFormResponse;
    }

    public void setFreeFormResponse(String freeFormResponse) {
        this.freeFormResponse = freeFormResponse;
    }
}

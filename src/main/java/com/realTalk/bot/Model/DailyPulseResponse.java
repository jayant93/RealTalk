package com.realTalk.bot.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class DailyPulseResponse {

    @Id
    @GeneratedValue
    private Long id ;

    private String userId;
    private String Question;
    private String textResponse;
    private String numericResponse;
    private Boolean ActiveStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime answeredAt = LocalDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime questionSentAt = LocalDateTime.now();

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

    public Boolean getActiveStatus() {
        return ActiveStatus;
    }

    public void setActiveStatus(Boolean status) {
        ActiveStatus = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }

    public String getNumericResponse() {
        return numericResponse;
    }

    public void setNumericResponse(String numericResponse) {
        this.numericResponse = numericResponse;
    }
}

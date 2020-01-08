package com.realTalk.bot.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Answers {


    @Id
    @GeneratedValue
    private Long id;

    private String Answers;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime answerGivenAt = LocalDateTime.now();

    private String ScalePoint;

    @ManyToOne
    private CompanyEmployeeDetails employee;

    private int NumberOfIncorrectformats;

    public LocalDateTime getAnswerGivenAt() {
        return answerGivenAt;
    }

    @ManyToOne
    private Questions question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public CompanyEmployeeDetails getEmployee() {
        return employee;
    }

    public void setEmployee(CompanyEmployeeDetails employee) {
        this.employee = employee;
    }

    public int getNumberOfIncorrectformats() {
        return NumberOfIncorrectformats;
    }

    public void setNumberOfIncorrectformats(int numberOfIncorrectformats) {
        NumberOfIncorrectformats = numberOfIncorrectformats;
    }


    public String getScalePoint() {
        return ScalePoint;
    }

    public void setScalePoint(String scalePoint) {
        ScalePoint = scalePoint;
    }

    public String getAnswers() {
        return Answers;
    }

    public void setAnswers(String answers) {
        Answers = answers;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }


}

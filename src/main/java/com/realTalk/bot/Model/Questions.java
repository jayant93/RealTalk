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

    private String ValidationType;

    @ManyToOne
    private Topics topic;

    private String extraCheck;

    @ManyToOne
    private SubTopics subTopics;

    private boolean inUse;

    private long timesSent;

    private long timesAnswered;

    private float percentYield;

    private float avgScore;

    private int QuestionOrder;

    private int NumberOfTimesAsked;

    private int NumberOfTimesAnswered;

    public int getNumberOfTimesAsked() {
        return NumberOfTimesAsked;
    }

    public void setNumberOfTimesAsked(int numberOfTimesAsked) {
        NumberOfTimesAsked = numberOfTimesAsked;
    }

    public int getQuestionOrder() {
        return QuestionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        QuestionOrder = questionOrder;
    }

    public String getExtraCheck() {
        return extraCheck;
    }

    public void setExtraCheck(String extraCheck) {
        this.extraCheck = extraCheck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }

    public String getValidationType() {
        return ValidationType;
    }

    public void setValidationType(String validationType) {
        ValidationType = validationType;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

    public SubTopics getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(SubTopics subTopics) {
        this.subTopics = subTopics;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public long getTimesSent() {
        return timesSent;
    }

    public void setTimesSent(long timesSent) {
        this.timesSent = timesSent;
    }

    public long getTimesAnswered() {
        return timesAnswered;
    }

    public void setTimesAnswered(long timesAnswered) {
        this.timesAnswered = timesAnswered;
    }

    public float getPercentYield() {
        return percentYield;
    }

    public void setPercentYield(float percentYield) {
        this.percentYield = percentYield;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }
}

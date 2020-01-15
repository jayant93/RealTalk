package com.realTalk.bot.Model;

import javax.persistence.*;

@Entity
public class MemberQuery {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private InstantMessagingData member;

    private String  OnBoardingStatus;
    private Integer OnBoardingQuestionCount;
    private String  DailyPulseQuestionStatus;
    private Integer DailyPulseQuestionCount;
    private Integer DailyDailyQuestionCount;

    public Integer getDailyDailyQuestionCount() {
        return DailyDailyQuestionCount;
    }

    public void setDailyDailyQuestionCount(Integer dailyDailyQuestionCount) {
        DailyDailyQuestionCount = dailyDailyQuestionCount;
    }

    private String ValidationType;

    public String getValidationType() {
        return ValidationType;
    }

    public void setValidationType(String validationType) {
        ValidationType = validationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstantMessagingData getMember() {
        return member;
    }

    public void setMember(InstantMessagingData member) {
        this.member = member;
    }

    public String getOnBoardingStatus() {
        return OnBoardingStatus;
    }

    public void setOnBoardingStatus(String onBoardingStatus) {
        OnBoardingStatus = onBoardingStatus;
    }

    public Integer getOnBoardingQuestionCount() {
        return OnBoardingQuestionCount;
    }

    public void setOnBoardingQuestionCount(Integer onBoardingQuestionCount) {
        OnBoardingQuestionCount = onBoardingQuestionCount;
    }

    public String getDailyPulseQuestionStatus() {
        return DailyPulseQuestionStatus;
    }

    public void setDailyPulseQuestionStatus(String dailyPulseQuestionStatus) {
        DailyPulseQuestionStatus = dailyPulseQuestionStatus;
    }

    public Integer getDailyPulseQuestionCount() {
        return DailyPulseQuestionCount;
    }

    public void setDailyPulseQuestionCount(Integer dailyPulseQuestionCount) {
        DailyPulseQuestionCount = dailyPulseQuestionCount;
    }
}

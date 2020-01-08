package com.realTalk.bot.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MemberQuery {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Member member;

    private Boolean OnBoardingStatus;
    private Integer OnBoardingQuestionCount;
    private Boolean DailyPulseQuestionStatus;
    private Integer DailyPulseQuestionCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Boolean getOnBoardingStatus() {
        return OnBoardingStatus;
    }

    public void setOnBoardingStatus(Boolean onBoardingStatus) {
        OnBoardingStatus = onBoardingStatus;
    }

    public Integer getOnBoardingQuestionCount() {
        return OnBoardingQuestionCount;
    }

    public void setOnBoardingQuestionCount(Integer onBoardingQuestionCount) {
        OnBoardingQuestionCount = onBoardingQuestionCount;
    }

    public Boolean getDailyPulseQuestionStatus() {
        return DailyPulseQuestionStatus;
    }

    public void setDailyPulseQuestionStatus(Boolean dailyPulseQuestionStatus) {
        DailyPulseQuestionStatus = dailyPulseQuestionStatus;
    }

    public Integer getDailyPulseQuestionCount() {
        return DailyPulseQuestionCount;
    }

    public void setDailyPulseQuestionCount(Integer dailyPulseQuestionCount) {
        DailyPulseQuestionCount = dailyPulseQuestionCount;
    }
}

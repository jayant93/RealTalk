package com.realTalk.bot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserQueryInfo {


    @Id
    @GeneratedValue
    public Long UserId;

    public String slackId;

    public String name;

    public String email;

    public String teamName;



//    private String teamId;
//
//    private String userId;

    public String timeZone;

    public String startAtCompany;

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    public String getStartAtCompany() {
        return startAtCompany;
    }

    public void setStartAtCompany(String startAtCompany) {
        this.startAtCompany = startAtCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}

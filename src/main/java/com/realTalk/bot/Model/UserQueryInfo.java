package com.realTalk.bot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserQueryInfo {


    @Id
    @GeneratedValue
    private Long UserId;

    private String name;

    private String email;

    private String teamName;

    private String startAtCompany;

//    private String teamId;
//
//    private String userId;

    private String timeZone;


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

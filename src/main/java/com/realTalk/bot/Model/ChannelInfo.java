package com.realTalk.bot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChannelInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String channelId;

    private String UserSlackId;

    private int DailyPulseCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDailyPulseCount() {
        return DailyPulseCount;
    }

    public void setDailyPulseCount(int dailyPulseCount) {
        DailyPulseCount = dailyPulseCount;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String ChannelId) {
         channelId = ChannelId;
    }

    public String getUserSlackId() {
        return UserSlackId;
    }

    public void setUserSlackId(String userSlackId) {
        UserSlackId = userSlackId;
    }
}

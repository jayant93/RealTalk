package com.realTalk.bot.BotController;

import com.realTalk.bot.Model.InstantMessagingData;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Service.OnBoardingService;
import com.realTalk.bot.Service.SlackWorkspaceInfoCollect;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

@Component
public class SlackBot2 extends Bot {

    @Value("${slackBotToken}")
    String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Autowired
    Environment environment;

    @Autowired
    SlackWorkspaceInfoCollect service;

    @Autowired
    OnBoardingService onBoardingService;


    WebSocketSession socketSession;

    @Controller(events = {EventType.CHANNEL_JOINED,EventType.DIRECT_MENTION})
    public void JoinChannel(WebSocketSession session, Event event) throws IOException {

        socketSession = session;
        CollectWorkspaceInfo info = new CollectWorkspaceInfo(environment,service);
        info.collectWorkspaceInfo();

        List<InstantMessagingData> members =  onBoardingService.getUserByChannelId(event.getChannelId());

        for(int i=0;i<members.size();i++){
            Message WelcomOnBoardingMessage = new Message();
                WelcomOnBoardingMessage.setType("message");
                WelcomOnBoardingMessage.setUser(members.get(i).getUser());
                WelcomOnBoardingMessage.setText("Welcome to Real Talk type yes for onBoarding or No to Do it later");
                WelcomOnBoardingMessage.setChannel(members.get(i).getId());
                session.sendMessage(new TextMessage(WelcomOnBoardingMessage.toJSONString()));
        }
        reply(session, event, new Message("Welcome To RealTalk"));


    }

    @Controller(events = EventType.DIRECT_MESSAGE)
    public void chat(WebSocketSession session,Event event){



    }


}

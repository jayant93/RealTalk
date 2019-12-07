package com.realTalk.bot.BotController;



import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Service.QuestionsAndAnswersService;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;


@Component
public class SlackBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

    @Value("${slackBotToken}")
    String slackToken;

    @Autowired
    QuestionsAndAnswersService QueryService;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    List<Questions> questions = new ArrayList<>();
    int count = 0;
    @Controller(events = {EventType.DIRECT_MENTION,EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) {

        if (event.getText().contains("yes") && count == 0) {
            questions = QueryService.getAuthQuestions("Authentication");
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }
        if (event.getText().contains("no") && count == 0) {
            questions = QueryService.getAuthQuestions("Authentication");
            count++;
            reply(session, event, new Message("Your Account is not confirmed!\nHave a nice day"));
            return;
        }

        if(count == 1){
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }
        if(count == 2){
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }  if(count == 3){
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }  if(count == 4){
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }  if(count == 5){
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
            return;
        }

    }


    @Controller(events = EventType.MESSAGE)
    public void onReceiveMessage(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hi, this is a message!"));
    }


    @Controller(events = EventType.BOT_ADDED)
    public void WelcomeMessage(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hi,Welcome to RealTalk for proving your Authentication" +
                "you have to answer some really simple Questions.Are you ready?"));
    }

    @Controller(events = EventType.PIN_ADDED)
    public void WelcomeMessage2(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hi,Welcome to RealTalk for proving your Authentication" +
                " you have to answer some really simple Questions.Are you ready?" +
                "\n Type yes to proceed else no."));
    }
}

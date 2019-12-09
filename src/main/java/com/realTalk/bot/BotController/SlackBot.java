package com.realTalk.bot.BotController;



import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Model.UserQueryInfo;
import com.realTalk.bot.Service.QuestionsAndAnswersService;
import com.realTalk.bot.Service.UserInfoManipulationService;
import com.realTalk.bot.Utilities.DateValidatorUsingDateFormat;
import com.realTalk.bot.helpers.staticHelpers.TempUserInfo;
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


   // TempUserInfo userInfo = new TempUserInfo();

    @Autowired
    UserInfoManipulationService userInfoManipulationService;

    Boolean AuthProcessStatus = false;

    int invalidDateCount = 0;

    @Controller(events = {EventType.DIRECT_MENTION,EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) {

        if (event.getText().contains("yes") && count == 0) {
            AuthProcessStatus = true;
            questions = QueryService.getAuthQuestions("Authentication");
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }
        if (event.getText().contains("no") && count == 0) {
            //saving name of user

            questions = QueryService.getAuthQuestions("Authentication");
            count++;
            reply(session, event, new Message("Your Account is not confirmed!\nHave a nice day"));
            return;
        }
        if(count == 0 && AuthProcessStatus == true){
            TempUserInfo.setName(event.getText());
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }
        if(count == 1 && AuthProcessStatus == true){
            TempUserInfo.setEmail(event.getText());
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }
        if(count == 2 && AuthProcessStatus == true){
            TempUserInfo.setTeamName(event.getText());
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));

            return;
        }  if(count == 3 && AuthProcessStatus == true){
            TempUserInfo.setTimeZone(event.getText());
            logger.info("reply By user : "+event.getText().toString());
            count++;
            reply(session, event, new Message(questions.get(count).getQuestion()));
            return;
        }  if(count == 4 && AuthProcessStatus == true){
            DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
            //if user enters a valid date
            if(validator.isValid(event.getText()))
            {
                if(invalidDateCount != 3) {
                    TempUserInfo.setDateStartAtCompany(event.getText());
                    userInfoManipulationService.saveUserQueryInfoToDB(questions);
                    logger.info("reply By user : " + event.getText().toString());
                    reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                    count = 0;
                    AuthProcessStatus = false;
                    return;
                }
                else{
                    reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                    count = 0;
                    AuthProcessStatus = false;
                    return;
                }
            }
            else{
                count = 4;
                invalidDateCount++;
                    reply(session, event, new Message(" Uh oh, I was expecting you to say something like 11/1/2018. \n Would you try that again please."));
            return;
            }


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

//        logger.info("Slack ID : "+event.getUser().getId()+"\n" +
//                "Username : "+event.getUser().getProfile().getFirstName()+" "+event.getUser().getProfile().getLastName()+"\n" +
//                "Phone Number : "+event.getUser().getProfile().getPhone());




        reply(session, event, new Message("Hi,Welcome to RealTalk for proving your Authentication" +
                " you have to answer some really simple Questions.Are you ready?" +
                "\n Type yes to proceed else no."));



    }
}

package com.realTalk.bot.BotController;



import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Service.QuestionsAndAnswersService;
import com.realTalk.bot.Service.UserInfoManipulationService;
import com.realTalk.bot.Utilities.DateValidatorUsingDateFormat;
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
import java.util.LinkedList;
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



    List QueryAnswers = new LinkedList();

    int count = 0;


   // TempUserInfo userInfo = new TempUserInfo();

    @Autowired
    UserInfoManipulationService userInfoManipulationService;

    Boolean AuthProcessStatus = false;

    int invalidDateCount = 0;

    Boolean Validator = false;

    @Controller(events = {EventType.DIRECT_MENTION,EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) throws IllegalAccessException {

//Questions saved in db
        List<Questions> questions = QueryService.getAuthQuestions("Authentication");
        //Dynamic logic for asking quesions

        if (Validator){
            DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
            //if user enters a valid date
            if (validator.isValid(event.getText()) || invalidDateCount == 3) {
                           if( validator.isValid(event.getText())){
                                   QueryAnswers.add(event.getText());
                                     }else{
                                   QueryAnswers.add("");
                               }
                           Validator = false;
                            //reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                            count++;
                            onReceiveDM(session,event);
                            return;
            }
            else {
                invalidDateCount++;
                reply(session, event, new Message(" Uh oh, I was expecting you to say something like 11/1/2018. \n Would you try that again please."));
                return;
            }
    }
            if(AuthProcessStatus == true || event.getText().contains("yes")) {
                AuthProcessStatus = true;
                QueryAnswers.add(event.getText());
                if (count == questions.size()+1) {
                    reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                    count = 0;
                    AuthProcessStatus = false;
                    Validator = false;
                    userInfoManipulationService.saveUserQueryInfoToDB(questions, QueryAnswers,Long.valueOf(invalidDateCount));
                    QueryAnswers = new LinkedList();
                } else if (count < questions.size()+1) {
                    if (!questions.get(count).getValidationType().equalsIgnoreCase("DateValidation")) {
                        reply(session, event, new Message(questions.get(count).getQuestion()));
                        count++;
                    } else if (questions.get(count).getValidationType().equalsIgnoreCase("DateValidation")) {
                        reply(session, event, new Message(questions.get(count).getQuestion()));
                        Validator = true;
                        count++;
                    }
                  }
                }

        //saving name of user
            if(event.getText().contains("no") && AuthProcessStatus == false) {
                    questions = QueryService.getAuthQuestions("Authentication");
                    count++;
                    reply(session, event, new Message("Your Account is not confirmed!\nHave a nice day"));
                    return;
                }
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

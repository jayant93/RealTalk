package com.realTalk.bot.BotController;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.realTalk.bot.Model.ChannelInfo;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Service.QuestionsAndAnswersService;
import com.realTalk.bot.Service.UserInfoManipulationService;
import com.realTalk.bot.Utilities.DateValidatorUsingDateFormat;
import jdk.nashorn.api.scripting.ScriptUtils;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.BotWebSocketHandler;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


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



    LinkedHashMap<Questions,String> QueryAnswers = new LinkedHashMap<Questions,String>();

    int count = 0;


   // TempUserInfo userInfo = new TempUserInfo();

    @Autowired
    UserInfoManipulationService userInfoManipulationService;

    Boolean AuthProcessStatus = false;

    int invalidDateCount = 0;

    Boolean Validator = false;
    Boolean TeamEmailCollectionStatus = false;
    Boolean HaveATeamCheck = false;
    Boolean HaveAManagerCheck = false;
    Boolean SendAdminMailToBeAnAdmin = false;
    Boolean CollectCompanyLeadersEmail= false;
    Boolean CollectAdminEmails = false;

    Boolean IsAdminCheck = false;
    List TeamEmails = new LinkedList();
    List CompanyLeadersEmailList = new LinkedList();
    List CompanyAdminEmailList = new LinkedList();

    Boolean DailyPulseStatus = false;

    List DailyPulseAnswers = new LinkedList();
    Questions ActivePulseQuestion = null;

/*
functionality for onboarding QuestionsCollectAdminEmails
 */
    @Controller(events = {EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) throws IllegalAccessException {


                    if(DailyPulseStatus && count==0){
                            DailyPulseAnswers.add(event.getText());
                            count++;
                            reply(session,event,new Message(" Please feel free to leave any additional comments. Remember, they're anonymous too."));
                            return;
                    }else if(DailyPulseStatus && count == 1){
                        DailyPulseAnswers.add(event.getText());
                        count =0;
                        DailyPulseStatus = false;
                        reply(session,event,new Message("Thankyou for your precious time"));
                        QueryService.saveDailyPulseAnswers(ActivePulseQuestion,DailyPulseAnswers);
                        ActivePulseQuestion = null;
                        return;
                    }

                            if(!AuthProcessStatus && count==0){
                                if(event.getText().equalsIgnoreCase("Yes") && count ==0){
                                    AuthProcessStatus = true;
                                }else{
                                    AuthProcessStatus = false;
                                }
                            }

                    //Questions saved in db
                            List<Questions> questions = QueryService.getAuthQuestions();
                            //Dynamic logic for asking quesions


                                if(IsAdminCheck){
                                    QueryAnswers.put(questions.get(count-1),event.getText());
                                    if(event.getText().equalsIgnoreCase("yes")){
                                        count++;
                                        reply(session, event, new Message(questions.get(count).getQuestion()));
                                        count++;
                                        IsAdminCheck=false;
                                        return;
                                    }
                                    else{
                                        reply(session, event, new Message(questions.get(count).getQuestion()));
                                        IsAdminCheck = false;
                                        count++;
                                        return;
                                    }
                                }


                                    if(HaveAManagerCheck){
                                        QueryAnswers.put(questions.get(count-1),event.getText());
                                        if(event.getText().equalsIgnoreCase("yes")){
                                            reply(session, event, new Message(questions.get(count).getQuestion()));
                                            count++;
                                            HaveAManagerCheck = false;
                                            return;

                                        }
                                        else if(event.getText().equalsIgnoreCase("No")){
                                            count++;
                                            HaveAManagerCheck = false;
                                            onReceiveDM(session,event);
                                            return;
                                        }
                                    }



                            if(HaveATeamCheck){

                                if(event.getText().contains("yes")) {
                                    QueryAnswers.put(questions.get(count-1),event.getText());
                                    TeamEmailCollectionStatus = true;
                                    HaveATeamCheck = false;
                                    reply(session, event, new Message(questions.get(count).getQuestion()));
                                } else if(event.getText().equalsIgnoreCase("No")){
                                    count++;
                                    TeamEmailCollectionStatus = false;
                                    HaveATeamCheck = false;
                                    onReceiveDM(session,event);
                                    return;
                                }
                                HaveATeamCheck = false;


                                return;
                            }


                            //Collecting Company Leaders email of the team
                            if(CollectAdminEmails && !event.getText().equalsIgnoreCase("Done")){
                                CompanyAdminEmailList.add(event.getText());
                                return;
                            }else if(CollectAdminEmails && event.getText().equalsIgnoreCase("Done")){
                                CollectAdminEmails = false;
                                count++;
                                onReceiveDM(session,event);
                                return;
                            }

                            //Collecting Company Leaders email of the team
                            if(CollectCompanyLeadersEmail && !event.getText().equalsIgnoreCase("Done")){
                                CompanyLeadersEmailList.add(event.getText());
                                return;
                            }else if(CollectCompanyLeadersEmail && event.getText().equalsIgnoreCase("Done")){
                                CollectCompanyLeadersEmail = false;
                                count++;
                                onReceiveDM(session,event);
                                return;
                            }


                            //Collecting emails of the team
                            if(TeamEmailCollectionStatus && !event.getText().equalsIgnoreCase("Done")){
                                TeamEmails.add(event.getText());
                                return;
                            }else if(TeamEmailCollectionStatus && event.getText().equalsIgnoreCase("Done")){
                                TeamEmailCollectionStatus = false;
                                HaveATeamCheck = false;
                                count++;
                                onReceiveDM(session,event);
                                return;
                            }

                if (Validator){
                                DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
                                //if user enters a valid date
                                    if (validator.isValid(event.getText()) || invalidDateCount == 3) {
                                                   if( validator.isValid(event.getText())){
                                                        //   QueryAnswers.add(event.getText());
                                                             }else{
                                                          // QueryAnswers.add("");
                                                       }
                                                         Validator = false;
                                                    //reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                                                       onReceiveDM(session,event);
                                                        return;
                                            }
                                            else {
                                                invalidDateCount++;
                                                reply(session, event, new Message(" Uh oh, I was expecting you to say something like 11/1/2018. \n Would you try that again please."));
                                                return;
                                            }
                                 }


                    if(AuthProcessStatus == true) {
                        if(count == 0){
                            //Do Nothing
                        }else if(count == questions.size()){
                            //Do Nothing
                            }
                        else{
                            QueryAnswers.put(questions.get(count-1),event.getText());
                             }
                            if (count == questions.size()) {
                                reply(session, event, new Message("Thankyou for your precious Time.\nWe will get back back to you with Account confirmation Information.\nHave a nice Day!"));
                                count = 0;
                                AuthProcessStatus = false;
                                Validator = false;
                                userInfoManipulationService.saveUserQueryInfoToDB(QueryAnswers,Long.valueOf(invalidDateCount));
                                QueryAnswers = new LinkedHashMap<>();
                                }
                                    else if (count < questions.size()) {
                                        if (questions.get(count).getValidationType().equalsIgnoreCase("DateValidation")) {
                                                reply(session, event, new Message(questions.get(count).getQuestion()));
                                                Validator = true;
                                                count++;
                                                }else if(questions.get(count).getExtraCheck().equalsIgnoreCase("HaveAManager")){
                                                HaveAManagerCheck = true;
                                                reply(session, event, new Message(questions.get(count).getQuestion()));
                                                count++;
                                            }

                                             else if(questions.get(count).getExtraCheck().equalsIgnoreCase("CollectAdminEmails")){
                                               CollectAdminEmails = true;
                                                reply(session, event, new Message(questions.get(count).getQuestion()));
                                                count++;
                                                 }
                                             else if(questions.get(count).getExtraCheck().equalsIgnoreCase("IsAdminCheck")){
                                                    IsAdminCheck = true;
                                                  reply(session, event, new Message(questions.get(count).getQuestion()));
                                                 count++;
                                                   }
                                            else if(questions.get(count).getExtraCheck().equalsIgnoreCase("CollectCompanyLeadersEmail")){
                                                CollectCompanyLeadersEmail = true;
                                                reply(session, event, new Message(questions.get(count).getQuestion()));
                                                count++;
                                            }
                                             else if(questions.get(count).getExtraCheck().equalsIgnoreCase("HaveATeam")){
                                                   HaveATeamCheck = true;
                                                    reply(session, event, new Message(questions.get(count).getQuestion()));
                                                    count++;
                                                 }
                                                 else if (!questions.get(count).getValidationType().equalsIgnoreCase("DateValidation")) {
                                                     reply(session, event, new Message(questions.get(count).getQuestion()));
                                                     count++;
                                                 }
                                             }
                        }

        //saving name of user
            if(event.getText().contains("no") && AuthProcessStatus == false) {
                    questions = QueryService.getAuthQuestions();
                    count++;
                    reply(session, event, new Message("Your Account is not confirmed!\nHave a nice day"));
                    return;
                }
    }


    @Controller(events = EventType.BOT_ADDED)
    public void WelcomeMessage(WebSocketSession session, Event event) {
        DailyPulseSession = session;
        channelId = event.getChannelId();
        reply(session, event, new Message("Hi,Welcome to RealTalk for proving your Authentication" +
                "you have to answer some really simple Questions.Are you ready?"));
    }

    String channelId=null;
    WebSocketSession DailyPulseSession =null;
    @Controller(events = EventType.PIN_ADDED)
    public void WelcomeMessage2(WebSocketSession session, Event event) {

            DailyPulseSession = session;

        userInfoManipulationService.saveChannelInfo(event.getChannelId(),event.getUserId());

        reply(session, event, new Message("Welcome to RealTalk for proving your Authentication" +
                " you have to answer some really simple Questions.Are you ready?" +
                "\n Type yes to proceed else no."));



    }


    int i =0;
    ChannelInfo info = new ChannelInfo();
    @Scheduled(cron = " 0 9 * * *")
    public void scheduleTaskWithFixedRate() throws IOException {

        List<ChannelInfo> channelInfoList = userInfoManipulationService.getChannels();
        String  PulseQuestion = "On a scale of 1 to 5. 1=Strongly Disagree, 2=Disagree, 3=Neutral, 4=Agree, 5=Strongly Agree.";
        List<Questions> pulseQuestionsList =  QueryService.getPulseQuestion();


            Message dailyPulseMessage = new Message();

            if(i<channelInfoList.size()) {
                info = channelInfoList.get(i);
            }else{}

            if(i<pulseQuestionsList.size()) {
                ActivePulseQuestion = pulseQuestionsList.get((info.getDailyPulseCount()));
            }else{

            }
               PulseQuestion = PulseQuestion+"\n"+pulseQuestionsList.get((info.getDailyPulseCount())).getQuestion();

                dailyPulseMessage.setChannel(info.getChannelId());
                dailyPulseMessage.setType("message");
                dailyPulseMessage.setText(PulseQuestion);
                info.setDailyPulseCount(pulseQuestionsList.get((info.getDailyPulseCount()+1)).getQuestionOrder());
                DailyPulseStatus = true;

            DailyPulseSession.sendMessage(new TextMessage(dailyPulseMessage.toJSONString()));
            i++;
            }
    }






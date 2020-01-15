package com.realTalk.bot.BotController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.realTalk.bot.Model.InstantMessagingData;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Model.MemberQuery;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.MemberQueryRepository;
import com.realTalk.bot.Repositories.QuestionsRepository;
import com.realTalk.bot.Service.DailyPulseService;
import com.realTalk.bot.Service.OnBoardingService;
import com.realTalk.bot.Service.SlackWorkspaceInfoCollect;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

import static com.realTalk.bot.Utilities.Status.STARTED;

@Component
public class SlackBot extends Bot {

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
    MemberQueryRepository memberQueryRepo;
    @Autowired
    QuestionsRepository QuestionsRepo;
    @Autowired
    SlackWorkspaceInfoCollect service;

    @Autowired
    OnBoardingService onBoardingService;

    @Autowired
    DailyPulseService dailyPulseService;


    WebSocketSession Ssession;

    WebSocketSession socketSession;

    public final Logger log = LoggerFactory.getLogger(SlackBot.class);

    @Controller(events = {EventType.CHANNEL_JOINED, EventType.DIRECT_MENTION})
    public void JoinChannel(WebSocketSession session, Event event) throws IOException {

        socketSession = session;
        Ssession = socketSession;
        CollectWorkspaceInfo info = new CollectWorkspaceInfo(environment, service);
        info.collectWorkspaceInfo();

        List<InstantMessagingData> members = onBoardingService.getUserByChannelId(event.getChannelId());
        members = onBoardingService.saveOnBoardingMembers(members);
        for (int i = 0; i < members.size(); i++) {
            Message WelcomOnBoardingMessage = new Message();
            WelcomOnBoardingMessage.setType("message");
            WelcomOnBoardingMessage.setUser(members.get(i).getUser());
            MemberQuery memberQuery = memberQueryRepo.findByMemberId(members.get(i).getUser());
            List<Questions> questions = QuestionsRepo.findOnBoardingQuestions();
            memberQuery.setOnBoardingStatus(STARTED);


            WelcomOnBoardingMessage.setText("Welcome to Real Talk!\n" +
                    "RealTalk improves your workplace by allowing employees to provide anonymous feedback to managers and leaders\n" +
                    "Lets get you setup...\n" +
                    "" + questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion());


            memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
            memberQuery = memberQueryRepo.save(memberQuery);
            WelcomOnBoardingMessage.setChannel(members.get(i).getId());
            session.sendMessage(new TextMessage(WelcomOnBoardingMessage.toJSONString()));
        }

        reply(session, event, new Message("Welcome To RealTalk"));


    }

    @Controller(events = EventType.DIRECT_MESSAGE)
    public void chat(WebSocketSession session, Event event) {

        reply(session, event, new Message(onBoardingService.getOnBoardQuestion(event)));

    }

    public void finalize() {
        log.info("revoking session");
        Ssession = socketSession; // Putting the reference id

        // of the current object
        // into the static variable

    }


    @Scheduled(cron = "0 */5 * * * *")
   // @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void DailyPulseMessage() throws IOException {

        List<MemberQuery> members = onBoardingService.getOnBoardedMembers();
        if (members != null && Ssession != null) {
            log.info("Sending daily pulse Question");
            for (int i = 0; i < members.size(); i++) {
                Message WelcomOnBoardingMessage = new Message();
                WelcomOnBoardingMessage.setType("message");
                WelcomOnBoardingMessage.setUser(members.get(i).getMember().getUser());
                WelcomOnBoardingMessage.setText(dailyPulseService.getQuestion(members.get(i).getMember().getUser()) + "" +
                        "\n1=Strongly Disagree, 2=Disagree, 3=Neutral, 4=Agree, 5=Strongly Agree.");
                WelcomOnBoardingMessage.setChannel(members.get(i).getMember().getId());
                Ssession.sendMessage(new TextMessage(WelcomOnBoardingMessage.toJSONString()));
            }
        }
    }
}

package com.realTalk.bot.Service;

import com.realTalk.bot.Model.DailyPulseResponse;
import com.realTalk.bot.Model.MemberQuery;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.*;
import com.realTalk.bot.Utilities.Status;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyPulseService implements Status {


    @Autowired
    InstantMessagingRepository instantMessagingRepo;
    @Autowired
    MemberQueryRepository memberQueryRepo;
    @Autowired
    QuestionsRepository QuestionsRepo;
    @Autowired
    MemberRepository memberRepo;
    @Autowired
    DailyPulseResponseRepository dailyPulseResponseRepository;

    public String getQuestion(String userId) {
        String Question = null;
        MemberQuery memberQuery = memberQueryRepo.findByMemberId(userId);
        List<Questions> questions = QuestionsRepo.findDailyPulseQuestion();

        if (memberQuery.getDailyPulseQuestionStatus().equalsIgnoreCase(STARTED) && memberQuery.getDailyDailyQuestionCount() == 0) {
            Question = questions.get(memberQuery.getDailyPulseQuestionCount()).getQuestion();
            memberQuery.setDailyDailyQuestionCount(memberQuery.getDailyDailyQuestionCount() + 1);
            memberQuery.setDailyPulseQuestionCount(memberQuery.getDailyPulseQuestionCount() + 1);
            memberQueryRepo.save(memberQuery);
            return Question;
        }

        return Question;
    }


    public String getDailyPulseResponse(Event event) {
        String Reply = null;
        MemberQuery memberQuery = memberQueryRepo.findByMemberId(event.getUserId());
        List<Questions> questions = QuestionsRepo.findDailyPulseQuestion();


        if (memberQuery.getDailyPulseQuestionStatus().equalsIgnoreCase(STARTED) && memberQuery.getDailyDailyQuestionCount() == 1) {
            Reply = "Please feel free to leave any additional comments. Remember, they're anonymous too.";

            DailyPulseResponse response = new DailyPulseResponse();
            response.setNumericResponse(event.getText());
            response.setQuestion(questions.get(memberQuery.getDailyPulseQuestionCount() - 1).getQuestion());
            response.setUserId(event.getUserId());
            response.setActiveStatus(true);
            dailyPulseResponseRepository.save(response);

            memberQuery.setDailyDailyQuestionCount(memberQuery.getDailyDailyQuestionCount() + 1);
            memberQueryRepo.save(memberQuery);
            return Reply;
        }
        if (memberQuery.getDailyPulseQuestionStatus().equalsIgnoreCase(STARTED) && memberQuery.getDailyDailyQuestionCount() == 2) {
            Reply = "Thankyou for your Precious Time";
            DailyPulseResponse response = new DailyPulseResponse();
            response = dailyPulseResponseRepository.findActiveResponse(event.getUserId());
            response.setTextResponse(event.getText());
            // response.setQuestion(questions.get(memberQuery.getDailyDailyQuestionCount()+1).getQuestion());
            response.setUserId(event.getUserId());
            response.setActiveStatus(false);
            dailyPulseResponseRepository.save(response);

            memberQuery.setDailyDailyQuestionCount(0);
            memberQueryRepo.save(memberQuery);
            return Reply;
        }


        return Reply;
    }

}

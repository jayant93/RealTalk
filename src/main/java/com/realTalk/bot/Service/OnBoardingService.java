package com.realTalk.bot.Service;

import com.realTalk.bot.Model.*;
import com.realTalk.bot.Repositories.*;
import com.realTalk.bot.Utilities.DateValidatorUsingDateFormat;
import com.realTalk.bot.Utilities.Status;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class OnBoardingService implements Status {

    public static final Logger log = LoggerFactory.getLogger(OnBoardingService.class);
    @Autowired
    ChannelRepository channelRepo;
    @Autowired
    InstantMessagingRepository instantMessagingRepo;
    @Autowired
    MemberQueryRepository memberQueryRepo;
    @Autowired
    QuestionsRepository QuestionsRepo;
    @Autowired
    QuestionResponseRepository questionResponseRepository;
    @Autowired
    MemberRepository memberRepo;
    @Autowired
    DailyPulseService dailyPulseService;

    public List<InstantMessagingData> getUserByChannelId(String channelId) {

        List<InstantMessagingData> imsList = new ArrayList<>();
        Optional<Channel> channel = channelRepo.findById(channelId);
        for (int i = 0; i < channel.get().getMembers().size(); i++) {
            InstantMessagingData imsData = new InstantMessagingData();
            imsData = instantMessagingRepo.findByUser(channel.get().getMembers().get(i).getId());
            if (imsData == null) {
                continue;
            } else {
                imsList.add(imsData);
            }


        }
        return imsList;
    }


    public  List<InstantMessagingData> saveOnBoardingMembers(List<InstantMessagingData> members) {

        List<InstantMessagingData> result = new ArrayList<>();
        log.info("Saving OnBoarding details for users sent welcome message");
        Iterator<InstantMessagingData> it = members.iterator();
        while (it.hasNext()) {
            InstantMessagingData data = it.next();
            MemberQuery testData = memberQueryRepo.findByMemberId(data.getUser());
            if(testData==null){
            MemberQuery queries = new MemberQuery();
            queries.setDailyPulseQuestionCount(0);
            queries.setMember(data);
            queries.setOnBoardingQuestionCount(0);
            queries.setDailyDailyQuestionCount(0);
            queries.setOnBoardingStatus(NOTSTARTED);
            queries.setDailyPulseQuestionStatus(NOTSTARTED);
            memberQueryRepo.save(queries);
            result.add(data);
            }else{
                continue;
            }
        }
        return result;
    }

    //GEtting onBoarding question
    public String getOnBoardQuestion(Event event) {



        String Question = null;

        MemberQuery memberQuery = memberQueryRepo.findByMemberId(event.getUserId());

        if(memberQuery.getDailyPulseQuestionStatus().equalsIgnoreCase(STARTED) && memberQuery.getDailyDailyQuestionCount()>=1){
            Question =   dailyPulseService.getDailyPulseResponse(event);
            return Question;
        }


        List<Questions> questions = QuestionsRepo.findOnBoardingQuestions();



        /*
        OnBoarding Complete
         */
        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(COMPLETE)) {
            Question = "OnBoarding Process is Completed To Update your Information type 'UPDATE INFO'";
            return Question;
        }
            /*
            OnBoarding not yet started
            */
        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(NOTSTARTED) && event.getText().equalsIgnoreCase("yes")) {
            //memberQuery.setDailyPulseQuestionStatus(STARTED);
            memberQuery.setOnBoardingStatus(STARTED);
            memberQuery = memberQueryRepo.save(memberQuery);

        }

        /*
         *ONBoarding Started
         */
        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(STARTED)
                && memberQuery.getOnBoardingQuestionCount() == 0) {

            Question = questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion();
            memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
            memberQuery = memberQueryRepo.save(memberQuery);
            return Question;
        }

        /*
         *OnBoarding
         */
        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(STARTED)
                && memberQuery.getOnBoardingQuestionCount() < questions.size() - 1) {

            QuestionsResponses responses = new QuestionsResponses();
            if (questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getExtraCheck().equalsIgnoreCase("none")) {
                Question = questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion();
                log.info("Saving response to Questions ");

                responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
                responses.setAnswer(event.getText());
                responses.setQuestionType(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestionType());
                responses.setUser(event.getUserId());
                responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
                questionResponseRepository.save(responses);

                memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
                memberQuery = memberQueryRepo.save(memberQuery);
                return Question;
            } else {
                /*
                Here Starts Logic for Different Types Of Validations
                 */
                Question = CheckValidation(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getExtraCheck()
                        , event
                        , questions
                        , memberQuery,
                        responses);

                return Question;
            }


        }


        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(STARTED)
                && memberQuery.getOnBoardingQuestionCount() == questions.size() - 1) {
            QuestionsResponses responses = new QuestionsResponses();
            Question = questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion();
            log.info("Saving response to Questions ");
            Question = Question.replace("[EmployeeName]",memberRepo.findById(event.getUserId()).get().getReal_name());
            responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
            responses.setAnswer(event.getText());
            responses.setUser(event.getUserId());
            responses.setQuestionType(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestionType());
            responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
            questionResponseRepository.save(responses);

            memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
            memberQuery = memberQueryRepo.save(memberQuery);
            return Question;

        }
          /*
            Changing Status TO COmplete
             */
        if (memberQuery.getOnBoardingStatus().equalsIgnoreCase(STARTED)
                && memberQuery.getOnBoardingQuestionCount() == questions.size()) {
            QuestionsResponses responses = new QuestionsResponses();
            responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
            responses.setAnswer(event.getText());
            responses.setUser(event.getUserId());
            responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
            responses.setQuestionType(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestionType());
            questionResponseRepository.save(responses);
            memberQuery.setOnBoardingStatus(COMPLETE);
            memberQuery.setDailyPulseQuestionStatus(STARTED);
            memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
            memberQuery = memberQueryRepo.save(memberQuery);
            Question = "You have been successfully OnBoarded thankyou..";

        }


        return Question;

    }


    public String CheckValidation(String ValidationType
            , Event event,
                                  List<Questions> questions
            , MemberQuery memberQuery
            , QuestionsResponses responses) {
        String Question = null;

        //If Date Validation is active
        if (ValidationType.equalsIgnoreCase("DateValidation")) {
            DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
            if (validator.isValid(event.getText())) {
                Question = questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion();
                responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
                responses.setQuestionType(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestionType());
                responses.setAnswer(event.getText());
                responses.setUser(event.getUserId());
                responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
                questionResponseRepository.save(responses);
                memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
                memberQuery = memberQueryRepo.save(memberQuery);
            } else {
                Question = "Uh oh, I was expecting you to say something like 11/1/2018. \n Would you try that again please.";
            }
        }


        if (ValidationType.equalsIgnoreCase("HaveATeam") || ValidationType.equalsIgnoreCase("HaveAManager")) {

            if (event.getText().trim().equalsIgnoreCase("Yes")) {
                Question = questions.get(memberQuery.getOnBoardingQuestionCount()).getQuestion();
                responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
                responses.setQuestionType(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestionType());
                responses.setAnswer(event.getText());
                responses.setUser(event.getUserId());
                responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
                questionResponseRepository.save(responses);
                memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 1);
                memberQuery = memberQueryRepo.save(memberQuery);
            } else {
                Question = questions.get(memberQuery.getOnBoardingQuestionCount() + 1).getQuestion();
                responses.setQuestion(questions.get(memberQuery.getOnBoardingQuestionCount() - 1));
                responses.setAnswer(event.getText());
                responses.setUser(event.getUserId());
                responses.setQuestion_text(questions.get(memberQuery.getOnBoardingQuestionCount() - 1).getQuestion());
                questionResponseRepository.save(responses);
                memberQuery.setOnBoardingQuestionCount(memberQuery.getOnBoardingQuestionCount() + 2);
                memberQuery = memberQueryRepo.save(memberQuery);
            }
        }


        return Question;
    }

    public List<MemberQuery> getOnBoardedMembers() {
        return memberQueryRepo.findOnBoardedMembers();
    }
}

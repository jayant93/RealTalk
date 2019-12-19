package com.realTalk.bot.Service;

import com.realTalk.bot.Model.*;
import com.realTalk.bot.Repositories.*;

import org.hibernate.boot.jaxb.hbm.spi.Adapter4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.DirectFieldAccessFallbackBeanWrapper;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/*
for saving user info provided while
onBoarding
 */

@Service
public class UserInfoManipulationService {

    @Autowired
    UserQueryInfoRepository userQueryRepo;

    @Autowired
    AnswersRepository answersRepo;

    @Autowired
    CompanyEmployeeDetailsRepo companyEmployeeDetailsRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    EmployeePositionRepo employeePositionRepo;

    @Autowired
    ChannelInfoRepo channelInfoRepo;


    /*
    This method will be used to saved OnBoarding information
    About the user Also saving Answers to particular questions
     */
    public void saveUserQueryInfoToDB(LinkedHashMap answersList, Long invalidCount) throws IllegalAccessException {

        try {
            Answers answers = null;
            ArrayList<Answers> DbAnswersList = new ArrayList<>();
            ArrayList<Questions> QuestionList = new ArrayList<Questions>(answersList.keySet());
            ArrayList<String> AnswerList = new ArrayList<String>(answersList.values());
            //Instances for creating Analytics information
            Company companyDetails = new Company();


            companyDetails.setName(AnswerList.get(getAnswerIndex(QuestionList, "Company's name")));
            companyDetails.setLocation(AnswerList.get(getAnswerIndex(QuestionList, "country are you located in")));
            companyDetails.setSignupDate(AnswerList.get(getAnswerIndex(QuestionList, "start at your company")));

            companyDetails = companyRepo.save(companyDetails);

            CompanyEmployeeDetails employeeDetails = new CompanyEmployeeDetails();

            int EmployeeEmailIndex = getAnswerIndex(QuestionList, "your Email");

            employeeDetails.setCompany(companyDetails);
            employeeDetails.setEmail(AnswerList.get(EmployeeEmailIndex).substring(AnswerList.get(EmployeeEmailIndex).indexOf("|") + 1, AnswerList.get(EmployeeEmailIndex).indexOf(">")));
            employeeDetails.setLocation(AnswerList.get(getAnswerIndex(QuestionList, "city are you located")) + "," + AnswerList.get(getAnswerIndex(QuestionList, "country are you located")));
            employeeDetails.setNickname(AnswerList.get(getAnswerIndex(QuestionList, "name you would like me to call you")));
            employeeDetails.setStartDateAtCompany(AnswerList.get(getAnswerIndex(QuestionList, "start at your company")));
            employeeDetails.setCompany(companyDetails);


            EmployeePosition position = new EmployeePosition();
            position.setTitle(AnswerList.get(getAnswerIndex(QuestionList, "your title")));
            position = employeePositionRepo.save(position);

            employeeDetails.setPosition(position);

            companyEmployeeDetailsRepo.save(employeeDetails);

            //      Team teamDetails = new Team();


            for (int i = 0; i < answersList.size(); i++) {
                answers = new Answers();
                answers.setQuestion(QuestionList.get(i));
                if (i == EmployeeEmailIndex) {
                    answers.setAnswers(AnswerList.get(i).substring(AnswerList.get(i).indexOf("|") + 1, AnswerList.get(i).indexOf(">")));
                } else {
                    answers.setAnswers(AnswerList.get(i));
                }
                answers.setGivenBy(AnswerList.get(0));
                DbAnswersList.add(answers);
            }

            answersRepo.saveAll(DbAnswersList);

        }catch (Exception e){
            e.printStackTrace();
        }

//        userQueryRepo.save(info);

    }


    public static int getAnswerIndex(ArrayList<Questions> questions,String QuestionStr){

        int result=0;

        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getQuestion().contains(QuestionStr)){
                result =  i;
            }
            else
                continue;
        }
        return result;
    }


    public void saveChannelInfo(String channelId,String UserSlackId){

        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setChannelId(channelId);
        channelInfo.setUserSlackId(UserSlackId);
        channelInfo.setDailyPulseCount(0);


        channelInfoRepo.save(channelInfo);
    }

    public List<ChannelInfo> getChannels(){
        List<ChannelInfo> channelInfoList = channelInfoRepo.findAll();
        return channelInfoList;
    }
}

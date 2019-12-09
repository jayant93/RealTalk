package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Answers;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Model.UserQueryInfo;
import com.realTalk.bot.Repositories.AnswersRepository;
import com.realTalk.bot.Repositories.UserQueryInfoRepository;
import com.realTalk.bot.helpers.staticHelpers.TempUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /*
    This method will be used to saved OnBoarding information
    About the user Also saving Answers to particular questions
     */
    public void saveUserQueryInfoToDB(List<Questions> questions){

        UserQueryInfo info = new UserQueryInfo();
        Answers answers = null;

        info.setEmail(TempUserInfo.getEmail());
        answers = new Answers();
        answers.setAnswers(TempUserInfo.getEmail());
        answers.setGivenBy(TempUserInfo.getEmail());
        answers.setQuestion(questions.get(1));
        answersRepo.save(answers);


        info.setName(TempUserInfo.getName());
        answers = new Answers();
        answers.setAnswers(TempUserInfo.getName());
        answers.setGivenBy(TempUserInfo.getEmail());
        answers.setQuestion(questions.get(0));
        answersRepo.save(answers);


        info.setTeamName(TempUserInfo.getTeamName());
        answers = new Answers();
        answers.setAnswers(TempUserInfo.getTeamName());
        answers.setGivenBy(TempUserInfo.getEmail());
        answers.setQuestion(questions.get(2));
        answersRepo.save(answers);

        info.setTimeZone(TempUserInfo.getTimeZone());
        answers = new Answers();
        answers.setAnswers(TempUserInfo.getTimeZone());
        answers.setGivenBy(TempUserInfo.getEmail());
        answers.setQuestion(questions.get(3));
        answersRepo.save(answers);

        info.setStartAtCompany(TempUserInfo.getDateStartAtCompany());
        answers = new Answers();
        answers.setAnswers(TempUserInfo.getDateStartAtCompany());
        answers.setGivenBy(TempUserInfo.getEmail());
        answers.setQuestion(questions.get(4));
        answersRepo.save(answers);

            TempUserInfo.setName(null);
            TempUserInfo.setTimeZone(null);
            TempUserInfo.setTeamName(null);
            TempUserInfo.setEmail(null);


            userQueryRepo.save(info);


    }


}

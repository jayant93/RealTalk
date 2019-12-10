package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Answers;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Model.UserQueryInfo;
import com.realTalk.bot.Repositories.AnswersRepository;
import com.realTalk.bot.Repositories.UserQueryInfoRepository;
import com.realTalk.bot.helpers.staticHelpers.TempUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    public void saveUserQueryInfoToDB(List<Questions> questions,List<String> answersList,Long invalidCount) throws IllegalAccessException {

        UserQueryInfo info = new UserQueryInfo();
        Answers answers = null;

    //saving answers to questions
        for(int i=0;i<questions.size();i++) {
            answers = new Answers();
            answers.setQuestion(questions.get(i));
            answers.setAnswers(answersList.get(i+1));
            answers.setGivenBy(answersList.get(1));
            if(questions.get(i).getValidationType().equalsIgnoreCase("DateValidation")) {
                answers.setNumberOfIncorrectformats(invalidCount);
            }
            else{
                answers.setNumberOfIncorrectformats(Long.valueOf(0));
            }
            answersRepo.save(answers);
        }

        info.setName(answersList.get(1));
        info.setEmail(answersList.get(2));
        info.setTeamName(answersList.get(3));
        info.setTimeZone(answersList.get(4));
        info.setStartAtCompany(answersList.get(5));

        userQueryRepo.save(info);

    }


}

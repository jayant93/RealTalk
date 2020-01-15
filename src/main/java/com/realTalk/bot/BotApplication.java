package com.realTalk.bot;

import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.ArrayList;
import java.util.List;



@EntityScan("com.realTalk.bot.Model")
@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot","com.realTalk.bot"})
@EnableScheduling
public class BotApplication implements CommandLineRunner {

	@Autowired
	QuestionsRepository Qrepo;

	public static void main(String... args){

		SpringApplication.run(BotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		List<Questions> questionsList = new ArrayList<>();

		Questions query = new Questions();

		query = new Questions();
		query.setQuestion("What is the name you would like me to call you?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("When did you start working at this company?(MM/DD/YYYY)");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("DateValidation");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("When did you start working with your current team?(MM/DD/YYYY)");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("DateValidation");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is your title?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What country are you located in?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What city are you located in?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("On average, how many days a week do you work remote (Answer a number between 0 and 5)?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Do you have a manager (Yes/No)?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("HaveAManager");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("What is your Managers email address?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Do you manage a team that directly reports to you? (Yes/No)");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("HaveATeam");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Please enter the emails of the people that report to you?(please separate them with commas)");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Congratulations on getting set up! \n Let's try it out ! Remember, all answers are *anonymous* and \n" +
				" on a scale of 1 to 5. 1=Strongly Disagree, 2=Disagree, 3=Neutral, 4=Agree, 5=Strongly Agree." +
				"\n\nI would recommend my friends to work at my company?");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Thanks [EmployeeName] for the RealTalk! Please feel " +
				"free to leave any additional comments. Remember, they're anonymous too.");
		query.setQuestionType("OnBoarding");
		query.setExtraCheck("none");
		questionsList.add(query);



		Qrepo.saveAll(questionsList);




	}
}

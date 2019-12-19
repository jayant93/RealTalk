package com.realTalk.bot;

import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;


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
			query.setQuestion("What is your Full Name ?");
			query.setQuestionType("Authentication");
			query.setValidationType("none");
		query.setExtraCheck("none");
			questionsList.add(query);

			query = new Questions();
			query.setQuestion("What is your Email ?");
			query.setQuestionType("Authentication");
		query.setExtraCheck("none");
		query.setValidationType("none");

			questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your Team Name ?");
			query.setQuestionType("Authentication");
		query.setExtraCheck("none");
		query.setValidationType("none");
		questionsList.add(query);
//			query = new Questions();
//			query.setQuestion("what is your Team ID ?");
//		query.setQuestionType("Authentication");
//		questionsList.add(query);
//			query = new Questions();
//			query.setQuestion("What is your User ID ?");
//		query.setQuestionType("Authentication");
//		questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your TimeZone ?");
		query.setQuestionType("Authentication");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is the name you would like me to call you?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What month did you start at your company (MM/DD/YY) ?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("DateValidation");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("When did you join your Team? (Please answer in MM/DD/YYYY form)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("DateValidation");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is your title?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What country are you located in?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What city are you located in?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("On average, how many days a week do you work remote (Answer a number between 0 and 5)?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Do you have a manager (Yes/No)?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("HaveAManager");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("What is their email address?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Do you manage a team that directly reports to you? (Yes/No)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("HaveATeam");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("What are their email addresses? (Type \"Done\" when you're finished)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("TeamCollectEmail");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("Are you an Admin?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("IsAdminCheck");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("Will you be an administrator of RealTalk? For more information about being an Admin click here [link].");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("MailAdminForPermissionToBeAnAdmin");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Please verify some additional information to get your Company set up and can be used for benchmarking (your Company will always be anonymous).\n" +
				" You can always update this information in The Hub.Type Okay to proceed Further.");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("CompanyDetailInsertCheck");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What's your Company's name?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What industry is your company in?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("How many employees does it have? (<25, 25-50, 51-100, 101-250, 250-500, 500+)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);



		query = new Questions();
		query.setQuestion("Approximately, what percentage of your workforce is remote?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is your current average employee tenure?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is your annual revenue? (<$1M, $1-10M, $10-25M, 25-50M, 50M-100M, 100M+)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("Please enter the email addresses of company leaders that should have access to The Hub's Insights (CXOs, Directors/VPs, HR, etc.)\" (Type \"Done\" when you're finished)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("CollectCompanyLeadersEmail");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("Please enter the email addresses of other employees that might be Admins for RealTalk.");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("CollectAdminsEmail");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("What is the name the Division in the company you work for? (e.g. Finance, Operations, Product Management, Design, Operations, Support, HR, Sales, Marketing, Data Science, Customer Success, Professional Services, Other)");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);



		query = new Questions();
		query.setQuestion("Congratulations on getting set up! You can always edit this information by typing \"/X\" or see other commands by typing \"/Y\"");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("OnBoardingDone");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("Let's try it out [Employee First Name]! Remember, all answers are {bolded: anonymous} and \n on a scale of 1 to 5. 1=Strongly Disagree, 2=Disagree, 3=Neutral, 4=Agree, 5=Strongly Agree." +
				"\nI would recommend my friends to work at my company?");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);


		query = new Questions();
		query.setQuestion("Thanks [Employee First Name] for the RealTalk! Please feel free to leave any additional comments. Remember, they're anonymous too.");
		query.setQuestionType("OnBoarding");
		query.setValidationType("none");
		query.setExtraCheck("none");
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("I would recommend my friends to work at my company?");
		query.setQuestionType("DailyPulseQuestion");
		query.setValidationType("none");
		query.setExtraCheck("none");
		query.setQuestionOrder(1);
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("My peers are committed to doing quality work.");
		query.setQuestionType("DailyPulseQuestion");
		query.setValidationType("none");
		query.setExtraCheck("none");
		query.setQuestionOrder(2);
		questionsList.add(query);

		query = new Questions();
		query.setQuestion("I have the freedom to try new tools to help me do my job better");
		query.setQuestionType("DailyPulseQuestion");
		query.setValidationType("none");
		query.setExtraCheck("none");
		query.setQuestionOrder(3);
		questionsList.add(query);


		Qrepo.saveAll(questionsList);




	}
}

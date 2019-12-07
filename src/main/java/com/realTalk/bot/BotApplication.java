package com.realTalk.bot;

import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@EntityScan("com.realTalk.bot.Model")
@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot","com.realTalk.bot"})
public class BotApplication implements CommandLineRunner {

	@Autowired
	QuestionsRepository Qrepo;

	public static void main(String... args) {
		SpringApplication.run(BotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Questions> questionsList = new ArrayList<>();

		Questions query = new Questions();
			query.setQuestion("What is your Full Name ?");
			query.setQuestionType("Authentication");
			questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your Email ?");
			query.setQuestionType("Authentication");
			questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your Team Name ?");
		query.setQuestionType("Authentication");
		questionsList.add(query);
			query = new Questions();
			query.setQuestion("what is your Team ID ?");
		query.setQuestionType("Authentication");
		questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your User ID ?");
		query.setQuestionType("Authentication");
		questionsList.add(query);
			query = new Questions();
			query.setQuestion("What is your TimeZone ?");
		query.setQuestionType("Authentication");
		questionsList.add(query);

		Qrepo.saveAll(questionsList);


	}
}

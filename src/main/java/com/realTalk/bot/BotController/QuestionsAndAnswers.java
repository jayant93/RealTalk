package com.realTalk.bot.BotController;

import com.realTalk.bot.Service.QuestionsAndAnswersService;
import com.realTalk.bot.helpers.Request.QuestionRequest;
import com.realTalk.bot.helpers.Response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/Questions")
public class QuestionsAndAnswers {

    @Autowired
    QuestionsAndAnswersService service;

    @GetMapping
    public ResponseEntity<CustomResponse> createQuestions(@RequestBody QuestionRequest request){

          return service.addQuestionAndAnswers(request);

    }



}

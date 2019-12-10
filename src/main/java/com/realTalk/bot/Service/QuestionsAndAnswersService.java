package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Answers;
import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.QuestionsRepository;
import com.realTalk.bot.helpers.Request.QuestionRequest;
import com.realTalk.bot.helpers.Response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsAndAnswersService {

    @Autowired
    QuestionsRepository QRepo;

    //Lets work on adding some methods to Add Questions and Answers
    public ResponseEntity<CustomResponse> addQuestionAndAnswers(List<QuestionRequest> request){

            Questions query = new Questions();
            CustomResponse customResponse = new CustomResponse();
            List<Questions> queryList = new ArrayList<>();


        try {
            for(int i=0;i<request.size();i++) {
                query = new Questions();
                query.setQuestion(request.get(i).getQuestion());
                query.setValidationType(request.get(i).getValidationType());
                queryList.add(query);
            }


            queryList = QRepo.saveAll(queryList);

            customResponse.setError_code("200");
            customResponse.setStatus(HttpStatus.CREATED);
            customResponse.setMessage("Question Created Successfully");
            customResponse.setResponse_Object(queryList);

            return ResponseEntity.ok().body(customResponse);
        }
        catch (Exception e){
            customResponse.setError_code("500");
            customResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setMessage("Question Creation Unsuccessfull");
            customResponse.setResponse_Object(null);

            return ResponseEntity.badRequest().body(customResponse);
        }

    }

    public List<Questions> getAuthQuestions(String QuestionType) {
        List<Questions> questions = QRepo.findAuthQuestions(QuestionType);
        return questions;
    }
}

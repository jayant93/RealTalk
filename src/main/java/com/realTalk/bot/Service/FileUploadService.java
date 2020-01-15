package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Questions;
import com.realTalk.bot.Repositories.QuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadService {

    @Autowired
    QuestionsRepository Qrepo;

    public static final Logger log = LoggerFactory.getLogger(FileUploadService.class);
    public String Upload(File file) throws IOException {

        String UploadStatus = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Questions questions = null;
        List<Questions> questionList = new ArrayList<Questions>();
        while (reader.readLine()!=null){
            questions = new Questions();
                String lines = reader.readLine();
             //   List<String> QuestionDetails = new ArrayList<>();
                String[] QuestionDetails = lines.split(",");
                questions.setExtraCheck("none");
                questions.setQuestionType("DailyPulseQuestion");
                questions.setQuestion(QuestionDetails[5]);
                questionList.add(questions);
            log.info("Lines == "+lines);
        }
        Qrepo.saveAll(questionList);
        return UploadStatus;
    }

}

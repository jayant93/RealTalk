package com.realTalk.bot.BotController;


import com.realTalk.bot.Model.CompanyEmployeeDetails;
import com.realTalk.bot.Service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.logging.Logger;

import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/File")
public class FileController {

    @Autowired
    FileDownloadService fileDownloadService;


    @GetMapping(path="/EmployeeDetails")
    public ResponseEntity getEmployeeExcel(HttpServletRequest request) throws IOException {

        File file = fileDownloadService.generateEmployeeReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename= "+file.getName());



        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }



    @GetMapping(path="/QuestionAnswers")
    public ResponseEntity getAnswersExcel(HttpServletRequest request) throws IOException {

        File file = fileDownloadService.generateQuestionReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename= "+file.getName());



        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }






}

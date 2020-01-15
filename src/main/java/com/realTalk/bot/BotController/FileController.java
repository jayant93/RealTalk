package com.realTalk.bot.BotController;

import com.realTalk.bot.Service.FileDownloadService;
import com.realTalk.bot.Service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "/File")
public class FileController {

    @Autowired
    FileDownloadService fileDownloadService;

    @Autowired
    FileUploadService uploadService;


    @GetMapping(path = "/Employee")
    public ResponseEntity getEmployeeExcel(HttpServletRequest request) throws IOException {

        File file = fileDownloadService.generateWorkspaceMemberDetails();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename= " + file.getName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }


    @GetMapping(path = "/OnBoardingResponses")
    public ResponseEntity getOnBoardingExcel(HttpServletRequest request) throws IOException {

        File file = fileDownloadService.generateQuestionReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename= " + file.getName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

    @GetMapping(path = "/DailyPulseResponses")
    public ResponseEntity getDailyPulseExcel(HttpServletRequest request) throws IOException {

        File file = fileDownloadService.generateDailyPulseQuestionReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename= " + file.getName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }


    @PostMapping("/Upload")
    public String DailyPulseQuestionsUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadStatus = null;
        uploadStatus = file.getOriginalFilename();
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        uploadService.Upload(convFile);
        return uploadStatus;
    }


}

package com.realTalk.bot.Service;

import com.realTalk.bot.Model.*;
import com.realTalk.bot.Repositories.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileDownloadService {


    @Autowired
    MemberRepository memberRepo;

    @Autowired
    QuestionResponseRepository questionResponseRepo;
    @Autowired
    DailyPulseResponseRepository dailyPulseResponseRepository;


    public File generateQuestionReport() throws IOException {

        List<QuestionsResponses> AnswersList = questionResponseRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Questions and Answers Details");


        HSSFRow row = spreadsheet.createRow(1);
        HSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ID");
        cell = row.createCell(2);
        cell.setCellValue("QUESTION");
        cell = row.createCell(3);
        cell.setCellValue("ANSWER");
        cell = row.createCell(4);
        cell.setCellValue("ANSWERS GIVEN BY");
        cell = row.createCell(5);
        cell.setCellValue("ANSWERS GIVEN AT");


        int j = 2;

        for (int i = 0; i < AnswersList.size(); i++) {
            row = spreadsheet.createRow(j);
            cell = row.createCell(1);
            cell.setCellValue(AnswersList.get(i).getResponseId());
            cell = row.createCell(2);
            cell.setCellValue(AnswersList.get(i).getQuestion_text());
            cell = row.createCell(3);
            cell.setCellValue(AnswersList.get(i).getAnswer());
            cell = row.createCell(4);
            cell.setCellValue(memberRepo.findById(AnswersList.get(i).getUser()).get().getEmail());
            cell = row.createCell(5);
            cell.setCellValue(AnswersList.get(i).getAnsweredAt());
            j++;
        }

        File file = new File("/home/RealTalkQuestionAnswers.xlsx");

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        System.out.println("exceldatabase.xlsx written successfully");

        return file;


    }


    public File generateDailyPulseQuestionReport() throws IOException {

        List<DailyPulseResponse> AnswersList = dailyPulseResponseRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Daily Pulse Response Details");


        HSSFRow row = spreadsheet.createRow(1);
        HSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ID");
        cell = row.createCell(2);
        cell.setCellValue("QUESTION");
        cell = row.createCell(3);
        cell.setCellValue("NUMERIC RESPONSE");
        cell = row.createCell(4);
        cell.setCellValue("TEXT RESPONSE");
        cell = row.createCell(5);
        cell.setCellValue("ANSWERS GIVEN BY");
        cell = row.createCell(6);
        cell.setCellValue("ANSWERS GIVEN AT");


        int j = 2;

        for (int i = 0; i < AnswersList.size(); i++) {
            row = spreadsheet.createRow(j);
            cell = row.createCell(1);
            cell.setCellValue(AnswersList.get(i).getId());
            cell = row.createCell(2);
            cell.setCellValue(AnswersList.get(i).getQuestion());
            cell = row.createCell(3);
            cell.setCellValue(AnswersList.get(i).getNumericResponse());
            cell = row.createCell(4);
            cell.setCellValue(AnswersList.get(i).getTextResponse());
            cell = row.createCell(5);
            cell.setCellValue(memberRepo.findById(AnswersList.get(i).getUserId()).get().getEmail());
            cell = row.createCell(6);
            cell.setCellValue(AnswersList.get(i).getAnsweredAt());
            j++;
        }

        File file = new File("/home/RealTalkDailyPulseResponses.xlsx");

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        System.out.println("exceldatabase.xlsx written successfully");

        return file;


    }


    public File generateWorkspaceMemberDetails() throws IOException {


        List<Member> memberList = memberRepo.findAll();


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("employee details");


        HSSFRow row = spreadsheet.createRow(1);
        HSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ID");
        cell = row.createCell(2);
        cell.setCellValue("NAME");
        cell = row.createCell(3);
        cell.setCellValue("FIRST_NAME");
        cell = row.createCell(4);
        cell.setCellValue("LAST_NAME");
        cell = row.createCell(5);
        cell.setCellValue("EMAIL");
        cell = row.createCell(6);
        cell.setCellValue("PHONE");
        cell = row.createCell(7);
        cell.setCellValue("TIME_ZONE");
        cell = row.createCell(8);
        cell.setCellValue("TIME_ZONE_LABEL");
        cell = row.createCell(9);
        cell.setCellValue("IS_ADMIN");
        cell = row.createCell(10);
        cell.setCellValue("IS_OWNER");
        cell = row.createCell(11);
        cell.setCellValue("IS_BOT");


        int j = 2;

        for (int i = 0; i < memberList.size(); i++) {
            row = spreadsheet.createRow(j);
            cell = row.createCell(1);
            cell.setCellValue(memberList.get(i).getId());
            cell = row.createCell(2);
            cell.setCellValue(memberList.get(i).getName());
            cell = row.createCell(3);
            cell.setCellValue(memberList.get(i).getFirst_name());
            cell = row.createCell(4);
            cell.setCellValue(memberList.get(i).getLast_name());
            cell = row.createCell(5);
            cell.setCellValue(memberList.get(i).getEmail());
            cell = row.createCell(6);
            cell.setCellValue(memberList.get(i).getPhone());
            cell = row.createCell(7);
            cell.setCellValue(memberList.get(i).getTz());
            cell = row.createCell(8);
            cell.setCellValue(memberList.get(i).getTz_label());
            cell = row.createCell(9);
            cell.setCellValue(memberList.get(i).getIs_admin());
            cell = row.createCell(10);
            cell.setCellValue(memberList.get(i).getIs_owner());
            cell = row.createCell(11);
            cell.setCellValue(memberList.get(i).getIs_bot());
            j++;
        }

        File file = new File("/home/WorkspaceMemberDetails.xlsx");

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        System.out.println("exceldatabase.xlsx written successfully");

        return file;

    }


}

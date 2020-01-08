package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Answers;
import com.realTalk.bot.Model.CompanyEmployeeDetails;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Repositories.AnswersRepository;
import com.realTalk.bot.Repositories.CompanyEmployeeDetailsRepo;
import com.realTalk.bot.Repositories.MemberRepository;
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
    CompanyEmployeeDetailsRepo companyEmployeeDetailsRepo;

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    AnswersRepository answersRepo;


    public File generateEmployeeReport() throws IOException {

        List<CompanyEmployeeDetails> employeeList = companyEmployeeDetailsRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("employee details");


        HSSFRow row = spreadsheet.createRow(1);
        HSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ID");
        cell = row.createCell(2);
        cell.setCellValue("COMPANY NAME");
        cell = row.createCell(3);
        cell.setCellValue("EMPLOYEE_NAME");
        cell = row.createCell(4);
        cell.setCellValue("TITLE");
        cell = row.createCell(5);
        cell.setCellValue("START DATE AT COMPANY");
        cell = row.createCell(6);
        cell.setCellValue("LOCATION");


        int j = 2;

        for (int i = 0; i < employeeList.size(); i++) {
            row = spreadsheet.createRow(j);
            cell = row.createCell(1);
            cell.setCellValue(employeeList.get(i).getId());
            cell = row.createCell(2);
            cell.setCellValue(employeeList.get(i).getCompany().getName());
            cell = row.createCell(3);
            cell.setCellValue(employeeList.get(i).getFullName());
            cell = row.createCell(4);
            cell.setCellValue(employeeList.get(i).getPosition().getTitle());
            cell = row.createCell(5);
            cell.setCellValue(employeeList.get(i).getStartDateAtCompany());
            cell = row.createCell(6);
            cell.setCellValue(employeeList.get(i).getLocation());
            j++;
        }

        File file = new File("/home/RealTalkCompanyDetails.xlsx");

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        System.out.println("exceldatabase.xlsx written successfully");

        return file;
    }


    public File generateQuestionReport() throws IOException {

        List<Answers> AnswersList = answersRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet spreadsheet = workbook.createSheet("Questions and Answers Details");


        HSSFRow row = spreadsheet.createRow(1);
        HSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("ID");
        cell = row.createCell(2);
        cell.setCellValue("QUESTION");
        cell = row.createCell(3);
        cell.setCellValue("ANSWERS");
        cell = row.createCell(4);
        cell.setCellValue("ANSWERS GIVEN BY");
        cell = row.createCell(5);
        cell.setCellValue("ANSWERS GIVEN AT");


        int j = 2;

        for (int i = 0; i < AnswersList.size(); i++) {
            row = spreadsheet.createRow(j);
            cell = row.createCell(1);
            cell.setCellValue(AnswersList.get(i).getId());
            cell = row.createCell(2);
            cell.setCellValue(AnswersList.get(i).getQuestion().getQuestion());
            cell = row.createCell(3);
            cell.setCellValue(AnswersList.get(i).getAnswers());
            cell = row.createCell(4);
            cell.setCellValue(AnswersList.get(i).getEmployee().getEmail());
            cell = row.createCell(5);
            cell.setCellValue(AnswersList.get(i).getAnswerGivenAt());
            j++;
        }

        File file = new File("/home/RealTalkQuestionAnswers.xlsx");

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

package com.commerce.microcommerce.web.controller;
import com.commerce.microcommerce.model.User;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelController {

    @RequestMapping (value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<User>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<User> userList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                User user = new User();

                XSSFRow row = worksheet.getRow(index);
                Integer id = (int) row.getCell(0).getNumericCellValue();


                user.setId(id);
                user.setNom(row.getCell(2).getStringCellValue());
                user.setPrenom(row.getCell(3).getStringCellValue());
                user.setEmail(row.getCell(4).getStringCellValue());
                user.setPseudo(row.getCell(5).getStringCellValue());



                userList.add(user);
            }
        }

        return new ResponseEntity<>(userList, status);
    }
}


package com.jpmc.springWithKafka;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class InclusiveWordController {

    @PostMapping("/check-inclusive-words")
    public ResponseEntity<List<InclusiveWordResult>> checkInclusiveWords(@RequestBody String inputString) throws IOException {
        List<InclusiveWordResult> results = new ArrayList<>();


        File file = new ClassPathResource("words.xlsx").getFile();
        System.out.println("---------------------- : " +  file.exists());
        try {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell inclusiveWordCell = row.getCell(0);
                Cell descriptionCell = row.getCell(2);
                Cell alternateWordsCell = row.getCell(3);

                String inclusiveWord = inclusiveWordCell.getStringCellValue();
                String description = descriptionCell.getStringCellValue();
                String alternateWords = alternateWordsCell.getStringCellValue();

                if (inputString.contains(inclusiveWord)) {
                    results.add(new InclusiveWordResult(inclusiveWord, description, alternateWords));
                }
            }

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(results);
    }
}


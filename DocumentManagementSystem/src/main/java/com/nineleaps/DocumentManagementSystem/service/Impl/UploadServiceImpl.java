package com.nineleaps.DocumentManagementSystem.service.Impl;

import com.nineleaps.DocumentManagementSystem.dao.EmployeeAccounts;
import com.nineleaps.DocumentManagementSystem.dao.EmployeeData;
import com.nineleaps.DocumentManagementSystem.dto.TokenRequestedData;
import com.nineleaps.DocumentManagementSystem.exceptions.CustomResponse;
import com.nineleaps.DocumentManagementSystem.exceptions.FileTypeEmpty;
import com.nineleaps.DocumentManagementSystem.exceptions.UploadError;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeAccountsRepository;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeDataRepository;
import com.nineleaps.DocumentManagementSystem.service.UploadService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class UploadServiceImpl implements UploadService {
    @Autowired
    EmployeeAccountsRepository employeeAccountsRepo;
    @Autowired
    EmployeeDataRepository employeeDataRepo;
    @Autowired
    TokenRequestedData tokenRequestedData;


    @Override
    public ResponseEntity<CustomResponse> storeData(String tokenData, MultipartFile multipartFile, String fileType) throws IOException, ParseException {

        if (fileType.equals(null)) {
            throw new FileTypeEmpty("file type does not have any value");
        }


// FETCHING THE REQUIRED EMAIL RECORD AND THEN USING THE UID TO STORE AS FOLDERUID IN EMPLOYEE DATA
        long currentTime = Instant.now().toEpochMilli();

        EmployeeAccounts employeeAccounts = employeeAccountsRepo.findbyEmailId(tokenRequestedData.getUserEmail());
        EmployeeData finddata = employeeDataRepo.findFileRow(fileType, employeeAccounts.getUid().toString());
        if (finddata != null) {
            EmployeeData employeeData = new EmployeeData(finddata.getUid(), fileType,
                    employeeAccounts.getUid().toString(), multipartFile.getOriginalFilename(), tokenRequestedData.getUserName(),
                    currentTime);
            employeeDataRepo.save(employeeData);
        } else {
            EmployeeData employeeData = new EmployeeData(UUID.randomUUID(), fileType,
                    employeeAccounts.getUid().toString(), multipartFile.getOriginalFilename(), tokenRequestedData.getUserName(),
                    currentTime);
            employeeDataRepo.save(employeeData);
        }


        //saving data to the system
        try {
            File content = new File("/home/nineleaps/Desktop/UserData/" +
                    employeeAccounts.getUid().toString() + "/" + fileType);
            content.createNewFile();
            FileOutputStream fout = new FileOutputStream(content);
            fout.write(multipartFile.getBytes());
            fout.close();
        } catch (Exception e) {
            throw new UploadError("There was some problem while uploading the file");
        }

        CustomResponse customResponse = new CustomResponse(new Date(), "Success",
                "the file was uploaded sucessfully!", HttpStatus.CREATED.getReasonPhrase());
//

        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
    }
}

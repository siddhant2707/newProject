package com.nineleaps.DocumentManagementSystem.service.Impl;

import com.nineleaps.DocumentManagementSystem.dao.EmployeeAccounts;
import com.nineleaps.DocumentManagementSystem.dao.EmployeeData;
import com.nineleaps.DocumentManagementSystem.dto.TokenRequestedData;
import com.nineleaps.DocumentManagementSystem.exceptions.CustomResponse;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeAccountsRepository;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeDataRepository;
import com.nineleaps.DocumentManagementSystem.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;


@Service
public class DeleteServiceImpl implements DeleteService {
    @Autowired
    EmployeeAccountsRepository employeeAccountsRepo;
    @Autowired
    EmployeeDataRepository employeeDataRepo;
    @Autowired
    TokenRequestedData tokenRequestedData;


    public ResponseEntity<CustomResponse> deleteRecord(String fileType) {
        String message = "Success";
        String details = "the file was deleted sucessfully!";
        EmployeeAccounts employeeAccounts = employeeAccountsRepo.findbyEmailId(tokenRequestedData.getUserEmail());
        EmployeeData employeeData = employeeDataRepo.findFileRow(fileType, employeeAccounts.getUid().toString());
        if (employeeData == null) {
            message = "NoData";
            details = "No data found to delete";

            System.out.println("No data found");
        } else {
            employeeDataRepo.deleteByUid(employeeData.getUid());
        }
        File file = new File("/home/nineleaps/Desktop/UserData/" + employeeAccounts.getUid().toString() + "/" + fileType);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }

        CustomResponse customResponse = new CustomResponse(new Date(), message,
                details, HttpStatus.OK.getReasonPhrase());


        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
    }
}




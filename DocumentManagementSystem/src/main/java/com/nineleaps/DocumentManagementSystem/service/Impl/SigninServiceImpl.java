package com.nineleaps.DocumentManagementSystem.service.Impl;


import com.nineleaps.DocumentManagementSystem.dao.EmployeeAccounts;
import com.nineleaps.DocumentManagementSystem.dto.SigninResponseData;
import com.nineleaps.DocumentManagementSystem.dto.TokenRequestedData;
import com.nineleaps.DocumentManagementSystem.exceptions.SignInUserDataNotFound;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeAccountsRepository;
import com.nineleaps.DocumentManagementSystem.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SigninServiceImpl implements SignInService {

    @Autowired
    EmployeeAccountsRepository employeeAccountRepo;

    @Autowired
    TokenRequestedData tokenRequestedData;

    @Override
    public SigninResponseData authorizeUser() {
        System.out.println(tokenRequestedData.getGoogleId());
        System.out.println(tokenRequestedData.getUserEmail());

        EmployeeAccounts data;
            data = employeeAccountRepo.findbyEmailId(tokenRequestedData.getUserEmail());
            employeeAccountRepo.updateGoogleId(tokenRequestedData.getGoogleId(), data.getUid());


        SigninResponseData packetData = new SigninResponseData();
        packetData.setEmailId(data.getEmailId());
        packetData.setView(data.getDesignation());


        //CREATING A FOLDER FOR THE LOGGED IN USER
        String uid = "/home/nineleaps/Desktop/UserData/" + data.getUid().toString();
        new File(uid).mkdir();


        return packetData;
    }

}








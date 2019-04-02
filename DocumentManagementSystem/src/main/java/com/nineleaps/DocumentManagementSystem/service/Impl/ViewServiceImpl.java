package com.nineleaps.DocumentManagementSystem.service.Impl;

import com.nineleaps.DocumentManagementSystem.dao.EmployeeAccounts;
import com.nineleaps.DocumentManagementSystem.dao.EmployeeData;
import com.nineleaps.DocumentManagementSystem.dto.TokenRequestedData;
import com.nineleaps.DocumentManagementSystem.exceptions.ViewNoRecordsFound;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeAccountsRepository;
import com.nineleaps.DocumentManagementSystem.repository.EmployeeDataRepository;
import com.nineleaps.DocumentManagementSystem.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewServiceImpl implements ViewService {

    @Autowired
    EmployeeDataRepository employeeDataRepo;
    @Autowired
    EmployeeAccountsRepository employeeAccountsRepo;
    @Autowired
    TokenRequestedData tokenRequestedData;


    @Override
    public List<EmployeeData> fetchViewData() {
        EmployeeAccounts employeeAccounts = employeeAccountsRepo.findbyEmailId(tokenRequestedData.getUserEmail());
        List<EmployeeData> employeeData = employeeDataRepo.findByfolderUid(employeeAccounts.getUid().toString());
        System.out.println(employeeData.size());
        if (employeeData.size() == 0) {
            throw new ViewNoRecordsFound("You Have Not Uploaded any Data Till Now!!");
        }
        return employeeData;
    }
}

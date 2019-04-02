package com.nineleaps.DocumentManagementSystem.service.Impl;

import com.nineleaps.DocumentManagementSystem.dao.DocumentType;
import com.nineleaps.DocumentManagementSystem.repository.DocumentTypeRepository;
import com.nineleaps.DocumentManagementSystem.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    @Autowired
    DocumentTypeRepository documentTypeRepo;


    @Override
    public List<DocumentType> fetchTypeOfDocument() {

        return documentTypeRepo.findFileType();
    }
}

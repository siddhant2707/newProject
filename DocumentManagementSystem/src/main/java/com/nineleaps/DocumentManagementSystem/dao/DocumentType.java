package com.nineleaps.DocumentManagementSystem.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;


@Table("DocumentType")
public class DocumentType {

    @Id
    @Column(value = "uid")
    UUID uidDoc = UUID.randomUUID();

    @Column(value = "fileType")
    private String FileType;

    @Column(value = "displayName")
    private String DisplayName;

    public UUID getUidDoc() {
        return uidDoc;
    }

    public void setUidDoc(UUID uidDoc) {
        this.uidDoc = uidDoc;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

}

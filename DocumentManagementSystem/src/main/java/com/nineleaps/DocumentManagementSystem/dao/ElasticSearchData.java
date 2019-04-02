package com.nineleaps.DocumentManagementSystem.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "employeedatabase", type = "employeeaccounts", shards = 1)
public class ElasticSearchData {
    @Id
    @Field(type = FieldType.Text)
    String uid = UUID.randomUUID().toString();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public ElasticSearchData(String uid, String emailId) {
        this.uid = uid;
        this.emailId = emailId;
    }

    @Field(type = FieldType.Text)
    private String emailId;

    public ElasticSearchData() {
    }
}

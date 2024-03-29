package com.chaticat.elasticsearchservice.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(indexName = "users")
public class User {

    @Id
    private String id;

    private String username;

    private Boolean isPrivate;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Contact> contacts = new ArrayList<>();

}

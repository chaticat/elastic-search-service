package com.chaticat.elasticsearchservice.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@Setter
@Document(indexName = "users")
public class User {

    @Id
    private UUID id;

    private String username;

}

package com.chaticat.elasticsearchservice.chat.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@Document(indexName = "chats")
public class Chat {

    @Id
    private String id;

    private String name;

    private Boolean isPrivate;

    private boolean isGroup;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Participant> participants;

}

package com.realtimesearchwordservice.document;


import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "searchterm") // indexName 은 소문자로만 작성해야 한다.
public class SearchTermDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @NonNull
    @Setter
    @Field(type = FieldType.Text)
    private String searchTerm;
}

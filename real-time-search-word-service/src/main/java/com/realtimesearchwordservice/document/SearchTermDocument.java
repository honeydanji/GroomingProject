package com.realtimesearchwordservice.document;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "searchTerm")
public class SearchTermDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Field(type = FieldType.Text)
    private String searchTerm;
}

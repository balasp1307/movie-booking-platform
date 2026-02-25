package org.example.moviebooking.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "movies")
public class MovieIndex {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String language;
}
package com.building.tutorial.demostudentsreactive.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "authors")
public class Author {

    private String id;
    private String name;

}


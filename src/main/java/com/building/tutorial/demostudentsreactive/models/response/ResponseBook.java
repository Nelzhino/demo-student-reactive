package com.building.tutorial.demostudentsreactive.models.response;

import com.building.tutorial.demostudentsreactive.models.Author;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBook {

    private String id;
    private String name;
    private String description;
    private Author author;


}

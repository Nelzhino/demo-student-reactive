package com.building.tutorial.demostudentsreactive.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "books")
public class Book {


    @Id
    @NotEmpty
    private String id;
    @Min(value = 5)
    private String name;
    @Min(value = 5)
    @Max(value = 150)
    private String descripcion;
    @Valid
    @NotEmpty
    private Author author;


}

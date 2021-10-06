package com.building.tutorial.demostudentsreactive.models.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestBook {

    private String id;
    @NotEmpty
    @Min(value = 5)
    private String name;
    @NotEmpty
    @Min(value = 5)
    @Max(value = 150)
    private String description;
    @NotEmpty
    private String idAuthor;

}

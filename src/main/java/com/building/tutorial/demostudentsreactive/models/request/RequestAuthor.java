package com.building.tutorial.demostudentsreactive.models.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAuthor {

    private String id;
    @NotEmpty
    private String name;

}

package com.estudo.demoapplication.models;

import com.estudo.democore.enums.GenderEnum;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private GenderEnum gender;
}

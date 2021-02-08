package com.example.HunterApi.model;


import liquibase.pro.packaged.S;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class HunterDTO {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String clubName;

}

package com.example.HunterApi.model;


import liquibase.pro.packaged.S;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HunterDTO {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String clubName;

}

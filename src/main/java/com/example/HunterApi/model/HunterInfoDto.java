package com.example.HunterApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HunterInfoDto {

    private String name;
    private String surname;
}

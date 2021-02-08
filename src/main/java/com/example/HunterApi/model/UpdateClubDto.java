package com.example.HunterApi.model;

import lombok.Data;

import java.util.List;

@Data
public class UpdateClubDto {
    private String name;
    private List<HunterInfoDto> huntingInfo;
}

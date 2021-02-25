package com.example.HunterApi.model.HuntingClubDto;

import com.example.HunterApi.model.HunterDto.HunterInfoDto;
import lombok.Data;

import java.util.List;

@Data
public class UpdateClubDto {
    private String name;
    private List<HunterInfoDto> huntingInfo;
}

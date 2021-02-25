package com.example.HunterApi.model.HuntingClubDto;

import com.example.HunterApi.model.HunterDto.HunterInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HuntingClubDto {
    private Long id;
    private String name;
    private List<HunterInfoDto> huntingInfo;
}

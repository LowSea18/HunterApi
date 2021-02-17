package com.example.HunterApi.mapping;

import com.example.HunterApi.model.*;

import java.util.ArrayList;
import java.util.List;

public class Mapping {
    public HunterDTO mapHunterToDto(Hunter h){
        //List<ClubNameInfo> clubNameInfos = new ArrayList<>();
        if (h.getHuntingClub() != null) {
            return HunterDTO.builder()
                    .clubName(h.getHuntingClub().getName())
                    .name(h.getName())
                    .surname(h.getSurname())
                    .id(h.getId())
                    .age(h.getAge())
                    .build();
        }else {
            return HunterDTO.builder()
                    .name(h.getName())
                    .surname(h.getSurname())
                    .id(h.getId())
                    .age(h.getAge())
                    .build();
        }
    }

    public HuntingClubDto mapHuntingClubToHuntingClubDTO(HuntingClub huntingClub) {
        List<HunterInfoDto> hunterInfoDtos = new ArrayList<>();
        huntingClub.getHunters().forEach(h -> hunterInfoDtos.add(mapToInfo(h)));
        return HuntingClubDto.builder()
                .id(huntingClub.getId())
                .name(huntingClub.getName())
                .huntingInfo(hunterInfoDtos)
                .build();
    }

    public HunterInfoDto mapToInfo(Hunter hunter) {
        return HunterInfoDto.builder()
                .name(hunter.getName())
                .surname(hunter.getSurname())
                .build();
    }

}

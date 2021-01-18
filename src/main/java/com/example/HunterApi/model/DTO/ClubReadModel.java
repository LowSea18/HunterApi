package com.example.HunterApi.model.DTO;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.model.HuntingClub;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClubReadModel {

    private String name;
    private List<ClubHunterReadModel> hunters;

    public ClubReadModel(HuntingClub huntingClub){
        this.name=huntingClub.getName();
        this.hunters=huntingClub.getHunters().stream().map(ClubHunterReadModel::new).collect(Collectors.toList());
    }
}

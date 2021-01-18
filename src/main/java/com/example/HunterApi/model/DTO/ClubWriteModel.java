package com.example.HunterApi.model.DTO;

import com.example.HunterApi.model.HuntingClub;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClubWriteModel {
    private String name;
    private List<ClubHunterWriteModel> hunters;

    public ClubWriteModel (String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public HuntingClub toClub(){
        var e = new HuntingClub();
        e.setName(name);
        e.setHunters(hunters.stream().map(ClubHunterWriteModel::toHunter).collect(Collectors.toList()));
        return e;
    }
}

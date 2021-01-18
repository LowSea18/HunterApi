package com.example.HunterApi.model.DTO;

import com.example.HunterApi.model.Hunter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubHunterReadModel {
    private String name;
    private String surname;

    public ClubHunterReadModel(Hunter hunter){
        this.name=hunter.getName();
        this.surname=hunter.getSurname();
    }

}

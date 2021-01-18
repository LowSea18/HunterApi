package com.example.HunterApi.model.DTO;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.repository.HunterRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubHunterWriteModel {
    private String name;
    private String surname;

    public void setName(final String name){
        this.name=name;
    }
    public void setSurname(final String surname){
        this.surname=surname;
    }

    public Hunter toHunter(){
        return new Hunter(name,surname);
    }
}

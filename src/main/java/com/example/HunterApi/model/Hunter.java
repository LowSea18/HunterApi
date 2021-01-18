package com.example.HunterApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity

public class Hunter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    @ManyToOne
    @JoinColumn(name = "ClubId")
    private HuntingClub hunterclub;
    //private List<String> permits;

    public Hunter(String name, String surname){
        this.name=name;
        this.surname=surname;
    }


}

package com.example.HunterApi.model.Entity;

import com.example.HunterApi.model.Entity.HuntingClub;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "hunters")

public class Hunter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private HuntingClub huntingClub;
    //private List<String> permits;

    public Hunter(String name, String surname){
        this.name=name;
        this.surname=surname;
    }


}

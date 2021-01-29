package com.example.HunterApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.List;

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
    private HuntingClub hunterclub;
    //private List<String> permits;

    public Hunter(String name, String surname){
        this.name=name;
        this.surname=surname;
    }


}

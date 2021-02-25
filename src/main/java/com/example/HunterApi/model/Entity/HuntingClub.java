package com.example.HunterApi.model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hunter_club")
public class HuntingClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy="huntingClub",fetch = FetchType.EAGER)
    private List<Hunter> hunters;
}
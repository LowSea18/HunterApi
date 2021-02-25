package com.example.HunterApi;


import com.example.HunterApi.model.Entity.Hunter;
import com.example.HunterApi.model.Entity.HuntingClub;
import com.example.HunterApi.repository.HunterRepository;
import com.example.HunterApi.repository.HuntingClubRepository;
import com.example.HunterApi.service.HunterService;
import com.example.HunterApi.service.HuntingClubService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestswithH2Db {
    @Autowired
    private HunterService hunterService;
    @Autowired
    private HuntingClubService huntingClubService;
    @Autowired
    private HunterRepository hunterRepository;
    @Autowired
    private HuntingClubRepository huntingClubRepository;

    @BeforeEach
    void setUp(){
        Hunter hunter = new Hunter();
        Hunter hunter1 = new Hunter();
        Hunter hunter2 = new Hunter();

        hunter.setName("Filip");
        hunter1.setName("Kamil");
        hunter2.setName("Robert");
        hunter.setAge(55);
        hunter1.setAge(32);
        hunter2.setAge(60);
        hunter.setSurname("Kowal");
        hunter1.setSurname("Czapla");
        hunter2.setSurname("Kot");
        hunterService.addHunterService(hunter);
        hunterService.addHunterService(hunter1);
        hunterService.addHunterService(hunter2);


        HuntingClub huntingClub=new HuntingClub();
        huntingClub.setName("Remiza");
        huntingClubService.addHuntingClubService(huntingClub);

    }

    @Test
    void should_addHunter_toClub(){
        hunterService.addingHunterToClubService(hunterRepository.findByName("Filip").get().getId(),huntingClubRepository.findByName("Remiza").get().getId());

        Assertions.assertEquals(huntingClubRepository.findByName("Remiza").get().getId(),hunterRepository.findByName("Filip").get().getHuntingClub().getId());

    }


    @AfterEach
    void clearDb(){
        hunterRepository.deleteAll();
        huntingClubRepository.deleteAll();
    }
}

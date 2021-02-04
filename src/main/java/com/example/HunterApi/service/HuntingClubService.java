package com.example.HunterApi.service;

import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.model.*;
import com.example.HunterApi.repository.HuntingClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HuntingClubService {

    @Autowired
    private HuntingClubRepository huntingClubRepository;


    public List<HuntingClubDto> showAllHuntersClubsService() {

        List<HuntingClubDto> huntingClubDtos = new ArrayList<>();
        huntingClubRepository.findAll().forEach(hc -> huntingClubDtos.add(mapHuntingClubToHuntingClubDTO(hc)));
        return huntingClubDtos;
    }

    private HuntingClubDto mapHuntingClubToHuntingClubDTO(HuntingClub huntingClub) {

        List<HunterInfoDto> hunterInfoDtos = new ArrayList<>();
        huntingClub.getHunters().forEach(h -> hunterInfoDtos.add(mapToInfo(h)));

        return HuntingClubDto.builder()
                .id(huntingClub.getId())
                .name(huntingClub.getName())
                .huntingInfo(hunterInfoDtos)
                .build();


    }

    private HunterInfoDto mapToInfo(Hunter hunter) {
        return HunterInfoDto.builder()
                .name(hunter.getName())
                .surname(hunter.getSurname())
                .build();
    }

    public void addHuntingClubService(HuntingClub club) throws IllegalAccessException {
        Optional<HuntingClub> hunterClubFromDb = huntingClubRepository.findByName(club.getName());
        if (hunterClubFromDb.isPresent()) {
            throw new IllegalAccessException("Hunting Club already exist");
        } else
            huntingClubRepository.save(club);
            System.out.println("Added");
    }

    public HuntingClubDto getHuntingClubByIdService(Long id) throws IllegalAccessException {

        HuntingClub hc = huntingClubRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find club, id: " + id));;
        return mapHuntingClubToHuntingClubDTO(hc);
    }


}
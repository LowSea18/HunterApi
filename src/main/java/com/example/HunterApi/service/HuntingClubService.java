package com.example.HunterApi.service;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.model.HunterInfoDto;
import com.example.HunterApi.model.HuntingClub;
import com.example.HunterApi.model.HuntingClubDto;
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


    public List<HuntingClubDto> showAllHuntersClubsService(){

        List<HuntingClubDto> huntingClubDtos = new ArrayList<>();
        huntingClubRepository.findAll().forEach(hc -> huntingClubDtos.add(mapHuntingClubToHuntingClubDTO(hc)));
        return huntingClubDtos;
    }

    private HuntingClubDto mapHuntingClubToHuntingClubDTO(HuntingClub huntingClub){

        List<HunterInfoDto> hunterInfoDtos = new ArrayList<>();
        huntingClub.getHunters().forEach(h ->hunterInfoDtos.add(mapToInfo(h)));

        return HuntingClubDto.builder()
                .id(huntingClub.getId())
                .name(huntingClub.getName())
                .huntingInfo(hunterInfoDtos)
                .build();


    }

    private HunterInfoDto mapToInfo(Hunter hunter){
        return HunterInfoDto.builder()
                .name(hunter.getName())
                .surname(hunter.getSurname())
                .build();
    }

    public ResponseEntity addHuntingClubService (HuntingClub club){
        Optional<HuntingClub> hunterClubFromDb = huntingClubRepository.findByName(club.getName());
        if(hunterClubFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }else
        return ResponseEntity.ok(huntingClubRepository.save(club));
    }

    public ResponseEntity getHuntingClubByIdService(Long id){
        Optional<HuntingClub> huntingClubFromDb = huntingClubRepository.findById(id);
        if (huntingClubFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.ok(huntingClubRepository.findById(id));
    }

}

package com.example.HunterApi.service;

import com.example.HunterApi.Exception.AlreadyExistException;
import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.mapping.Mapping;
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
    private final HuntingClubRepository huntingClubRepository;
    @Autowired
    private final Mapping mapping;

    public HuntingClubService(HuntingClubRepository huntingClubRepository,Mapping mapping){
        this.huntingClubRepository=huntingClubRepository;
        this.mapping=mapping;
    }

    public List<HuntingClubDto> showAllHuntersClubsService() {

        List<HuntingClubDto> huntingClubDtos = new ArrayList<>();
        huntingClubRepository.findAll().forEach(hc -> huntingClubDtos.add(mapping.mapHuntingClubToHuntingClubDTO(hc)));
        return huntingClubDtos;
    }

    public void addHuntingClubService(HuntingClub club)  {
        Optional<HuntingClub> hunterClubFromDb = huntingClubRepository.findByName(club.getName());
        if (hunterClubFromDb.isPresent()) {
            throw new AlreadyExistException("Hunting Club already exist");
        } else
            huntingClubRepository.save(club);

    }

    public HuntingClubDto getHuntingClubByIdService(Long id) throws IllegalAccessException {

        HuntingClub hc = huntingClubRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find club, id: " + id));;
        return mapping.mapHuntingClubToHuntingClubDTO(hc);
    }

    public void updateClub(Long id,UpdateClubDto updateClubDto){
        HuntingClub ClubFromDb =huntingClubRepository.findById(id).orElseThrow(() -> new NotFoundException("Hunting club does not exist id:" +id));
        Optional <HuntingClub> club = huntingClubRepository.findByName(updateClubDto.getName());
        if(club.isPresent()){
            throw new AlreadyExistException("Hunting club already exist");
        }else

        ClubFromDb.setName(updateClubDto.getName());
        huntingClubRepository.save(ClubFromDb);
    }


}
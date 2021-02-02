package com.example.HunterApi.service;

import com.example.HunterApi.model.ClubNameInfo;
import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.model.HunterDTO;
import com.example.HunterApi.model.HuntingClub;
import com.example.HunterApi.repository.HunterRepository;
import com.example.HunterApi.repository.HuntingClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HunterService {

    @Autowired
    HunterRepository repository;
    @Autowired
    HuntingClubRepository huntingClubRepository;


    public List<HunterDTO> showAllHuntersService(){
        List<HunterDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(h -> dtos.add(mapHunterToDto(h)));
        return dtos;
    }
    private HunterDTO mapHunterToDto(Hunter h){

        //List<ClubNameInfo> clubNameInfos = new ArrayList<>();
        if (h.getHuntingClub() != null) {
            return HunterDTO.builder()
                    .clubName(h.getHuntingClub().getName())
                    .name(h.getName())
                    .surname(h.getSurname())
                    .id(h.getId())
                    .age(h.getAge())
                    .build();
        }else {
            return HunterDTO.builder()
                    .name(h.getName())
                    .surname(h.getSurname())
                    .id(h.getId())
                    .age(h.getAge())
                    .build();
        }
    }

    public HunterDTO getHunterByIdService(Long id) throws IllegalAccessException {
        Optional<Hunter> hunterFromDb = repository.findById(id);
        if (hunterFromDb.isEmpty()){
            throw new IllegalAccessException();
        }
        Hunter hunter = repository.findById(id).orElseThrow();
        return mapHunterToDto(hunter);
    }

    public void addHunterService(Hunter add) throws IllegalAccessException
    {
        if(add.getAge()<=0){
            throw new IllegalAccessException("age must be greater than zero!");
        }else
            {
                Optional<Hunter> hunterFromDb = repository.findByNameAndSurname(add.getName().toLowerCase(),add.getSurname().toLowerCase());
                if (hunterFromDb.isPresent()) {
                    throw new IllegalAccessException("Hunter already exist");
                }else
                    repository.save(add);
            }
    }

    private boolean checkSurname(String surname){

        return repository.findBySurname(surname).isPresent();
    }

    public void deleteHunterService(Long id) throws IllegalAccessException {
        Optional<Hunter> hunterFromDb = repository.findById(id);
        if(hunterFromDb.isEmpty()){
            throw new IllegalAccessException("Hunter does not exist");
        }else {
            repository.deleteById(id);
        }
    }

    public ResponseEntity<String> addingHuntertoClubService(Long id, Long idClub){

        Hunter hunterFromdb = repository.findById(id).orElseThrow();
        HuntingClub clubFromdb = huntingClubRepository.findById(idClub).orElseThrow();

        hunterFromdb.setHuntingClub(clubFromdb);

        repository.save(hunterFromdb);

        return ResponseEntity.ok().build();

    }



}

package com.example.HunterApi.service;

import com.example.HunterApi.Exception.AlreadyExistException;
import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.Exception.WrongAgeException;
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
    private HunterRepository repository;
    @Autowired
    private HuntingClubRepository huntingClubRepository;


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
        Hunter hunterFromDb = repository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        return mapHunterToDto(hunterFromDb);
    }

    public void addHunterService(Hunter add) throws IllegalAccessException
    {
        if(add.getAge()<=0){
            throw new WrongAgeException("Age must be greater then zero");
        }else
            {
                Optional<Hunter> hunterFromDb = repository.findByNameAndSurname(add.getName().toLowerCase(),add.getSurname().toLowerCase());
                if (hunterFromDb.isPresent()) {
                    throw new IllegalAccessException("Hunter already exist");
                }else
                    repository.save(add);
            }
    }



    public void deleteHunterService(Long id) throws IllegalAccessException {
        Hunter hunterFromDb = repository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        repository.delete(hunterFromDb);
    }

    public ResponseEntity<String> addingHunterToClubService(Long id, Long idClub){

        Hunter hunterFromdb = repository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        HuntingClub clubFromdb = huntingClubRepository.findById(idClub).orElseThrow(() -> new NotFoundException("Can not find club, id: " + idClub));
        hunterFromdb.setHuntingClub(clubFromdb);
        repository.save(hunterFromdb);
        return ResponseEntity.ok().build();
    }
    public void updateHunter(Long id, HunterDTO hunterDTO){
        Hunter hunterFromDb = repository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        Optional <Hunter> hunter = repository.findByNameAndSurname(hunterDTO.getName(),hunterDTO.getSurname());
        if (hunter.isPresent()){
            throw new AlreadyExistException("Hunter already exist");
        }else
        hunterFromDb.setName(hunterDTO.getName());
        hunterFromDb.setSurname(hunterDTO.getSurname());
        hunterFromDb.setAge(hunterDTO.getAge());
        repository.save(hunterFromDb);
    }



}

package com.example.HunterApi.service;

import com.example.HunterApi.Exception.AlreadyExistException;
import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.Exception.WrongAgeException;
import com.example.HunterApi.mapping.Mapping;
import com.example.HunterApi.model.Entity.Hunter;
import com.example.HunterApi.model.HunterDto.HunterDTO;
import com.example.HunterApi.model.Entity.HuntingClub;
import com.example.HunterApi.repository.HunterRepository;
import com.example.HunterApi.repository.HuntingClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HunterService {

    @Autowired
    private HunterRepository hunterRepository;
    @Autowired
    private HuntingClubRepository huntingClubRepository;
    @Autowired
    private Mapping mapping;

    public HunterService(HunterRepository hunterRepository,HuntingClubRepository huntingClubRepository,Mapping mapping){
        this.hunterRepository=hunterRepository;
        this.huntingClubRepository=huntingClubRepository;
        this.mapping=mapping;
    }

    public List<HunterDTO> showAllHuntersService(){
        List<HunterDTO> dtos = new ArrayList<>();
        hunterRepository.findAll().forEach(h -> dtos.add(mapping.mapHunterToDto(h)));
        return dtos;
    }

    public HunterDTO getHunterByIdService(Long id)  {
        Hunter hunterFromDb = hunterRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        return mapping.mapHunterToDto(hunterFromDb);
    }

    public void addHunterService(Hunter add)
    {
        if(add.getAge()<18){
            throw new WrongAgeException("Age must be greater then 18");
        }else
            {
                Optional<Hunter> hunterFromDb = hunterRepository.findByNameAndSurname(add.getName(),add.getSurname());
                if (hunterFromDb.isPresent()) {
                    throw new AlreadyExistException("Hunter already exist");
                }else
                    hunterRepository.save(add);
            }
    }

    public void deleteHunterService(Long id)  {
        Hunter hunterFromDb = hunterRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        hunterRepository.delete(hunterFromDb);
    }

    public void addingHunterToClubService(Long id, Long idClub){
        Hunter hunterFromdb = hunterRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
        HuntingClub clubFromdb = huntingClubRepository.findById(idClub).orElseThrow(() -> new NotFoundException("Can not find club, id: " + idClub));
        hunterFromdb.setHuntingClub(clubFromdb);
        hunterRepository.save(hunterFromdb);

    }
    public void updateHunter(Long id, HunterDTO hunterDTO){

        if(hunterDTO.getAge()<18){
            throw  new WrongAgeException("Age must be greater then 18");
        }else {

            Hunter hunterFromDb = hunterRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not find hunter, id: " + id));
            Optional<Hunter> hunter = hunterRepository.findByNameAndSurname(hunterDTO.getName(), hunterDTO.getSurname());
            if (hunter.isPresent()) {
                throw new AlreadyExistException("Hunter already exist");
            } else
                hunterFromDb.setName(hunterDTO.getName());
            hunterFromDb.setSurname(hunterDTO.getSurname());
            hunterFromDb.setAge(hunterDTO.getAge());
            hunterRepository.save(hunterFromDb);
        }
    }

}

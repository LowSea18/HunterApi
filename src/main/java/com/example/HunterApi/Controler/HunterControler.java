package com.example.HunterApi.Controler;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.model.HunterDTO;
import com.example.HunterApi.repository.HunterRepository;
import com.example.HunterApi.service.HunterService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class HunterControler {
    private HunterService hunterService;




    @GetMapping("/hunters")
    public List<HunterDTO> showAllHunters(){
        return hunterService.showAllHuntersService();
    }

    @GetMapping("/hunters/{id}")
    public HunterDTO getHunterByIdService(@PathVariable Long id) throws IllegalAccessException {
        return hunterService.getHunterByIdService(id);
    }



    @PostMapping("/hunters")
    public void addHunter(@RequestBody Hunter add) throws IllegalAccessException
    {
        hunterService.addHunterService(add);

    }

    @DeleteMapping("/hunters/{id}")
    public void deleteHunter(@PathVariable Long id) throws IllegalAccessException{

        hunterService.deleteHunterService(id);
    }

    @PostMapping("/hunters/{hunters_id}/huntingclub/{club_id}")
    public void addHunterToClub(@PathVariable (name = "hunters_id") Long id, @PathVariable (name = "club_id") Long idClub){
        hunterService.addingHunterToClubService(id,idClub);
    }

    @PutMapping("/hunters/{id}")
    public void updateHunter(@PathVariable (name ="id") Long id, @RequestBody HunterDTO hunterDTO){
        hunterService.updateHunter(id , hunterDTO);
    }





}

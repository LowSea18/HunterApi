package com.example.HunterApi.Controler;

import com.example.HunterApi.model.Entity.HuntingClub;
import com.example.HunterApi.model.HuntingClubDto.HuntingClubDto;
import com.example.HunterApi.model.HuntingClubDto.UpdateClubDto;
import com.example.HunterApi.service.HuntingClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HuntingClubControler{

    @Autowired
    private HuntingClubService huntingClubService;

    @GetMapping("/huntingclub")
    public List<HuntingClubDto> showAllHuntersClubs(){
        return huntingClubService.showAllHuntersClubsService();
    }

    @GetMapping("/huntingclub/{id}")
    public HuntingClubDto getHuntingClubByIdS(@PathVariable(name = "id") Long id) throws IllegalAccessException{
        return huntingClubService.getHuntingClubByIdService(id);
    }

    @PostMapping("/huntingclub")
    public void addHunterClub(@RequestBody HuntingClub add) throws IllegalAccessException
    {
        huntingClubService.addHuntingClubService(add);

    }
    @PutMapping("/huntingclub/{id}")
    public void updateClub(@PathVariable (name = "id") Long id, @RequestBody UpdateClubDto updateClubDto){
        huntingClubService.updateClub(id, updateClubDto);
    }
}

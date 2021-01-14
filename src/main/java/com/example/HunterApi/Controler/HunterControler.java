package com.example.HunterApi.Controler;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.repository.HunterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HunterControler {
    final HunterRepository repository;

    public HunterControler(HunterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hunters")
    public List<Hunter> showAllHunters(){
        return repository.findAll();
    }

    @PostMapping("/hunters")
    public void addHunter(@RequestBody Hunter add)
    {
        repository.save(add);

    }


}

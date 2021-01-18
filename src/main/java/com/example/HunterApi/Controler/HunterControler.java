package com.example.HunterApi.Controler;

import com.example.HunterApi.model.Hunter;
import com.example.HunterApi.repository.HunterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/hunters/{id}")
    public void deleteHunter(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/hunters/{id}")
    public ResponseEntity<Hunter> showHunterById(@PathVariable Long id) {
        Optional<Hunter> byId = repository.findById(id);

        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


}

package com.smnb.smnbapi.controller;

import com.smnb.smnbapi.model.Login;
import com.smnb.smnbapi.model.Pacient;
import com.smnb.smnbapi.repository.PacientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping({"/pacients"})
public class PacientController {
    private PacientRepository repository;

    PacientController(PacientRepository pacientRepository) {
        this.repository = pacientRepository;
    }

    @GetMapping
    @CrossOrigin
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    @CrossOrigin
    public ResponseEntity<Pacient> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @CrossOrigin
    public Pacient create(@RequestBody Pacient pacient){

        return repository.save(pacient);
    }

    @PutMapping(value="/{id}")
    @CrossOrigin
    public ResponseEntity<Pacient> update(@PathVariable("id") long id,
                                          @RequestBody Pacient pacient){
        return repository.findById(id)
                .map(record -> {
                    record.setName(pacient.getName());
                    record.setEmail(pacient.getEmail());
                    record.setPhone(pacient.getPhone());
                    record.setEndereco(pacient.getEndereco());
                    record.setPassword(pacient.getPassword());
                    Pacient updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/login"})
    @CrossOrigin
    public ResponseEntity<Boolean> login(@RequestBody Login login){
        Optional<Pacient> pacient = Optional.ofNullable(repository.findByEmail(login.getEmail()));

        if (pacient.isPresent() && pacient.get().getPassword() != null && pacient.get().getPassword().equals(login.getPassword())){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

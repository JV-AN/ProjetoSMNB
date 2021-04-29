package com.smnb.smnbapi.controller;

import com.smnb.smnbapi.model.Schedule;
import com.smnb.smnbapi.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/schedule"})
public class ScheduleController {
    private ScheduleRepository scheduleRepository;

    ScheduleController(ScheduleRepository scheduleRepository) { this.scheduleRepository = scheduleRepository;}

    @GetMapping
    @CrossOrigin
    public List findAll(){
        return scheduleRepository.findAll();
    }

    @GetMapping(path = {"/{email}"})
    @CrossOrigin
    public List<Schedule> findById(@PathVariable String email){
        return scheduleRepository.findByPacientEmail(email);
    }

    @PostMapping
    @CrossOrigin
    public Schedule create(@RequestBody Schedule schedule){

        return scheduleRepository.save(schedule);
    }


    @DeleteMapping(path ={"/{id}"})
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return scheduleRepository.findById(id)
                .map(record -> {
                    scheduleRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}

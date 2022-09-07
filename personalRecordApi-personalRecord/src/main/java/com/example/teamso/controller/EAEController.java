package com.example.teamso.controller;

import com.example.teamso.PersonalRepository.PersonalRepository;
import com.example.teamso.entity.PersonalData;
import com.example.teamso.entity.PersonalDataDTO;
import com.example.teamso.entity.Status;
import com.example.teamso.service.PersonalService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@NoArgsConstructor
public class EAEController {


    @Autowired private PersonalService personalService;
    @Autowired private PersonalRepository personalRepository;

    @PostMapping("/checkinout")
    public ResponseEntity<Status> checkInOut(@RequestBody PersonalDataDTO personalDataDTO){
        return new ResponseEntity<>(personalService.entrancePersonal(personalDataDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/checkinout")
    public ResponseEntity<List<PersonalData>> getPersonalsByType(@RequestParam boolean type) {
        return new ResponseEntity<List<PersonalData>> (personalRepository.findByType(type), HttpStatus.OK);
    }

}



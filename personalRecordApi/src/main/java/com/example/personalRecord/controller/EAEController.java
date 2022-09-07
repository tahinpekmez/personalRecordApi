package com.example.personalRecord.controller;

import com.example.personalRecord.PersonalRepository.PersonalRepository;
import com.example.personalRecord.entity.PersonalData;
import com.example.personalRecord.entity.PersonalDataDTO;
import com.example.personalRecord.entity.Status;
import com.example.personalRecord.service.PersonalService;
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



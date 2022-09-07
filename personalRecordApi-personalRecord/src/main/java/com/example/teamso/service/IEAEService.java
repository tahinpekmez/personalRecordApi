package com.example.teamso.service;

import com.example.teamso.PersonalRepository.PersonalRepository;
import com.example.teamso.entity.PersonalData;
import com.example.teamso.entity.PersonalDataDTO;
import com.example.teamso.entity.Status;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface IEAEService {

    Status entrancePersonal(PersonalDataDTO personalDataDTO);
    Status checkLocation(double latitude, double longitude);


    }

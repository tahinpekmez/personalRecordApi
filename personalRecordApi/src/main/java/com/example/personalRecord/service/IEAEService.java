package com.example.personalRecord.service;

import com.example.personalRecord.entity.PersonalDataDTO;
import com.example.personalRecord.entity.Status;

import java.util.List;
import java.util.UUID;

public interface IEAEService {

    Status entrancePersonal(PersonalDataDTO personalDataDTO);
    Status checkLocation(double latitude, double longitude);


    }

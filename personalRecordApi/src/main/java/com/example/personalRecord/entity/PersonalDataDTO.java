package com.example.personalRecord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PersonalDataDTO {

    private String name;
    private String surname;
    private boolean type;
    private String machineId;
    private String branch;
    private Double latitude;
    private Double longitude;
    private Status location;
}

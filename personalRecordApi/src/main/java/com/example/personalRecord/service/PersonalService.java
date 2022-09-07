package com.example.personalRecord.service;

import com.example.personalRecord.PersonalRepository.PersonalRepository;
import com.example.personalRecord.controller.EAEController;
import com.example.personalRecord.entity.PersonalData;
import com.example.personalRecord.entity.PersonalDataDTO;
import com.example.personalRecord.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@AllArgsConstructor
@Data
@Service
@NoArgsConstructor
public class PersonalService implements IEAEService {

    private static final Logger logger = Logger.getLogger(String.valueOf(EAEController.class));
    Double d;
    Status loc;
    @Autowired
    private PersonalRepository personalRepository;

    @Override
    public Status entrancePersonal(PersonalDataDTO personalDataDTO) {
        List<PersonalData> personalDataList = personalRepository.findAll(Sort.by(Sort.Direction.DESC, "lastUpdate"));
        int l = personalDataList.size();
        int[] stream = IntStream.range(0, l).toArray();

        PersonalData personalData = new PersonalData();
        personalData.setMachineId(personalDataDTO.getMachineId());
        personalData.setName(personalDataDTO.getName());
        personalData.setSurname(personalDataDTO.getSurname());
        personalData.setType(personalDataDTO.isType());
        personalData.setBranch(personalDataDTO.getBranch());
        personalData.setLatitude(personalDataDTO.getLatitude());
        personalData.setLongitude(personalDataDTO.getLongitude());
        personalData.setLastUpdate(LocalDateTime.now());
        personalData.setLocation(checkLocation(personalData.getLatitude(), personalData.getLongitude()));

        if (personalDataList.size() > 0) {
            for (Integer i : stream) {
                if (personalDataList.stream().anyMatch(personalData1 -> personalData1.getMachineId().equals(personalDataDTO.getMachineId())) && personalDataList.get(i).isType() && personalDataDTO.isType()) {
                    return Status.PERSONAL_ALREADY_EXISTS;
                }
                else if (personalDataList.stream().anyMatch(personalData1 -> personalData1.getMachineId().equals(personalDataDTO.getMachineId())) && !personalDataList.get(i).isType() && !personalDataDTO.isType()) {
                    return Status.PERSONAL_ALREADY_OUT;
                }
                else if (personalDataList.stream().anyMatch(personalData1 -> personalData1.getMachineId().equals(personalDataDTO.getMachineId())) && personalDataList.get(i).isType() && !personalDataDTO.isType()) {
                    personalData.setUserID(personalDataList.get(i).getUserID());
                    personalRepository.save(personalData);
                    return Status.PERSONAL_EXIT;
                }
                else if (personalDataList.stream().anyMatch(personalData1 -> personalData1.getMachineId().equals(personalDataDTO.getMachineId())) && !personalDataList.get(i).isType() && personalDataDTO.isType()) {
                    personalData.setUserID(personalDataList.get(i).getUserID());
                    personalRepository.save(personalData);
                    return Status.PERSONAL_ENTRANCE;
                }
                else if (personalDataList.stream().noneMatch(personalData1 -> personalData1.getMachineId().equals(personalDataDTO.getMachineId()))) {
                    personalData.setUserID(UUID.randomUUID().toString());
                    personalRepository.save(personalData);
                    return Status.PERSONAL_CREATED;
                }
            }
        }
        personalData.setUserID(UUID.randomUUID().toString());
        personalRepository.save(personalData);
        return Status.FIRST_PERSONAL_CREATED;
    }

    @Override
    public Status checkLocation(double latitude, double longitude) {

        PersonalData personalData = new PersonalData();
        personalData.setLatitude(latitude);
        personalData.setLongitude(longitude);


        double lon1 = Math.toRadians(30.00);
        double lon2 = Math.toRadians(personalData.getLongitude());
        double lat1 = Math.toRadians(40.00);
        double lat2 = Math.toRadians(personalData.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        d = (c * r);

        if (d <= 0.5) {
            return Status.NEAR_PLACE;
        } else if (d > 0.5) {
            return Status.FAR_PLACE;
        }
        return Status.UNKNOWN;
    }
}
package com.example.personalRecord.PersonalRepository;

import com.example.personalRecord.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PersonalRepository extends JpaRepository<PersonalData, UUID> {
  List<PersonalData> findByType(boolean type);

}
package com.example.teamso.PersonalRepository;

import com.example.teamso.entity.PersonalData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PersonalRepository extends JpaRepository<PersonalData, UUID> {
  List<PersonalData> findByType(boolean type);

}
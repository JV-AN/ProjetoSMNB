package com.smnb.smnbapi.repository;

import com.smnb.smnbapi.model.Pacient;
import com.smnb.smnbapi.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPacientEmail (String pacientEmail);

}

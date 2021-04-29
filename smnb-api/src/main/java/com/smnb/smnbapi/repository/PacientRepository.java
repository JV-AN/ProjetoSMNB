package com.smnb.smnbapi.repository;

import com.smnb.smnbapi.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {

    Pacient findByEmail(String email);


}

package com.home.project_megamenu.reservations;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 환자의 예약 정보 조회
    List<Reservation> findByPatient_PatientId(Long patientId);

}

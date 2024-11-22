package com.home.project_megamenu.reservations;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByPatient_PatientId(Long patientId);

    // 예약일로 예약을 검색하는 메서드 (LocalDate로 변경)
    List<Reservation> findByReservationDate(LocalDate reservationDate);


}

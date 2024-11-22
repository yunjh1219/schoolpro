package com.home.project_megamenu.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // 예약 저장 또는 업데이트 (Reservation을 저장하는 메서드)
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // 예약 업데이트를 위한 메서드 추가
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // 예약 상태를 'y' (확정)로 변경
    public Reservation confirmReservation(Long id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservation.setStatus("y");
            return saveReservation(reservation);
        }
        return null;
    }

    // 예약 상태를 'n' (취소)로 변경
    public Reservation cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservation.setStatus("n");
            return saveReservation(reservation);
        }
        return null;
    }

    // 특정 환자의 예약 정보 조회
    public List<Reservation> getReservationsByPatientId(Long patientId) {
        return reservationRepository.findByPatient_PatientId(patientId);
    }



}
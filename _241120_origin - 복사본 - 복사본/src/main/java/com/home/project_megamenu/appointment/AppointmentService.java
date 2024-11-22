package com.home.project_megamenu.appointment;

import com.home.project_megamenu.dto.AppointmentForm;
import com.home.project_megamenu.patient.Patient;
import com.home.project_megamenu.patient.PatientRepository;
import com.home.project_megamenu.reservations.Reservation;
import com.home.project_megamenu.reservations.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AppointmentService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public void processAppointmentForm(AppointmentForm appointmentForm) {
        // 1. 환자 정보 확인
        Patient existingPatient = patientRepository.findByPhonenumber(appointmentForm.getPhonenumber());

        Patient patient;
        if (existingPatient != null) {
            // 2. 환자 정보가 있으면 기존 환자 사용
            patient = existingPatient;
        } else {
            // 3. 환자 정보가 없으면 새로운 환자 생성
            patient = new Patient();
            patient.setName(appointmentForm.getName());
            patient.setEmail(appointmentForm.getEmail());
            patient.setPhonenumber(appointmentForm.getPhonenumber());
            patient.setGender(appointmentForm.getGender());

            // 새로운 환자 저장
            patientRepository.save(patient);  // patient 저장
        }

        // 4. 예약 정보 생성
        Reservation reservation = new Reservation();
        reservation.setReservationDate(appointmentForm.getReservationDate());
        reservation.setHourselect(appointmentForm.getHourselect());
        reservation.setMinutesselect(appointmentForm.getMinutesselect());
        reservation.setTreatment1(appointmentForm.getTreatment1());
        reservation.setTreatment2(appointmentForm.getTreatment2());
        reservation.setAdditionalText(appointmentForm.getAdditionalText());
        reservation.setPatient(patient);  // 환자와 예약 연결

        // 5. 예약 저장
        reservationRepository.save(reservation);  // 예약 저장
    }
}
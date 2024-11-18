package com.home.project_megamenu.appointment;

import com.home.project_megamenu.dto.AppointmentForm;
import com.home.project_megamenu.patient.PatientRepository;
import com.home.project_megamenu.reservations.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AppointmentService appointmentService;

    // 폼 데이터를 @ModelAttribute로 바인딩
    @PostMapping("/submit-appointment")
    public ResponseEntity<String> submitAppointment(@RequestBody AppointmentForm appointmentForm) {
        appointmentService.processAppointmentForm(appointmentForm);
        return ResponseEntity.ok("예약이 성공적으로 완료되었습니다.");
    }




}
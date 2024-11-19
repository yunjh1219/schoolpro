package com.home.project_megamenu.patient;

import com.home.project_megamenu.reservations.Reservation;
import com.home.project_megamenu.reservations.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private ReservationRepository reservationRepository; // 예약 정보를 가져오기 위한 리포지토리 추가

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    // 환자 목록 조회
    @GetMapping("/list")
    public String getAllPatients(@RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "searchType", required = false) String searchType,
                                 @RequestParam(value = "reset", required = false) String reset,
                                 Model model) {

        List<Patient> patients;

        // 전체 목록 보기 버튼 클릭 시
        if ("true".equals(reset)) {
            patients = patientRepository.findAll();  // 전체 목록을 반환
            search = null;  // 검색어 초기화
            searchType = null;  // 검색 조건 초기화
        } else if (search != null && !search.isEmpty() && searchType != null) {
            // 검색 조건이 있을 때, PatientService에서 검색 처리
            patients = patientService.searchPatients(search, searchType);
        } else {
            // 검색 조건이 없으면 모든 환자 목록
            patients = patientRepository.findAll();
        }

        // Thymeleaf로 전달할 모델에 환자 목록과 검색 필드 값을 추가
        model.addAttribute("patients", patients);
        model.addAttribute("search", search);  // 검색어 유지
        model.addAttribute("searchType", searchType);  // 검색 조건 유지
        return "pages/patientlist";  // patientlist.html 페이지 반환
    }

    // 환자 상세 조회
    @GetMapping("/detail/{id}")
    public String getPatientDetail(@PathVariable("id") Long patientId, Model model) {
        // 환자 정보 조회
        Patient patient = patientRepository.findById(patientId).orElse(null);

        // 해당 환자의 예약 목록 조회
        List<Reservation> reservations = reservationRepository.findByPatient_PatientId(patientId);

        model.addAttribute("patient", patient); // 환자 정보 전달
        model.addAttribute("reservations", reservations); // 예약 목록 전달
        return "pages/patientDetail";  // patientDetail.html 페이지 반환
    }

    // 환자 추가 요청 처리
    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@RequestParam String name,
                                        @RequestParam String phonenumber,
                                        @RequestParam String email,
                                        @RequestParam String gender) {
        Patient patient = Patient.builder()
                .name(name)
                .phonenumber(phonenumber)
                .email(email)
                .gender(gender)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        patientService.savePatient(patient);
        return ResponseEntity.ok().body("{\"success\": true}");
    }
}
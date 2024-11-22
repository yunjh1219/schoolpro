package com.home.project_megamenu.patient;

import com.home.project_megamenu.reservations.Reservation;
import com.home.project_megamenu.reservations.ReservationRepository;
import com.home.project_megamenu.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ReservationService reservationService;  // 예약 서비스를 가져오기 위한 서비스

    @Autowired
    private PatientService patientService;

    // 환자 목록 조회
    @GetMapping("/list")
    public String getAllPatients(@RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "searchType", required = false) String searchType,
                                 @RequestParam(value = "reset", required = false) String reset,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 Model model) {

        Pageable pageable = PageRequest.of(page, size);  // 페이지 번호와 크기 설정
        Page<Patient> patientPage;

        // 전체 목록 보기 버튼 클릭 시
        if ("true".equals(reset)) {
            patientPage = patientService.getPatients(pageable);  // 전체 환자 목록 반환
        } else if (search != null && !search.isEmpty() && searchType != null) {
            // 검색 조건이 있을 때, PatientService에서 검색 처리
            patientPage = patientService.searchPatients(search, searchType, pageable);
        } else {
            // 검색 조건이 없으면 모든 환자 목록 반환
            patientPage = patientService.getPatients(pageable);
        }

        model.addAttribute("patients", patientPage.getContent());  // 현재 페이지의 환자 목록
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", patientPage.getTotalPages());  // 전체 페이지 수
        model.addAttribute("totalItems", patientPage.getTotalElements());  // 전체 환자 개수
        model.addAttribute("search", search);  // 검색어 유지
        model.addAttribute("searchType", searchType);  // 검색 조건 유지

        return "pages/patientlist";  // 환자 목록을 표시할 페이지
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

        patientService.savePatient(patient);  // 환자 정보 저장
        return ResponseEntity.ok().body("{\"success\": true}");  // 성공 응답 반환
    }

    // 환자 정보 수정 요청 처리
    @PostMapping("/update")
    public String updatePatient(@RequestParam Long patientId,
                                @RequestParam String name,
                                @RequestParam String phonenumber,
                                @RequestParam String email,
                                @RequestParam String gender) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            patient.setName(name);
            patient.setPhonenumber(phonenumber);
            patient.setEmail(email);
            patient.setGender(gender);
            patient.setUpdated_at(LocalDateTime.now());  // 수정일자 갱신

            patientService.savePatient(patient);  // 수정된 환자 정보 저장
        }
        return "redirect:/patient/list";  // 환자 목록 페이지로 리다이렉트
    }

    // 환자 정보 수정 화면 요청 처리
    @GetMapping("/{id}/edit")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            List<Reservation> reservations = reservationService.getReservationsByPatientId(id);  // 예약 정보 가져오기
            model.addAttribute("patient", patient);  // 환자 정보 모델에 추가
            model.addAttribute("reservations", reservations);  // 예약 정보 모델에 추가
        }
        return "pages/patientedit";  // 환자 수정 페이지로 이동
    }

}
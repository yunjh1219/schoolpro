package com.home.project_megamenu.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientRepository.findAll();  // 모든 환자 정보 조회
        model.addAttribute("patients", patients);  // Thymeleaf로 환자 목록 전달
        return "pages/patientlist";  // patient.html 페이지 반환
    }

    // 환자 이름 검색
    @GetMapping("/search")
    public String searchPatient(@RequestParam("patientName") String patientName, Model model) {
        Patient patient = patientService.findByName(patientName); // patientService를 사용
        model.addAttribute("patient", patient);
        return "pages/patient_details"; // 환자 상세 페이지 템플릿 경로 명시
    }

    // 환자 정보 수정
    @PostMapping("/update")
    public String updatePatient(@RequestParam Long patient_id,
                                @RequestParam String name,
                                @RequestParam String phonenumber,
                                @RequestParam String email,
                                @RequestParam String gender,
                                Model model) {
        Patient patient = patientRepository.findById(patient_id).orElse(null);
        if (patient != null) {
            patient.setName(name);
            patient.setPhonenumber(phonenumber);
            patient.setEmail(email);
            patient.setGender(gender);
            patientRepository.save(patient);
            model.addAttribute("patient", patient);
            model.addAttribute("reservations", patient.getReservations());
            return "pages/patient_details";  // 환자 상세 페이지로 다시 이동
        } else {
            model.addAttribute("error", "Patient not found");
            return "error";  // 환자가 존재하지 않으면 error 페이지로 이동
        }
    }

    //환자생성
    @PostMapping("/create")
    public String createPatient(@RequestParam String name,
                                @RequestParam String phonenumber,
                                @RequestParam String email,
                                @RequestParam String gender,
                                Model model) {
        // 새로운 환자 객체 생성
        Patient patient = new Patient();
        patient.setName(name);
        patient.setPhonenumber(phonenumber);
        patient.setEmail(email);
        patient.setGender(gender);

        // 환자 정보를 저장
        patientRepository.save(patient);

        // 환자 등록 성공 메시지
        model.addAttribute("message", "환자 등록이 완료되었습니다.");

        // 환자 목록 페이지로 리다이렉트
        return "pages/patient_details";
    }
}



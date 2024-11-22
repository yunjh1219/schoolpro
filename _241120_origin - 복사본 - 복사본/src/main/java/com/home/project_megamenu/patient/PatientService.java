package com.home.project_megamenu.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;  // PatientRepository를 사용하여 환자 정보 저장

    // 특정 환자 조회
    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    // 환자 저장
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    // 페이징 처리된 모든 환자 조회
    public Page<Patient> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    // 검색 조건에 맞는 환자 조회 (페이징 처리)
    public Page<Patient> searchPatients(String search, String searchType, Pageable pageable) {
        switch (searchType) {
            case "patientId":
                try {
                    Long patientId = Long.parseLong(search);  // String을 Long으로 변환
                    return patientRepository.findByPatientId(patientId, pageable);
                } catch (NumberFormatException e) {
                    return Page.empty();  // 숫자가 아니면 빈 페이지 반환
                }
            case "name":
                return patientRepository.findByNameContaining(search, pageable);
            case "phonenumber":
                return patientRepository.findByPhonenumberContaining(search, pageable);
            default:
                return Page.empty();  // 검색 타입이 잘못되었을 경우 빈 페이지 반환
        }
    }
}
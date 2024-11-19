package com.home.project_megamenu.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;  // PatientRepository를 사용하여 환자 정보 저장

    // 환자 저장 메서드
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }


    // 모든 환자 조회
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();  // 모든 환자 정보를 조회
    }

    // 검색 조건에 맞는 환자 조회
    public List<Patient> searchPatients(String search, String searchType) {
        List<Patient> patients = new ArrayList<>();  // 기본값을 빈 리스트로 설정

        // 검색 유형에 따라 검색 처리
        switch (searchType) {
            case "patientId":
                try {
                    Long patientId = Long.parseLong(search);  // String을 Long으로 변환
                    Patient patient = patientRepository.findByPatientId(patientId);  // patient_id로 정확히 검색
                    if (patient != null) {
                        patients.add(patient);  // 환자가 존재하면 리스트에 추가
                    }
                } catch (NumberFormatException e) {
                    // patient_id가 숫자가 아닌 경우 예외 처리
                    // 예외 처리 시 빈 리스트를 그대로 반환
                }
                break;
            case "name":
                patients = patientRepository.findByNameContaining(search);  // 이름으로 검색
                break;
            case "phonenumber":
                patients = patientRepository.findByPhonenumberContaining(search);  // 전화번호로 검색
                break;
            default:
                break;
        }
        return patients;  // 빈 리스트 혹은 결과 리스트 반환
    }


}
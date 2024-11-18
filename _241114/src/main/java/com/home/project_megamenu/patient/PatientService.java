package com.home.project_megamenu.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;  // PatientRepository를 사용하여 환자 정보 저장

    // 환자 저장 메서드
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }



    // 전화번호로 환자 조회
    public Patient findByPhoneNumber(String phonumber) {
        return patientRepository.findByPhonenumber(phonumber);  // PatientRepository의 메서드를 호출
    }

    // 이름으로 환자 찾기
    public Patient findByName(String name) {
        return patientRepository.findByName(name);
    }

    // ID로 환자 조회 메서드
    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

}
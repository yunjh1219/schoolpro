package com.home.project_megamenu.patient;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // 전화번호로 환자 조회
    Patient findByPhonenumber(String phonumber);

    Patient findByPatientId(Long patientId);

    // 이름에 대한 검색
    List<Patient> findByNameContaining(String name);

    // 전화번호에 대한 검색
    List<Patient> findByPhonenumberContaining(String phonenumber);

}
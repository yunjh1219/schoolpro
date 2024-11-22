package com.home.project_megamenu.patient;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // 전화번호로 환자 조회
    Patient findByPhonenumber(String phonumber);

    // 전화번호로 환자 검색 (페이징 처리)
    Page<Patient> findByPhonenumberContaining(String phonenumber, Pageable pageable);

    // 환자 번호로 조회
    Patient findByPatientId(Long patientId);

    // 이름으로 환자 검색 (페이징 처리)
    Page<Patient> findByNameContaining(String name, Pageable pageable);

    // 환자 번호로 검색 (페이징 처리)
    Page<Patient> findByPatientId(Long patientId, Pageable pageable);


}
package com.home.project_megamenu.patient;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    // 전화번호로 환자 조회
    Patient findByPhonenumber(String phonumber);

    // 이름으로 환자 찾기
    Patient findByName(String name);


}
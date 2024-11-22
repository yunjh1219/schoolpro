package com.home.project_megamenu.reservations;

import com.home.project_megamenu.patient.Patient;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_sequence", initialValue = 1000, allocationSize = 1)
    @NotNull  // null 값 허용하지 않음
    private Long reservation_id;  // 예약번호는 자동 생성

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @NotNull  // null 값 허용하지 않음
    private LocalDate reservation_date;  // 예약 날짜
    @NotNull  // null 값 허용하지 않음
    private int hour_select;  // 예약 시간 (시)
    @NotNull  // null 값 허용하지 않음
    private int minutes_select;  // 예약 분 (분)

    private String treatment1;  // 첫 번째 치료 항목
    private String treatment2;  // 두 번째 치료 항목
    private String additionalText;  // 추가 설명

    private String status;  // "y" (확정), "n" (취소)

    @NotNull  // null 값 허용하지 않음
    private LocalDateTime created_at;  // 예약 생성일

    @NotNull  // null 값 허용하지 않음
    private LocalDateTime updated_at;  // 예약 수정일

    // 예약 생성 시 자동으로 날짜 설정
    @PrePersist
    public void onCreate() {
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");

        if (reservation_date == null) {
            reservation_date = LocalDate.now(koreaZoneId);  // 예약 날짜를 현재 날짜로 설정
        }

        if (created_at == null) {
            created_at = LocalDateTime.now(koreaZoneId);  // 생성일 설정
        }
        updated_at = LocalDateTime.now(koreaZoneId);  // 수정일은 생성 시 설정
    }

    // 예약 수정 시 자동으로 날짜 설정
    @PreUpdate
    public void onUpdate() {
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        updated_at = LocalDateTime.now(koreaZoneId);  // 수정일 업데이트
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", reservation_date=" + reservation_date +
                ", hour_select=" + hour_select +
                ", minutes_select=" + minutes_select +
                ", treatment1='" + treatment1 + '\'' +
                ", treatment2='" + treatment2 + '\'' +
                ", additionalText='" + additionalText + '\'' +
                ", status='" + status + '\'' +
                ", patient_id=" + (patient != null ? patient.getPatientId() : null) + // patient ID만 출력
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
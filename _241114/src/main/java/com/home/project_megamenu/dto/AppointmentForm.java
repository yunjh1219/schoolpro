package com.home.project_megamenu.dto;

import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class AppointmentForm {
    private String name;
    private String email;
    private String phonenumber;
    private String gender;
    private LocalDate reservation_date;
    private int hour_select;
    private int minutes_select;
    private String treatment1;
    private String treatment2;
    private String additionalText;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonumber) {
        this.phonenumber = phonumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservationDate) {
        this.reservation_date = reservationDate;
    }

    public int getHour_select(){return hour_select = hour_select; }

    public void setHour_select(int hour_select) {this.hour_select = hour_select; }

    public int getMinutes_select(){return minutes_select; }

    public void setMinutes_select(int minutes_select) {this.minutes_select = minutes_select; }

    public String getTreatment1() {
        return treatment1;
    }

    public void setTreatment1(String treatment1) {
        this.treatment1 = treatment1;
    }

    public String getTreatment2() {
        return treatment2;
    }

    public void setTreatment2(String treatment2) {
        this.treatment2 = treatment2;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }
}
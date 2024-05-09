package com.riwi.admin_riwi.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "appointment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String coderName;
    @Column(nullable = false)
    private String start;
    @Column(nullable = false)
    private String end;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    @JoinColumn(name = "coder_id", referencedColumnName = "id")
    private Integer coder;
    @JoinColumn(name = "pyschologist_id", referencedColumnName = "id")
    private Integer pyschologist;
}

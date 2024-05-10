package com.riwi.admin_riwi.domain.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.riwi.admin_riwi.util.enums.ClanEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "coder")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 15, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(nullable=false)
    private BigInteger cc;
    private String password;
    @Column(length = 15, nullable = false)
    private String phone;
    private LocalDate dateborn;
    private String photo;
    private ClanEnum clan;
}

package com.riwi.admin_riwi.domain.entities;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "psychologist")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Psychologist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(nullable=false)
    private BigInteger cc;
    private String password;
    private String photo;

}

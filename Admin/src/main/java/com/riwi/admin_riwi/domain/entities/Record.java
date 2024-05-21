package com.riwi.admin_riwi.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate dateRecord;
    @Lob
    private String registration;
    @Lob
    private String observation;
    @ManyToOne
    @JoinColumn(name = "coder_id",referencedColumnName = "_id")
    private Coder coder;
    
}

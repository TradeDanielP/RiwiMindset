package com.riwi.test.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "testScore")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idScore;
    @Column(nullable = false)
    public String idCoder;
    @Column(nullable = false)
    public String score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test" , referencedColumnName = "idTest")
    private TestEntity test;

}

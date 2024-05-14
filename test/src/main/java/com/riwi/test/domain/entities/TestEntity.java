package com.riwi.test.domain.entities;

import java.util.List;

import com.riwi.test.utils.enums.TypeTest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity( name = "testEntity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTest;
    @Column(nullable = false)
    private TypeTest typeTest;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    @ElementCollection
    private List<String> answers;
    @Column(nullable = false)
    private int answerCorrectIndex;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "test",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<TestScore> testScore;
}

package com.riwi.admin_riwi.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "psychologist")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Psychologist {

    @Id
    private String _id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(nullable=false)
    private String document;
    @Column(nullable = false)
    private String password;
    private String photo;
    @Column(nullable=false)
    private String role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "psychologist",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<Appointment> appointments;
}

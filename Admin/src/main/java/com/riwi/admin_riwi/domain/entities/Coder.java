package com.riwi.admin_riwi.domain.entities;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.riwi.admin_riwi.util.enums.ClanEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity(name = "coder")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coder {

    @Id
    private String _id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable=false)
    private String document;
    @Column(nullable=false)
    private String password;
    @Column(length = 21, nullable = false)
    private String phone;
    @Column(nullable=false)
    private LocalDate dateBirth;

    private String photo;
    @Column(nullable=false)
    private ClanEnum clan;
    @Column(nullable=false)
    private String role;
    
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude // @121312312
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "coder",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Appointment> appointments;
}

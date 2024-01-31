package com.mazzillio.med.voll.api.patient;

import com.mazzillio.med.voll.api.address.Address;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "patients")
@Entity(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private Address address;
    private boolean active;

    public Patient(CreatePatientData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updatePatient(UpdatePatientData data) {
        this.name = data.name() != null ? data.name() : this.name;
        this.phone = data.phone() != null ? data.phone() : this.phone;
        if (data.address() != null) this.address.updateAddress(data.address());
    }

    public void exclude() {
        this.active = false;
    }
}

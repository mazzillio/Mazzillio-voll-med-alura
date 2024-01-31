package com.mazzillio.med.voll.api.doctor;


import com.mazzillio.med.voll.api.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "doctors")
@Entity(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private boolean active;

    public Doctor(CreateDoctorData data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.phone = data.phone();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateDoctor(UpdateDoctorData data) {
        this.name = data.name() != null ? data.name() : this.name;
        this.phone = data.phone() != null ? data.phone() : this.phone;
        if (data.address() != null) this.address.updateAddress(data.address());
    }

    public void exclude() {
        this.active = false;
    }
}

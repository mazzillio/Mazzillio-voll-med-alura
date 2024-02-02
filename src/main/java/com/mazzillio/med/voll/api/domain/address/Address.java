package com.mazzillio.med.voll.api.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Column(name = "public_place")
    private String publicPlace;
    private String neighborhood;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Address(AddressData data) {
        this.publicPlace = data.publicPlace();
        this.neighborhood = data.neighborhood();
        this.zipCode = data.zipCode();
        this.city = data.city();
        this.uf = data.uf();
        this.complement = data.complement();
        this.number = data.number();
    }

    public void updateAddress(AddressData data) {
        this.publicPlace = data.publicPlace() != null ? data.publicPlace() : this.publicPlace;
        this.neighborhood = data.neighborhood() != null ? data.neighborhood() : this.neighborhood;
        this.zipCode = data.zipCode() != null ? data.zipCode() : this.zipCode;
        this.city = data.city() != null ? data.city() : this.city;
        this.uf = data.uf() != null ? data.uf() : this.uf;
        this.complement = data.complement() != null ? data.complement() : this.complement;
        this.number = data.number() != null ? data.number() : this.number;


    }
}

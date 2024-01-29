package com.mazzillio.med.voll.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Column(name="public_place")
    private String publicPlace;
    private String neighborhood;
    @Column(name="zip_code")
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
}

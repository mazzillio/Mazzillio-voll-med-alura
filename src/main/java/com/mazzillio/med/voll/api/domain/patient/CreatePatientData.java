package com.mazzillio.med.voll.api.domain.patient;

import com.mazzillio.med.voll.api.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreatePatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @Valid
        AddressData address
) {
}

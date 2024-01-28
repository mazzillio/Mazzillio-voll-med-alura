package com.mazzillio.med.voll.api.patient;

import com.mazzillio.med.voll.api.address.AddressData;

public record CreatePatientData(String name, String email, String phone, AddressData address) {
}

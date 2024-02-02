package com.mazzillio.med.voll.api.domain.doctor;

import com.mazzillio.med.voll.api.domain.address.AddressData;

public record UpdateDoctorData(String name, String phone, AddressData address) {
}

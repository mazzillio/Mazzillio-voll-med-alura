package com.mazzillio.med.voll.api.doctor;

import com.mazzillio.med.voll.api.address.AddressData;

public record UpdateDoctorData(String name, String phone, AddressData address) {
}

package com.mazzillio.med.voll.api.domain.patient;

import com.mazzillio.med.voll.api.domain.address.AddressData;

public record UpdatePatientData(String name, String phone, AddressData address) {

}

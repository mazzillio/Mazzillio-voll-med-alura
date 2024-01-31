package com.mazzillio.med.voll.api.patient;

import com.mazzillio.med.voll.api.address.AddressData;

public record UpdatePatientData(String name, String phone, AddressData address) {

}

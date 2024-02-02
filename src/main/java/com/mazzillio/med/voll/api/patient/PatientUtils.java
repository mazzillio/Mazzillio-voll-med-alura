package com.mazzillio.med.voll.api.patient;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

public class PatientUtils {
    public static String getNextPage(Page<Patient> patientPage, UriComponentsBuilder uriComponentsBuilder) {
        if (patientPage.hasNext()) {
            return uriComponentsBuilder.path("/patients").queryParam("page", patientPage.getNumber() + 1).build().toUriString();
        }
        return null;

    }
}

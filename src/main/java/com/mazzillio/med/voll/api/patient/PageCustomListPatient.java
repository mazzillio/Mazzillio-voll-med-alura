package com.mazzillio.med.voll.api.patient;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageCustomListPatient(List<ListDataPatient> content, int pageNumber, Long totalElements, int totalPages) {
    public PageCustomListPatient(Page<Patient> patientPage) {
        this(
                patientPage.getContent().stream().map(ListDataPatient::new).toList(),
                patientPage.getPageable().getPageNumber(),
                patientPage.getTotalElements(),
                patientPage.getTotalPages()
        );
    }
}

package com.mazzillio.med.voll.api.patient;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


public record PageCustomListPatient(List<ListDataPatient> content, int pageNumber, Long totalElements, int totalPages, String nextPage) {
    public PageCustomListPatient(Page<Patient> patientPage, UriComponentsBuilder uriComponentsBuilder) {
        this(
         patientPage.getContent().stream().map(ListDataPatient::new).toList(),
                patientPage.getPageable().getPageNumber(),
                patientPage.getTotalElements(),
                patientPage.getTotalPages(),
                PatientUtils.getNextPage(patientPage,uriComponentsBuilder)
        );
    }
}

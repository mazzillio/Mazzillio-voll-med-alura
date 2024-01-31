package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.address.PatientRepository;
import com.mazzillio.med.voll.api.patient.CreatePatientData;
import com.mazzillio.med.voll.api.patient.ListDataPatient;
import com.mazzillio.med.voll.api.patient.PageCustomListPatient;
import com.mazzillio.med.voll.api.patient.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void createPatient(@RequestBody @Valid CreatePatientData createPatientData) {
        patientRepository.save(new Patient(createPatientData));
    }
    @GetMapping
    public PageCustomListPatient list(@PageableDefault(size = 10, sort = "name") Pageable pagination) {
        return new PageCustomListPatient(patientRepository.findAll(pagination));
    }
}

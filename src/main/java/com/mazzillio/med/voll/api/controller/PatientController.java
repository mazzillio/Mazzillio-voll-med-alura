package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.address.PatientRepository;
import com.mazzillio.med.voll.api.patient.CreatePatientData;
import com.mazzillio.med.voll.api.patient.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

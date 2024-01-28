package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.patient.CreatePatientData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @PostMapping
    public void createPatient(@RequestBody CreatePatientData createPatientData){
        System.out.println(createPatientData);
    }
}

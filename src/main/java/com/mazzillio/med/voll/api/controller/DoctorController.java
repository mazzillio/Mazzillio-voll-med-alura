package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.doctor.CreateDoctorData;
import com.mazzillio.med.voll.api.doctor.Doctor;
import com.mazzillio.med.voll.api.doctor.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreateDoctorData createDoctorData){
        doctorRepository.save(new Doctor(createDoctorData));
    }
}

package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.doctor.CreateDoctorData;
import com.mazzillio.med.voll.api.doctor.Doctor;
import com.mazzillio.med.voll.api.doctor.DoctorRepository;
import com.mazzillio.med.voll.api.doctor.ListDataDoctor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreateDoctorData createDoctorData) {
        doctorRepository.save(new Doctor(createDoctorData));
    }

    @GetMapping
    public Page<ListDataDoctor> list(@PageableDefault(size = 10, sort = "name") Pageable pagination) {
        return doctorRepository.findAll(pagination).map(ListDataDoctor::new);
    }
}

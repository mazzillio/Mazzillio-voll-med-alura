package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.doctor.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable @NotNull  Long id, @RequestBody @Valid UpdateDoctorData updateDoctorData) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.updateDoctor(updateDoctorData);
    }
}

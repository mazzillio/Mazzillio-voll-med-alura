package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.doctor.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDataDetails> create(@RequestBody @Valid CreateDoctorData createDoctorData, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = new Doctor(createDoctorData);
        doctorRepository.save(doctor);
        var uri = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDataDetails(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataDoctor>> list(@PageableDefault(size = 10, sort = "name") Pageable pagination) {
        var page = doctorRepository.findAllByActiveTrue(pagination).map(ListDataDoctor::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDataDetails> getOne(@PathVariable Long id){
        return ResponseEntity.ok(new DoctorDataDetails(doctorRepository.getReferenceById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDataDetails> update(@PathVariable @NotNull Long id, @RequestBody @Valid UpdateDoctorData updateDoctorData) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.updateDoctor(updateDoctorData);
        return ResponseEntity.ok(new DoctorDataDetails(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.exclude();
        return ResponseEntity.noContent().build();
    }
}

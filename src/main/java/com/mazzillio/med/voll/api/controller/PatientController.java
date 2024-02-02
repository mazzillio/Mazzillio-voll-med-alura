package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.domain.address.PatientRepository;
import com.mazzillio.med.voll.api.domain.patient.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDataDetails> createPatient(@RequestBody @Valid CreatePatientData createPatientData, UriComponentsBuilder uriComponentsBuilder) {
        Patient patient = new Patient(createPatientData);
        patientRepository.save(patient);
        URI uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDataDetails(patient));
    }

    @GetMapping
    public ResponseEntity<PageCustomListPatient> list(@PageableDefault(sort = "name") Pageable pagination, UriComponentsBuilder uriComponentsBuilder) {
        PageCustomListPatient listPatient = new PageCustomListPatient(patientRepository.findAllByActiveTrue(pagination), uriComponentsBuilder);
        return ResponseEntity.ok(listPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDataDetails> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(new PatientDataDetails(patientRepository.getReferenceById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientDataDetails> update(@PathVariable @NotNull Long id, @RequestBody UpdatePatientData updatePatientData) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.updatePatient(updatePatientData);
        return ResponseEntity.ok(new PatientDataDetails(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable @NotNull Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.exclude();
        return ResponseEntity.noContent().build();
    }
}

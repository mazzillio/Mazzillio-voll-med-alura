package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.doctor.CreateDoctorData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @PostMapping
    public void create(@RequestBody CreateDoctorData createDoctorData){
        System.out.println(createDoctorData);
    }
}

package com.mazzillio.med.voll.api.controller;

import com.mazzillio.med.voll.api.domain.appointment.AppointmentDataDetails;
import com.mazzillio.med.voll.api.domain.appointment.AppointmentSchedule;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerCancelData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointment;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDataDetails> schedule(@RequestBody @Valid SchedulerAppointmentData data) {
        AppointmentDataDetails dto = appointment.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody @Valid SchedulerCancelData data) {
        appointment.cancel(data);
        return ResponseEntity.noContent().build();
    }
}

package com.mazzillio.med.voll.api.domain.appointment;

import com.mazzillio.med.voll.api.domain.ExceptionValidation;
import com.mazzillio.med.voll.api.domain.address.PatientRepository;
import com.mazzillio.med.voll.api.domain.doctor.Doctor;
import com.mazzillio.med.voll.api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void schedule(SchedulerAppointmentData data) {


        if (!patientRepository.existsById(data.idPatient())) {
            throw new ExceptionValidation("Patient id does not exists!");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ExceptionValidation("Doctor id does not exists!");
        }
        var patient = patientRepository.findById(data.idPatient()).get();
        var doctor = chooseDoctor(data);
        var appointment = new Appointment(null, doctor, patient, data.data(),null);

        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(SchedulerAppointmentData data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null) {
            throw new ValidationException("Speciality is required without a doctor");
        }
        return doctorRepository.randomDoctorFreeByDate(data.specialty(),data.data());
    }

    public void cancel(SchedulerCancelData data) {
        if(!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Id appointment does not exists!");
        }
        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }
}

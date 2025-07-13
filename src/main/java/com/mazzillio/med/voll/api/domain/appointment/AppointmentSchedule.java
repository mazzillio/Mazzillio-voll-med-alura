package com.mazzillio.med.voll.api.domain.appointment;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.validations.ValidatorCancelAppointment;
import com.mazzillio.med.voll.api.domain.patient.Patient;
import com.mazzillio.med.voll.api.domain.patient.PatientRepository;
import com.mazzillio.med.voll.api.domain.appointment.validations.AppointmentValidator;
import com.mazzillio.med.voll.api.domain.doctor.Doctor;
import com.mazzillio.med.voll.api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentValidator> validators;
    @Autowired
    private List<ValidatorCancelAppointment> cancelValidators;

    public AppointmentDataDetails schedule(SchedulerAppointmentData data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ServiceExceptionValidation("Patient id does not exists!");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ServiceExceptionValidation("Doctor id does not exists!");
        }

        validators.forEach(v -> v.validate(data));

        Patient patient = patientRepository.findById(data.idPatient()).get();
        Doctor doctor = chooseDoctor(data);
        if (doctor == null) {
            throw new ServiceExceptionValidation("There is no doctor available on that date");
        }
        Appointment appointment = new Appointment(null, doctor, patient, data.data(), null);

        appointmentRepository.save(appointment);
        return new AppointmentDataDetails(appointment);
    }

    private Doctor chooseDoctor(SchedulerAppointmentData data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null) {
            throw new ServiceExceptionValidation("Speciality is required without a doctor");
        }
        return doctorRepository.randomDoctorFreeByDate(data.specialty(), data.data());
    }

    public void cancel(SchedulerCancelData data) {
        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Id appointment does not exists!");
        }
        cancelValidators.forEach(v -> v.validate(data));
        Appointment appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }
}

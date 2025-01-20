package com.mazzillio.med.voll.api.domain.doctor;

import com.mazzillio.med.voll.api.domain.address.AddressData;
import com.mazzillio.med.voll.api.domain.appointment.Appointment;
import com.mazzillio.med.voll.api.domain.patient.CreatePatientData;
import com.mazzillio.med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Appointment(null,doctor, patient, data,null));
    }

    private Doctor registerDoctor(String nome, String email, String crm, Specialty speciality) {
        var medico = new Doctor(doctorData(nome, email, crm, speciality));
        em.persist(medico);
        return medico;
    }

    private Patient registerPatient(String name, String email) {
        var patient = new Patient(patientData(name, email));
        em.persist(patient);
        return patient;
    }

    private  CreateDoctorData doctorData(String nome, String email, String crm, Specialty specialty) {
        return new CreateDoctorData(
                nome,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private CreatePatientData patientData(String name, String email) {
        return new CreatePatientData(
                name,
                email,
                "61999999999",
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

    @Test
    @DisplayName("Should be return null when doctor does not available in the date")
    void testRandomDoctorFreeByDate() {
        Patient patient = registerPatient("Paciente","paciente@email.com");
        Doctor doctor = registerDoctor("Medico","medico@voll.med","123456",Specialty.CARDIOLOGIA);
        LocalDateTime nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        registerAppointment(doctor,patient,nextMondayAt10);
        var freeDoctor = doctorRepository.randomDoctorFreeByDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(freeDoctor).isNull();
    }
    @Test
    @DisplayName("should be return doctor when doctor is free")
    void chooseFreeRandomDoctorInTheDate() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = registerDoctor("Medico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);

        var freeDoctor = doctorRepository.randomDoctorFreeByDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(freeDoctor).isEqualTo(medico);
    }
}
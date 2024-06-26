package com.mazzillio.med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);


    @Query("""
                select d from Doctor d
                where
                d.active = true
                and d.specialty = :specialty
                and d.id not in(
                   select a.doctor.id from Appointment a
                   where
                   a.data = :data
                   and a.cancelReason is null
                )
                
                order by rand()
                limit 1
                """)
    Doctor randomDoctorFreeByDate(Specialty specialty, LocalDateTime data);

    @Query("select d.active from Doctor d where d.id = :id")
    boolean findActiveById(Long id);
}

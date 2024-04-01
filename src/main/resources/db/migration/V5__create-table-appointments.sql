create table if not exists appointments (
    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    data datetime not null,
    cancel_reason varchar(100),

    primary key(id),
    constraint fk_appointments_doctor_id foreign key(doctor_id) references doctors(id),
    constraint fk_appointments_patient_id foreign key(patient_id) references patients(id)
)
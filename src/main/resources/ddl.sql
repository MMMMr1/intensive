
CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.patients
(
    uuid uuid NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    address VARCHAR(80) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    medical_card_number VARCHAR(30) NOT NULL,
    dt_created timestamp without time zone NOT NULL,
    dt_updated timestamp without time zone NOT NULL,
    CONSTRAINT patients_pkey PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS app.employees
(
    id bigint NOT NULL,
    dt_created timestamp without time zone,
    dt_updated timestamp without time zone,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.doctors
(
    department VARCHAR(60) NOT NULL,
    position VARCHAR(60) NOT NULL,
    id bigint NOT NULL,
    CONSTRAINT doctors_pkey PRIMARY KEY (id),
    CONSTRAINT id FOREIGN KEY (id)
        REFERENCES app.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS app.nurses
(
    blockcode VARCHAR(20) NOT NULL,
    blockfloor VARCHAR(20) NOT NULL,
    position VARCHAR(20) NOT NULL,
    id bigint NOT NULL,
    CONSTRAINT nurses_pkey PRIMARY KEY (id),
    CONSTRAINT id FOREIGN KEY (id)
        REFERENCES app.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS app.medical_histories
(
    uuid uuid NOT NULL,
    diagnosis VARCHAR(100) NOT NULL,
    treatment VARCHAR(500) NOT NULL,
    dt_created timestamp without time zone NOT NULL,
    dt_updated timestamp without time zone NOT NULL,
    doctor_id bigint,
    patient_uuid uuid,
    CONSTRAINT medical_history_pkey PRIMARY KEY (uuid),
    CONSTRAINT id_doctor FOREIGN KEY (doctor_id)
        REFERENCES app.doctors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT patient_uuid FOREIGN KEY (patient_uuid)
        REFERENCES app.patients (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE INDEX doctors_workhours_index
ON app.doctors (workHours);

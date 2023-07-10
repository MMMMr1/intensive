
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
)


CREATE TABLE IF NOT EXISTS app.doctors
(
    uuid uuid NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    position VARCHAR(60) NOT NULL,
    department VARCHAR(60) NOT NULL,
    dt_created timestamp without time zone,
    dt_updated timestamp without time zone,
    CONSTRAINT doctors_pkey PRIMARY KEY (uuid)
)

CREATE TABLE IF NOT EXISTS app.medical_histories
(
    uuid uuid NOT NULL,
    diagnosis VARCHAR(100) NOT NULL,
    treatment VARCHAR(500) NOT NULL,
    dt_created timestamp without time zone NOT NULL,
    dt_updated timestamp without time zone NOT NULL,
    id_patient uuid,
    id_doctor uuid,
    CONSTRAINT medical_history_pkey PRIMARY KEY (uuid),
    CONSTRAINT id_doctor FOREIGN KEY (id_doctor)
        REFERENCES app.doctors (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_patient FOREIGN KEY (id_patient)
        REFERENCES app.patients (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

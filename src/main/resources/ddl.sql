
CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.patients
(
    uuid uuid NOT NULL,
    lastname text COLLATE pg_catalog."default" NOT NULL,
    firstname text COLLATE pg_catalog."default" NOT NULL,
    surname text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default" NOT NULL,
    phone text COLLATE pg_catalog."default",
    medical_card_number text COLLATE pg_catalog."default",
    dt_created timestamp without time zone NOT NULL,
    dt_updated timestamp without time zone,
    CONSTRAINT patients_pkey PRIMARY KEY (uuid)
)


CREATE TABLE IF NOT EXISTS app.doctors
(
    uuid uuid NOT NULL,
    lastname text COLLATE pg_catalog."default" NOT NULL,
    firstname text COLLATE pg_catalog."default" NOT NULL,
    surname text COLLATE pg_catalog."default" NOT NULL,
    "position" text COLLATE pg_catalog."default" NOT NULL,
    department text COLLATE pg_catalog."default" NOT NULL,
    dt_created timestamp without time zone,
    dt_updated timestamp without time zone,
    CONSTRAINT doctors_pkey PRIMARY KEY (uuid)
)

CREATE TABLE IF NOT EXISTS app.medical_histories
(
    uuid uuid NOT NULL,
    diagnosis text COLLATE pg_catalog."default" NOT NULL,
    treatment text COLLATE pg_catalog."default" NOT NULL,
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

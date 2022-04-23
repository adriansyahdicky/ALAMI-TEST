CREATE DATABASE "alami-test";

CREATE TABLE IF NOT EXISTS public.contact
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    birth_place character varying(255) COLLATE pg_catalog."default",
    birth_date date,
    address character varying(255) COLLATE pg_catalog."default",
    enabled boolean,
    created_date timestamp with time zone,
    modified_date timestamp with time zone,
    CONSTRAINT contact_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.loan_application
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    contact_id character varying(36) COLLATE pg_catalog."default",
    amount numeric,
    loan_date timestamp with time zone,
    status character varying(100) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    modified_date timestamp with time zone,
    CONSTRAINT loan_application_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.saving_application
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    contact_id character varying(36) COLLATE pg_catalog."default",
    amount numeric,
    saving_date timestamp with time zone,
    status character varying(100) COLLATE pg_catalog."default",
    created_date time with time zone,
    modified_date time with time zone,
    CONSTRAINT saving_application_pkey PRIMARY KEY (id)
);

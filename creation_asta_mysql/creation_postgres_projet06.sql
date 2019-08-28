
--  -------------------- POSTGRESQL - PROJET06 ---------------------------------
--  Pour une maquette, pourqoui utiliser MySql qui est simple et rapide à construire
--  alors que PostGresql permet de perdre  des heures à faire ce qui suit
-- -----------------------------------------------------------------------------
-- Database: projet06

DROP DATABASE IF EXISTS projet06;

CREATE DATABASE projet06
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

GRANT ALL ON DATABASE projet06 TO postgres;

GRANT TEMPORARY, CONNECT ON DATABASE projet06 TO PUBLIC;

--  -------------------------------------------------------------------------
-- et les Drop dans l'ordre
-- ---------------------------------------------------------------------------

DROP TABLE IF EXISTS public.longueur;
DROP TABLE IF EXISTS public.voie;
DROP TABLE  IF EXISTS public.secteur;
DROP TABLE IF EXISTS public.commentaire;
DROP TABLE IF EXISTS public.topo;
DROP TABLE  IF EXISTS public.spot;
DROP TABLE IF EXISTS public.grimpeur;

DROP SEQUENCE IF EXISTS  public.commentaire_idcommentaire_seq;
DROP SEQUENCE IF EXISTS public.grimpeur_iduser_seq;
DROP SEQUENCE IF EXISTS public.longueur_idlongueur_seq;
DROP SEQUENCE IF EXISTS public.secteur_idsecteur_seq;
DROP SEQUENCE IF EXISTS public.voie_idvoie_seq;
DROP SEQUENCE IF EXISTS public.topo_idtopo_seq;
DROP SEQUENCE IF EXISTS public.spot_idspot_seq;

-- ---------------------   SEQUENCE --------------------------------------------
-- pour commencer !!
-- -------------------------------------------------------------------------

-- SEQUENCE: public.commentaire_idcommentaire_seq

CREATE SEQUENCE public.commentaire_idcommentaire_seq
    INCREMENT 1
    START 3
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.commentaire_idcommentaire_seq
    OWNER TO postgres;

-- SEQUENCE: public.grimpeur_iduser_seq

CREATE SEQUENCE public.grimpeur_iduser_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.grimpeur_iduser_seq
    OWNER TO postgres;

-- SEQUENCE: public.longueur_idlongueur_seq

CREATE SEQUENCE public.longueur_idlongueur_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.longueur_idlongueur_seq
    OWNER TO postgres;


-- SEQUENCE: public.secteur_idsecteur_seq

CREATE SEQUENCE public.secteur_idsecteur_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.secteur_idsecteur_seq
    OWNER TO postgres;


-- SEQUENCE: public.spot_idspot_seq

CREATE SEQUENCE public.spot_idspot_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.spot_idspot_seq
    OWNER TO postgres;


-- SEQUENCE: public.topo_idtopo_seq

CREATE SEQUENCE public.topo_idtopo_seq
    INCREMENT 1
    START 6
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.topo_idtopo_seq
    OWNER TO postgres;


-- SEQUENCE: public.voie_idvoie_seq

CREATE SEQUENCE public.voie_idvoie_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.voie_idvoie_seq
    OWNER TO postgres;

-- Table NBIEN RESPECTER l'ORDRE ------------------------

-- Table: public.grimpeur

CREATE TABLE public.grimpeur
(
    iduser integer NOT NULL DEFAULT nextval('grimpeur_iduser_seq'::regclass),
    email character varying(256) COLLATE pg_catalog."default",
    mdp character varying(16) COLLATE pg_catalog."default",
    nom character varying(256) COLLATE pg_catalog."default",
    profil character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT grimpeur_pkey PRIMARY KEY (iduser)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.grimpeur
    OWNER to postgres;
-- Table: public.spot



CREATE TABLE public.spot
(
    idspot integer NOT NULL DEFAULT nextval('spot_idspot_seq'::regclass),
    classification character varying(45) COLLATE pg_catalog."default",
    localisation character varying(45) COLLATE pg_catalog."default",
    nom character varying(45) COLLATE pg_catalog."default",
    user_iduser integer NOT NULL,
    CONSTRAINT spot_pkey PRIMARY KEY (idspot),
    CONSTRAINT fk6658yrb78eur49a2vs4elpodu FOREIGN KEY (user_iduser)
        REFERENCES public.grimpeur (iduser) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.spot
    OWNER to postgres;

-- Table: public.topo



CREATE TABLE public.topo
(
    idtopo integer NOT NULL DEFAULT nextval('topo_idtopo_seq'::regclass),
    date_de_parution date,
    est_publie integer,
    etat_reservation character varying(255) COLLATE pg_catalog."default",
    lieu character varying(45) COLLATE pg_catalog."default",
    nom character varying(256) COLLATE pg_catalog."default",
    resume character varying(256) COLLATE pg_catalog."default",
    user_iduser integer NOT NULL,
    CONSTRAINT topo_pkey PRIMARY KEY (idtopo),
    CONSTRAINT fkdk3dxm8445oyn4ernjibrvgd6 FOREIGN KEY (user_iduser)
        REFERENCES public.grimpeur (iduser) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.topo
    OWNER to postgres;


-- Table: public.commentaire



CREATE TABLE public.commentaire
(
    idcommentaire integer NOT NULL DEFAULT nextval('commentaire_idcommentaire_seq'::regclass),
    est_visible integer,
    texte character varying(256) COLLATE pg_catalog."default",
    spot_idspot integer NOT NULL,
    CONSTRAINT commentaire_pkey PRIMARY KEY (idcommentaire),
    CONSTRAINT fk9opuacfoym9aae4x5no6clpva FOREIGN KEY (spot_idspot)
        REFERENCES public.spot (idspot) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.commentaire
    OWNER to postgres;

-- Table: public.secteur



CREATE TABLE public.secteur
(
    idsecteur integer NOT NULL DEFAULT nextval('secteur_idsecteur_seq'::regclass),
    nom character varying(45) COLLATE pg_catalog."default",
    spot_idspot integer NOT NULL,
    CONSTRAINT secteur_pkey PRIMARY KEY (idsecteur),
    CONSTRAINT fkmlcj4gkyht0f5urxj9aratsjk FOREIGN KEY (spot_idspot)
        REFERENCES public.spot (idspot) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.secteur
    OWNER to postgres;


-- Table: public.voie



CREATE TABLE public.voie
(
    idvoie integer NOT NULL DEFAULT nextval('voie_idvoie_seq'::regclass),
    nom character varying(45) COLLATE pg_catalog."default",
    secteur_idsecteur integer NOT NULL,
    CONSTRAINT voie_pkey PRIMARY KEY (idvoie),
    CONSTRAINT fkhiagkwhsw8p3yqoqk2y1sqhem FOREIGN KEY (secteur_idsecteur)
        REFERENCES public.secteur (idsecteur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.voie
    OWNER to postgres;


-- Table: public.longueur


CREATE TABLE public.longueur
(
    idlongueur integer NOT NULL DEFAULT nextval('longueur_idlongueur_seq'::regclass),
    cotation character varying(45) COLLATE pg_catalog."default",
    nom character varying(45) COLLATE pg_catalog."default",
    nombre_de_spits integer,
    voie_idvoie integer NOT NULL,
    CONSTRAINT longueur_pkey PRIMARY KEY (idlongueur),
    CONSTRAINT fkp0jjx9qv97soh8eofxd8lwgr4 FOREIGN KEY (voie_idvoie)
        REFERENCES public.voie (idvoie) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.longueur
    OWNER to postgres;



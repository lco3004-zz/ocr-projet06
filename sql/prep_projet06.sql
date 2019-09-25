-- ***************************************
-- Creation du  Role: rl_projet06
-- **************************************

DROP ROLE IF EXISTS rl_projet06;

CREATE ROLE rl_projet06 WITH
  LOGIN
  SUPERUSER
  CREATEDB
  CREATEROLE
  INHERIT
  NOREPLICATION
  CONNECTION LIMIT -1
  PASSWORD 'xxxxxx';


-- ***************************************
-- Creation du Tablespace: ts_projet06 
-- **************************************
DROP TABLESPACE IF EXISTS ts_projet06;

CREATE TABLESPACE ts_projet06
  OWNER rl_projet06
  LOCATION 'c:\bd_data';

ALTER TABLESPACE ts_projet06
  OWNER TO rl_projet06;


-- ***************************************
-- Creation du Database: db_projet06 
-- **************************************

DROP DATABASE IF EXISTS db_projet06;

CREATE DATABASE db_projet06
    WITH 
    OWNER = rl_projet06
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = ts_projet06
    CONNECTION LIMIT = -1;

GRANT TEMPORARY, CONNECT ON DATABASE db_projet06 TO PUBLIC;

GRANT ALL ON DATABASE db_projet06 TO rl_projet06;

ALTER DEFAULT PRIVILEGES
GRANT ALL ON TABLES TO rl_projet06;




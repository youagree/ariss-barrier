-- create database

CREATE DATABASE unit_techno;

-- \c unit_techno

CREATE SCHEMA ariss_barrier;

CREATE USER ariss_barrier_admin WITH password 'squd';

ALTER USER ariss_barrier_admin WITH SUPERUSER;

GRANT USAGE ON SCHEMA ariss_barrier TO ariss_barrier_admin;

ALTER SCHEMA ariss_barrier OWNER TO ariss_barrier_admin;
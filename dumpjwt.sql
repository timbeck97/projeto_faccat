--
-- PostgreSQL database dump
--

-- Dumped from database version 14.6 (Ubuntu 14.6-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.6 (Ubuntu 14.6-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO timbeck;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id bigint NOT NULL,
    address_number character varying(5) NOT NULL,
    city character varying(100) NOT NULL,
    complement character varying(255),
    state character varying(30) NOT NULL
);


ALTER TABLE public.address OWNER TO timbeck;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO timbeck;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO timbeck;

--
-- Name: user_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_table (
    id bigint NOT NULL,
    name character varying(255),
    password character varying(255),
    username character varying(255),
    address_id bigint
);


ALTER TABLE public.user_table OWNER TO timbeck;

--
-- Name: user_table_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_table_roles (
    user_id bigint NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE public.user_table_roles OWNER TO timbeck;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, address_number, city, complement, state) FROM stdin;
1	99	Nova Hartz	Rua do restaurante becker	RS
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
3	ADMIN
4	USER
\.


--
-- Data for Name: user_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_table (id, name, password, username, address_id) FROM stdin;
2	Tim Morgenstern Beck	$2a$10$BxJ9e/Hm7GFhTv5MgIuHNeyenrljcKB38T2RhmZCEfHyThIImNdXK	timbeck97	1
\.


--
-- Data for Name: user_table_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_table_roles (user_id, roles_id) FROM stdin;
2	3
2	4
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 4, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user_table uk_en3wad7p8qfu8pcmh62gvef6v; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT uk_en3wad7p8qfu8pcmh62gvef6v UNIQUE (username);


--
-- Name: user_table user_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (id);


--
-- Name: user_table_roles user_table_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table_roles
    ADD CONSTRAINT user_table_roles_pkey PRIMARY KEY (user_id, roles_id);


--
-- Name: user_table_roles fkhn8b43sudbfyi60j8m0he2num; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table_roles
    ADD CONSTRAINT fkhn8b43sudbfyi60j8m0he2num FOREIGN KEY (user_id) REFERENCES public.user_table(id);


--
-- Name: user_table_roles fkkr1sfrdm6p2maklrdfg8vma1s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table_roles
    ADD CONSTRAINT fkkr1sfrdm6p2maklrdfg8vma1s FOREIGN KEY (roles_id) REFERENCES public.role(id);


--
-- Name: user_table fkt434p23ybliyotcdf0owf4l45; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT fkt434p23ybliyotcdf0owf4l45 FOREIGN KEY (address_id) REFERENCES public.address(id);


--
-- PostgreSQL database dump complete
--


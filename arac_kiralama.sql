--
-- PostgreSQL database dump
--

-- Dumped from database version 17.3
-- Dumped by pg_dump version 17.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: rentals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rentals (
    id integer NOT NULL,
    user_id integer,
    vehicle_id integer,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    rental_type character varying(20),
    is_returned boolean DEFAULT false,
    total_price numeric(15,2)
);


ALTER TABLE public.rentals OWNER TO postgres;

--
-- Name: rentals_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rentals_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rentals_id_seq OWNER TO postgres;

--
-- Name: rentals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rentals_id_seq OWNED BY public.rentals.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(64) NOT NULL,
    age integer NOT NULL,
    user_type character varying(50)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: vehicles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicles (
    id integer NOT NULL,
    brand character varying(50),
    model character varying(50),
    category character varying(20),
    price numeric,
    rental_rate numeric,
    is_available boolean DEFAULT true
);


ALTER TABLE public.vehicles OWNER TO postgres;

--
-- Name: vehicles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicles_id_seq OWNER TO postgres;

--
-- Name: vehicles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicles_id_seq OWNED BY public.vehicles.id;


--
-- Name: rentals id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rentals ALTER COLUMN id SET DEFAULT nextval('public.rentals_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: vehicles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles ALTER COLUMN id SET DEFAULT nextval('public.vehicles_id_seq'::regclass);


--
-- Data for Name: rentals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rentals (id, user_id, vehicle_id, start_date, end_date, rental_type, is_returned, total_price) FROM stdin;
1	9	9	2025-05-17 04:24:27.038028	2025-07-17 04:24:27.038028	MONTHLY	f	6480000.00
3	8	10	2025-05-19 16:35:38.159089	2025-05-29 16:35:38.159089	DAILY	t	408000.00
2	8	12	2025-05-19 15:11:44.823051	2025-05-19 16:11:44.823051	HOURLY	t	6000.00
4	8	21	2025-05-19 16:35:49.929424	2025-05-26 16:35:49.929424	WEEKLY	t	588000.00
5	8	1	2025-05-19 17:01:57.279226	2025-05-24 17:01:57.279226	DAILY	t	24000.00
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, age, user_type) FROM stdin;
8	mehmetkerem8@gmail.com	0f096619d854c254663eabc92a1b1ac806d90d7f1c61af6b6590eb495206c76e	34	INDIVIDUAL
7	mehmetkerem	0f096619d854c254663eabc92a1b1ac806d90d7f1c61af6b6590eb495206c76e	34	ADMIN
9	mehmetkerem9@gmail.com	0f096619d854c254663eabc92a1b1ac806d90d7f1c61af6b6590eb495206c76e	39	CORPORATE
\.


--
-- Data for Name: vehicles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehicles (id, brand, model, category, price, rental_rate, is_available) FROM stdin;
3	Ford	Focus	HELICOPTER	5000000	1234	t
4	Renault	Megan	CAR	750000	2500	t
5	Renault	Talisman	CAR	1200000	5000	t
6	BMW	M4	CAR	2500000	2500	t
7	BMW	M4	CAR	2500000	2500	t
8	MERCEDES	C180	CAR	3500000	3400	t
11	FORD	FOCUS	CAR	1200000	1200	t
13	TUSAS	HEL	HELICOPTER	85000000	34000	t
14	MERCEDES	AIRBUS	HELICOPTER	23000000	45000	t
15	Agusta 	X93F12	HELICOPTER	16000000	17000	t
17	AIRB	M234	HELICOPTER	87000000	60000	t
18	KAWASAKI	Z-500	MOTORCYCLE	458000	450	t
19	HONDA	DIO	MOTORCYCLE	35000	150	t
20	KUBA 	AZ-50	MOTORCYCLE	16000000	170	t
22	ARORA	SHARK	MOTORCYCLE	93000	150	t
9	BMW	M5	CAR	4500000	4500	t
12	FORD	MUSTANG	CAR	6000000	6000	t
10	AUDI	A6	CAR	1750000	1700	t
21	BMW 	S-1000	MOTORCYCLE	1250000	3500	t
1	BMW	M3	CAR	2000000	200	t
\.


--
-- Name: rentals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rentals_id_seq', 5, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 9, true);


--
-- Name: vehicles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicles_id_seq', 22, true);


--
-- Name: rentals rentals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rentals
    ADD CONSTRAINT rentals_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: vehicles vehicles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (id);


--
-- Name: rentals rentals_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rentals
    ADD CONSTRAINT rentals_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: rentals rentals_vehicle_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rentals
    ADD CONSTRAINT rentals_vehicle_id_fkey FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id);


--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.3 (Homebrew)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bookings; Type: TABLE; Schema: public; Owner: amrita.deviayu
--

CREATE TABLE public.bookings (
    id integer NOT NULL,
    user_id integer,
    user_email character varying(100),
    tour_name character varying(100) NOT NULL,
    full_name character varying(100) NOT NULL,
    group_size integer NOT NULL,
    phone character varying(20) NOT NULL,
    book_at timestamp with time zone NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.bookings OWNER TO "amrita.deviayu";

--
-- Name: bookings_id_seq; Type: SEQUENCE; Schema: public; Owner: amrita.deviayu
--

CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bookings_id_seq OWNER TO "amrita.deviayu";

--
-- Name: bookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: amrita.deviayu
--

ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: amrita.deviayu
--

CREATE TABLE public.reviews (
    id integer NOT NULL,
    product_id integer,
    username character varying(100) NOT NULL,
    review_text text NOT NULL,
    rating integer DEFAULT 0 NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT reviews_rating_check CHECK (((rating >= 0) AND (rating <= 5)))
);


ALTER TABLE public.reviews OWNER TO "amrita.deviayu";

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: amrita.deviayu
--

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_id_seq OWNER TO "amrita.deviayu";

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: amrita.deviayu
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- Name: tours; Type: TABLE; Schema: public; Owner: amrita.deviayu
--

CREATE TABLE public.tours (
    id integer NOT NULL,
    title character varying(100) NOT NULL,
    city character varying(100) NOT NULL,
    address character varying(100) NOT NULL,
    distance numeric NOT NULL,
    photo character varying(100) NOT NULL,
    description text NOT NULL,
    price numeric NOT NULL,
    max_group_size integer NOT NULL,
    featured boolean DEFAULT false,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.tours OWNER TO "amrita.deviayu";

--
-- Name: tours_id_seq; Type: SEQUENCE; Schema: public; Owner: amrita.deviayu
--

CREATE SEQUENCE public.tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tours_id_seq OWNER TO "amrita.deviayu";

--
-- Name: tours_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: amrita.deviayu
--

ALTER SEQUENCE public.tours_id_seq OWNED BY public.tours.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: amrita.deviayu
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    photo character varying(100),
    role character varying(100) DEFAULT 'user'::character varying,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO "amrita.deviayu";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: amrita.deviayu
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO "amrita.deviayu";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: amrita.deviayu
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: bookings id; Type: DEFAULT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- Name: tours id; Type: DEFAULT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.tours ALTER COLUMN id SET DEFAULT nextval('public.tours_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: amrita.deviayu
--

COPY public.bookings (id, user_id, user_email, tour_name, full_name, group_size, phone, book_at, created_at, updated_at) FROM stdin;
1	2	bisma123@gmail.com	Museum Wayang	Bisma Alif Alghifari	2	+6287735508811	2023-06-15 00:00:00+00	2023-06-10 15:29:37.421988+00	2023-06-10 15:29:37.421988+00
2	2	bisma123@gmail.com	Museum Nasional Indonesia	Bisma Alif Alghifari	2	+6287735508811	2023-06-23 00:00:00+00	2023-06-10 15:31:14.33844+00	2023-06-10 15:31:14.33844+00
3	9	tania123@gmail.com	Candi Borobudur	Tania Salsabila	2	087735508812	2023-06-25 00:00:00+00	2023-06-10 15:42:26.367976+00	2023-06-10 15:42:26.367976+00
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: amrita.deviayu
--

COPY public.reviews (id, product_id, username, review_text, rating, created_at, updated_at) FROM stdin;
1	1	bismalif	This place is awesome!	4	2023-06-04 16:45:30.080868+00	2023-06-04 16:45:30.080868+00
2	2	TaniaCantik	This place has amazing Wayangs!	5	2023-06-10 02:46:22.849144+00	2023-06-10 02:46:22.849144+00
3	4	TaniaCantik	This place tells history way better than my teacher.	5	2023-06-10 06:43:58.591+00	2023-06-10 06:43:58.591+00
4	12	Lauren Christy	Love how this place serves us history!	4	2023-06-10 11:02:37.315242+00	2023-06-10 11:02:37.315242+00
5	6	TaniaCantik	MONAS is really an amazing icon of Jakarta! You should come in here to relax and see the cities!	5	2023-06-10 12:53:13.342223+00	2023-06-10 12:53:13.342223+00
6	1	TaniaCantik	This place reminds me of the great history in the past!	5	2023-06-10 16:55:33.015253+00	2023-06-10 16:55:33.015253+00
7	11	TaniaCantik	This place has amazing foods!	0	2023-06-10 17:03:08.125082+00	2023-06-10 17:03:08.125082+00
\.


--
-- Data for Name: tours; Type: TABLE DATA; Schema: public; Owner: amrita.deviayu
--

COPY public.tours (id, title, city, address, distance, photo, description, price, max_group_size, featured, created_at, updated_at) FROM stdin;
1	Candi Borobudur	Magelang	Jalan Badrawati, Borobudur	3800	borobudur.jpg	Candi Borobudur is a majestic Buddhist temple located in Central Java, Indonesia. Built in the 9th century, it is one of the world's most impressive religious and architectural masterpieces. Rising over three levels and adorned with intricate stone carvings, the temple showcases the fusion of Indonesian and Indian artistic traditions. Its design symbolizes the Buddhist concept of reaching enlightenment, with each level representing a different stage of spiritual realization. Candi Borobudur attracts visitors from all over the world who come to admire its beauty, immerse themselves in its spiritual ambiance, and explore its rich cultural heritage.	15	8	t	2023-06-04 08:29:24.711809+00	2023-06-04 08:29:24.711809+00
2	Tirta Gangga	Bali	Jalan Raya Abang, Ababi	5400	tirta_gangga.jpg	Tirta Gangga, is a mesmerizing water palace known for its serene beauty and cultural significance. The name translates to 'Water of the Ganges,' symbolizing its sacred essence. Built in the early 20th century, Tirta Gangga showcases stunning water gardens, ornate sculptures, and serene pools filled with carp and lotus flowers. Visitors can wander through lush greenery, cross stone bridges, and immerse themselves in the tranquil ambiance. The palace offers a glimpse into Bali's rich heritage and serves as a peaceful retreat where one can relax, rejuvenate, and appreciate the timeless beauty of this enchanting place.	5	4	f	2023-06-04 08:29:39.066717+00	2023-06-04 08:29:39.066717+00
3	Museum Wayang	Jakarta	Jalan Pintu Besar Utara Nomor 27, Pinangsia, Kota Tua	3800	museum_wayang.jpg	Museum Wayang in Jakarta is a captivating cultural institution that showcases the rich heritage of wayang, the traditional Indonesian puppetry art form. With its extensive collection of intricately crafted puppets and artifacts, the museum offers a glimpse into the enchanting world of wayang. Visitors can explore the history, mythology, and craftsmanship behind this centuries-old tradition, while also enjoying performances and interactive displays. Museum Wayang serves as a cultural hub, preserving and promoting the art of wayang, and providing visitors with a deeper appreciation for Indonesia's vibrant cultural heritage.	5	4	t	2023-06-04 08:29:51.983907+00	2023-06-04 08:29:51.983907+00
4	Kota Tua	Jakarta	Jalan Pintu Besar Utara Nomer 27, Pinangsia, Tamansari	500	kota_tua.jpg	Kota Tua, located in Jakarta, Indonesia, is a historical district that offers a captivating glimpse into the city's colonial past. With its well-preserved Dutch colonial architecture, cobblestone streets, and charming squares, Kota Tua showcases the rich cultural heritage of Jakarta. Visitors can explore museums, such as the Jakarta History Museum and the Wayang Museum, which display artifacts and exhibits highlighting the city's history. The area also boasts vibrant street vendors, cafes, and art galleries, creating a vibrant atmosphere. Kota Tua is a captivating destination where history, culture, and modern life seamlessly blend, offering a unique experience for locals and tourists alike.	5	10	t	2023-06-04 08:30:03.260149+00	2023-06-04 08:30:03.260149+00
5	Istana Maimun	Medan	Jalan. Sultan Ma’moen Al Rasyid Nomor. 66, Medan Maimun	500	kota_tua.jpg	Istana Maimun is a historic landmark located in the city of Medan, Indonesia. Built in the 19th century, it serves as the royal palace of the Sultanate of Deli. The palace showcases a magnificent blend of Malay and European architectural styles, with its distinctive yellow exterior and intricate detailing. Inside, visitors can explore the opulent halls, beautifully adorned with traditional furniture, royal heirlooms, and lavish decorations. Istana Maimun offers a glimpse into the rich cultural heritage of Medan and serves as a testament to the region's royal history, attracting both locals and tourists alike.	5	10	f	2023-06-04 08:30:16.655315+00	2023-06-04 08:30:16.655315+00
6	Museum Nasional Indonesia	Jakarta	Jalan Medan Merdeka Barat No. 12	500	monas.jpg	Monas, short for Monumen Nasional, is an iconic landmark located in the heart of Jakarta, Indonesia. Standing tall at a height of 132 meters, Monas serves as a symbol of Indonesia's struggle for independence. The monument consists of a towering marble obelisk topped with a golden flame, representing the nation's spirit and freedom. Surrounding the monument is a vast park, offering a tranquil retreat amidst the bustling city. Monas is not only a popular tourist attraction but also a significant historical site, inviting visitors to learn about Indonesia's rich heritage and commemorate its journey to independence.	2	6	t	2023-06-04 15:47:47.892501+00	2023-06-04 15:47:47.892501+00
7	Kebun Raya Bogor	Bogor	Jalan Ir. H. Juanda, Paledang	800	kebun_raya.jpg	Kebun Raya Bogor, also known as the Bogor Botanical Gardens, is a magnificent botanical garden located in Bogor, Indonesia. With a rich history dating back to the 19th century, this vast garden spans over 87 hectares and is home to a diverse collection of plant species from around the world. Visitors can wander through lush pathways, enchanting gardens, and serene ponds while marveling at the beauty of nature. Kebun Raya Bogor offers a peaceful retreat from the bustling city, providing a perfect setting for relaxation, education, and exploration. It is a haven for botany enthusiasts, nature lovers, and those seeking a tranquil escape.	4	12	f	2023-06-04 15:49:27.190703+00	2023-06-04 15:49:27.190703+00
8	Klenteng Hok Tek Bio	Bogor	RT.03/RW.07, Babakan Pasar, Bogor Tengah	800	klenteng_hoktekbio.jpg	Klenteng Hok Tek Bio is a revered Chinese temple located in the heart of the city. With its rich history and cultural significance, it serves as a spiritual and cultural hub for the Chinese community and visitors alike. The temple exudes a serene ambiance, adorned with intricate architectural details, vibrant red lanterns, and mesmerizing statues. Inside, worshippers and visitors can find a tranquil sanctuary to offer prayers and seek blessings. Klenteng Hok Tek Bio also hosts various cultural events and celebrations, showcasing traditional performances and rituals that provide a glimpse into Chinese customs and traditions. It is a cherished symbol of unity, spirituality, and cultural heritage.	0	8	t	2023-06-04 15:50:57.60788+00	2023-06-04 15:50:57.60788+00
10	Gedung Papak	Bekasi	Jalan Ir H Juanda No.157, Margahayu	650	gedung_papak.jpg	Gedung Papak is a modern architectural masterpiece located in the heart of the city Bekasi. With its striking design and impressive stature, this iconic building stands as a symbol of elegance and sophistication. Boasting state-of-the-art facilities and versatile spaces, Gedung Papak offers a perfect venue for various events, from corporate conferences and exhibitions to social gatherings and weddings. Its spacious halls, stylish interiors, and advanced amenities create an exceptional environment that caters to the diverse needs of its clients. Whether for business or pleasure, Gedung Papak promises a memorable experience and sets the stage for unforgettable moments.	3	6	f	2023-06-04 15:54:28.368077+00	2023-06-04 15:54:28.368077+00
11	Klenteng Boen Tek Bio	Banten	Jalan Bhakti No. 14, Pasar Lama, Sukasari, Tangerang	1450	gedung_papak.jpg	Klenteng Boen Tek Bio is a historic Chinese temple located in the heart of the city. With its rich heritage and architectural beauty, it stands as a symbol of cultural diversity and religious harmony. The temple showcases intricate traditional Chinese craftsmanship, adorned with vibrant colors and ornate sculptures. It serves as a spiritual sanctuary for worshippers and visitors alike, offering a serene atmosphere for prayer and reflection. Klenteng Boen Tek Bio holds a significant place in the local community, preserving traditions and promoting cultural awareness. It is a must-visit destination for those seeking to explore the cultural tapestry of the region	1	8	f	2023-06-04 15:55:51.682756+00	2023-06-04 15:55:51.682756+00
12	Monumen Perjuangan Rakyat	Bekasi	RT.006/RW.006, Marga Jaya	750	monumen.jpg	Monumen Perjuangan Rakyat, or the Monument of People's Struggle, is a significant landmark that stands as a testament to the heroic struggle and sacrifice of the Indonesian people during their fight for independence. Located in Bekasi, the monument commemorates the nation's history and pays tribute to those who fought for freedom. With its striking architecture and symbolic elements, the Monumen Perjuangan Rakyat serves as a reminder of the bravery and determination of the Indonesian people, inspiring future generations to uphold the values of independence, unity, and national pride.	5	5	t	2023-06-04 15:58:02.245343+00	2023-06-04 15:58:02.245343+00
9	Museum Benteng Heritage	Banten	Jalan Cilame, Pasar Lama, Tangerang	1875	museum_benteng.jpg	Museum Benteng Heritage is a captivating historical museum located in the heart of Tangerang, Indonesia. Housed within a beautifully preserved Dutch colonial fortress, the museum offers visitors a fascinating journey through time. With its extensive collection of artifacts, photographs, and interactive displays, Museum Benteng Heritage provides a deep insight into the rich cultural heritage and historical significance of the region. From the exhibits showcasing the colonial era to the stories of local heroes and their struggle for independence, the museum offers a compelling narrative that educates and inspires visitors of all ages. Immerse yourself in the captivating history of Tangerang at Museum Benteng Heritage.	6	6	f	2023-06-04 15:52:29.501318+00	2023-06-11 07:27:51.777573+00
15	Pura Uluwatu	Bali	Desa Pecatu, Kecamatan Kuta Selatan	3765	pura_uluwatu	Pura Uluwatu is a majestic sea temple located on the cliffs of Bali, Indonesia. With its breathtaking panoramic views of the Indian Ocean, it offers a spiritual and awe-inspiring experience for visitors. The temple is known for its intricate architecture, traditional Balinese sculptures, and stunning sunset performances of the Kecak dance. As one of Bali's six key temples, Pura Uluwatu holds significant cultural and religious importance to the local community. It's a must-visit destination for those seeking tranquility, cultural immersion, and natural beauty, making it a true gem of Bali's cultural heritage.	20	6	\N	2023-06-11 08:49:26.333168+00	2023-06-11 08:49:26.333168+00
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: amrita.deviayu
--

COPY public.users (id, username, email, password, photo, role, created_at, updated_at) FROM stdin;
1	biru	biru123@gmail.com	biru123	biru.jpg	\N	2023-06-04 10:16:33.746745+00	2023-06-04 10:16:33.746745+00
2	bismalif	bisma123@gmail.com	$2a$10$CZxEZILvxnOqyvr0vPFYEOEAipBBGgUL3rRzDqsWj5As.SZNm7RUm	bisma.jpg	user	2023-06-04 16:26:13.358647+00	2023-06-04 16:26:13.358647+00
9	TaniaCantik	tania123@gmail.com	$2a$10$97t0vZGDCZyZCGHFaHGjPegYpFxR.f6wdi7NwOSB7ZVDRi4Zr.KbK	\N	user	2023-06-09 16:25:31.44362+00	2023-06-09 16:25:31.44362+00
10	sulthan24	sulthandarmawan24@gmail.com	$2a$10$BsuoHPgzZJMR682/oAvPLu2aqkpc9yqgKck38zJxez2Fsp/RJbiey	\N	user	2023-06-10 10:10:26.113014+00	2023-06-10 10:10:26.113014+00
11	akmalrbn	akmal@email.com	$2a$10$4cCjNTAmJX/AGpFtoEuI7.w97ILVPxQEnBKHqXVtnLhv4rlBCKLJu	\N	user	2023-06-10 10:19:11.281983+00	2023-06-10 10:19:11.281983+00
12	Lauren Christy	lauren123@gmail.com	$2a$10$KZkWI9br7pOQ5dV3q.FTJOZuI6DZF0jjv8p.D3Sn3X89vIhgCNiRm	\N	user	2023-06-10 11:01:52.178572+00	2023-06-10 11:01:52.178572+00
13	akmalll	mall@email.com	$2a$10$cuQ7CiFUlRTEZVtqJtPO4e.aNRyrL4ku2ogbui5WBhbAx9PhOlrJ.	\N	user	2023-06-10 11:04:09.296452+00	2023-06-10 11:04:09.296452+00
16	BiBintang	bintang123@gmail.com	$2a$10$isU/0BlcEFauXpR9NGJ6jumzq5RchQwgIunVR9v8w6gOSXsU9PJ02	\N	admin	2023-06-11 01:59:51.895576+00	2023-06-11 01:59:51.895576+00
\.


--
-- Name: bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: amrita.deviayu
--

SELECT pg_catalog.setval('public.bookings_id_seq', 3, true);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: amrita.deviayu
--

SELECT pg_catalog.setval('public.reviews_id_seq', 7, true);


--
-- Name: tours_id_seq; Type: SEQUENCE SET; Schema: public; Owner: amrita.deviayu
--

SELECT pg_catalog.setval('public.tours_id_seq', 15, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: amrita.deviayu
--

SELECT pg_catalog.setval('public.users_id_seq', 16, true);


--
-- Name: bookings bookings_pkey; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: tours tours_pkey; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);


--
-- Name: tours tours_title_key; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_title_key UNIQUE (title);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: bookings_user_id_idx; Type: INDEX; Schema: public; Owner: amrita.deviayu
--

CREATE INDEX bookings_user_id_idx ON public.bookings USING btree (user_id);


--
-- Name: bookings bookings_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reviews reviews_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: amrita.deviayu
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.tours(id);


--
-- PostgreSQL database dump complete
--


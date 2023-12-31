PGDMP                      {         	   vetSystem    16.1    16.1 3    @           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            A           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            B           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            C           1262    17956 	   vetSystem    DATABASE     m   CREATE DATABASE "vetSystem" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE "vetSystem";
                postgres    false            �            1259    19467    animal    TABLE     �  CREATE TABLE public.animal (
    animal_id bigint NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_colour character varying(255) NOT NULL,
    animal_birth_date date NOT NULL,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id bigint NOT NULL
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    19466    animal_animal_id_seq    SEQUENCE     }   CREATE SEQUENCE public.animal_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.animal_animal_id_seq;
       public          postgres    false    216            D           0    0    animal_animal_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.animal_animal_id_seq OWNED BY public.animal.animal_id;
          public          postgres    false    215            �            1259    19476    appointment    TABLE     �   CREATE TABLE public.appointment (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    appointment_animal_id bigint,
    appointment_doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    19475    appointment_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.appointment_appointment_id_seq;
       public          postgres    false    218            E           0    0    appointment_appointment_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.appointment_appointment_id_seq OWNED BY public.appointment.appointment_id;
          public          postgres    false    217            �            1259    19483    available_date    TABLE     �   CREATE TABLE public.available_date (
    available_date_id bigint NOT NULL,
    available_date date NOT NULL,
    available_date_doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    19482 $   available_date_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.available_date_available_date_id_seq;
       public          postgres    false    220            F           0    0 $   available_date_available_date_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.available_date_available_date_id_seq OWNED BY public.available_date.available_date_id;
          public          postgres    false    219            �            1259    19490    customer    TABLE     D  CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    customer_address character varying(255) NOT NULL,
    customer_city character varying(255) NOT NULL,
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(11) NOT NULL
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    19489    customer_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_customer_id_seq;
       public          postgres    false    222            G           0    0    customer_customer_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;
          public          postgres    false    221            �            1259    19499    doctor    TABLE     6  CREATE TABLE public.doctor (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255) NOT NULL,
    doctor_city character varying(255) NOT NULL,
    doctor_mail character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(11) NOT NULL
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    19498    doctor_doctor_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doctor_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doctor_doctor_id_seq;
       public          postgres    false    224            H           0    0    doctor_doctor_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doctor_doctor_id_seq OWNED BY public.doctor.doctor_id;
          public          postgres    false    223            �            1259    19508    vaccine    TABLE     .  CREATE TABLE public.vaccine (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255) NOT NULL,
    vaccine_name character varying(255) NOT NULL,
    vaccine_protection_finish_date date NOT NULL,
    vaccine_protection_start_date date NOT NULL,
    vaccine_animal_id bigint NOT NULL
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    19507    vaccine_vaccine_id_seq    SEQUENCE        CREATE SEQUENCE public.vaccine_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccine_vaccine_id_seq;
       public          postgres    false    226            I           0    0    vaccine_vaccine_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccine_vaccine_id_seq OWNED BY public.vaccine.vaccine_id;
          public          postgres    false    225            �           2604    19470    animal animal_id    DEFAULT     t   ALTER TABLE ONLY public.animal ALTER COLUMN animal_id SET DEFAULT nextval('public.animal_animal_id_seq'::regclass);
 ?   ALTER TABLE public.animal ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    19479    appointment appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointment ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointment_appointment_id_seq'::regclass);
 I   ALTER TABLE public.appointment ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    19486     available_date available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_date ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_date_available_date_id_seq'::regclass);
 O   ALTER TABLE public.available_date ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    19493    customer customer_id    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    19502    doctor doctor_id    DEFAULT     t   ALTER TABLE ONLY public.doctor ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctor_doctor_id_seq'::regclass);
 ?   ALTER TABLE public.doctor ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    19511    vaccine vaccine_id    DEFAULT     x   ALTER TABLE ONLY public.vaccine ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccine_vaccine_id_seq'::regclass);
 A   ALTER TABLE public.vaccine ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    226    225    226            3          0    19467    animal 
   TABLE DATA           �   COPY public.animal (animal_id, animal_breed, animal_colour, animal_birth_date, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    216   5?       5          0    19476    appointment 
   TABLE DATA           u   COPY public.appointment (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    218   @       7          0    19483    available_date 
   TABLE DATA           e   COPY public.available_date (available_date_id, available_date, available_date_doctor_id) FROM stdin;
    public          postgres    false    220   {@       9          0    19490    customer 
   TABLE DATA           ~   COPY public.customer (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    222   �@       ;          0    19499    doctor 
   TABLE DATA           p   COPY public.doctor (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    224   �A       =          0    19508    vaccine 
   TABLE DATA           �   COPY public.vaccine (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_finish_date, vaccine_protection_start_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    226   �B       J           0    0    animal_animal_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.animal_animal_id_seq', 6, true);
          public          postgres    false    215            K           0    0    appointment_appointment_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.appointment_appointment_id_seq', 5, true);
          public          postgres    false    217            L           0    0 $   available_date_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_date_available_date_id_seq', 11, true);
          public          postgres    false    219            M           0    0    customer_customer_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.customer_customer_id_seq', 6, true);
          public          postgres    false    221            N           0    0    doctor_doctor_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.doctor_doctor_id_seq', 5, true);
          public          postgres    false    223            O           0    0    vaccine_vaccine_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.vaccine_vaccine_id_seq', 6, true);
          public          postgres    false    225            �           2606    19474    animal animal_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animal_id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    216            �           2606    19481    appointment appointment_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    218            �           2606    19488 "   available_date available_date_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    220            �           2606    19497    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    222            �           2606    19506    doctor doctor_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (doctor_id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    224            �           2606    19515    vaccine vaccine_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (vaccine_id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    226            �           2606    19531 *   available_date fk3hhr5kyo13rc8g6u3p3bsyedu    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fk3hhr5kyo13rc8g6u3p3bsyedu FOREIGN KEY (available_date_doctor_id) REFERENCES public.doctor(doctor_id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fk3hhr5kyo13rc8g6u3p3bsyedu;
       public          postgres    false    3483    220    224            �           2606    19516 !   animal fk7v0huk1yclccxra61tk2wo16    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk7v0huk1yclccxra61tk2wo16 FOREIGN KEY (animal_customer_id) REFERENCES public.customer(customer_id);
 K   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk7v0huk1yclccxra61tk2wo16;
       public          postgres    false    3481    222    216            �           2606    19526 '   appointment fki81ox4a93tc9ka8c2fyd7p8h1    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fki81ox4a93tc9ka8c2fyd7p8h1 FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctor(doctor_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fki81ox4a93tc9ka8c2fyd7p8h1;
       public          postgres    false    224    3483    218            �           2606    19536 #   vaccine fko6uuy26nq7sv0pyruqjmwg43g    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fko6uuy26nq7sv0pyruqjmwg43g FOREIGN KEY (vaccine_animal_id) REFERENCES public.animal(animal_id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fko6uuy26nq7sv0pyruqjmwg43g;
       public          postgres    false    216    226    3475            �           2606    19521 '   appointment fksne2j50y78it7iyf8yjxhi1cl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fksne2j50y78it7iyf8yjxhi1cl FOREIGN KEY (appointment_animal_id) REFERENCES public.animal(animal_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fksne2j50y78it7iyf8yjxhi1cl;
       public          postgres    false    218    3475    216            3   �   x�M��J1����)�"Iv+�u[lA<uA/�:�a��L���2��'o��2K�x����k��/$�cq�b7ã�D��]ks�m	��4���X�N}q�A�G���hSjg/���5���������0�<'����jW]���#4��|�*����)a;Ex���9����Ѱ�-��M?�P��B�V�R;����U�l�[_a≻kq��o�R��WR�      5   N   x�U��	�0��5�H�������h������Pj��uG���'��-�E��化��ܛpf˴����-e�� ���      7   J   x�M̹�0C�Z�Ł�#�w��sDU������/�b
a�]��C��"gu�]Y|f�Z<�*@�N��,��s��      9   �   x�MP�N�@����_`%�#R�(&EJh,Ѭ}9��l���R����a�)0��jfG��G��=����G[^�ڴ<�����Ng��u��S<�f�o�l����"���$8+O�`�F���7si�M�0k��#�9�ғQ=Q���Ny�n������b!Y�����F�*����&��-z��<w0��C�z��MJ?r�qL��#��TO� i-y�^y�[�e��=�]��QD��T�� sG      ;   �   x�U��N�0Ek�+�+���݂h;$���� �AJ>�6%-i���c
XNy�{���0ZXm"�]`M4�a����<>{�|䨘߆���B	���d{\Gc�#=l��ΑNS�-�jv9)nI1�BB
%���k4�1ln�,�t�}��9����29Y��Jj�%;�K�������G�r������
M(�*��Y���г]�᧞<��:E=�L��Е�����s�6u      =   b   x�m���0D�s\���8�Gz�z���IH��%?�9 ఞ۱��%
j�<�ę4&~���J�vk7���dk�][���L��iI�<��+�     
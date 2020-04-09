insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa) values 
('pacijent', 'asd', 'asd', 'qwe', 'qwe', '123', '123', '123', '123');
insert into karton (broj_osiguranika, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa, pacijent) 
values ('123', 10, 10, 10, 10, 1, 1);
update korisnik
set karton = 1
where id = 1;
---------------
insert into klinika (naziv, opis, adresa)
values ('moja klinika', 'super klinika', 'moja adresa');
insert into tip_posete (pregled, naziv, klinika, cena)
values (true, 'super pregled', 1, 200);
insert into sala (broj, naziv, klinika)
values ('21', 'sala jedna mala', 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('20.04.2020.', 'dd.MM.yyyy.'), null, 1, 1, 1, 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('21.04.2020.', 'dd.MM.yyyy.'), 0.2, 1, 1, 1, 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('22.04.2020.', 'dd.MM.yyyy.'), 0.2, 1, 1, 1, 1);
insert into korisnik (tip, ime, prezime, email, lozinka)
values ('lekar', 'baba', 'deda', 'asd1', 'asd');
insert into korisnik (tip, ime, prezime, email, lozinka)
values ('lekar', 'mama', 'tata', 'asd2', 'asd');
insert into lekar_poseta (lekar, poseta)
values (2, 1);
insert into lekar_poseta (lekar, poseta)
values (3, 1);
insert into lekar_poseta (lekar, poseta)
values (2, 2);
insert into lekar_poseta (lekar, poseta)
values (3, 2);
insert into lekar_poseta (lekar, poseta)
values (2, 3);
---------------
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('01.04.2020.', 'dd.MM.yyyy.'), 0.2, 3, 1, 1, 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('02.04.2020.', 'dd.MM.yyyy.'), 0.2, 3, 1, 1, 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('03.04.2020.', 'dd.MM.yyyy.'), 0.2, 3, 1, 1, 1);
insert into poseta (pocetak, popust, stanje, karton, sala, tip_posete)
values (to_date('04.04.2020.', 'dd.MM.yyyy.'), 0.2, 3, 1, 1, 1);
-----------------
insert into lekar_poseta (lekar, poseta)
values (2, 4);
insert into lekar_poseta (lekar, poseta)
values (3, 4);
insert into lekar_poseta (lekar, poseta)
values (2, 5);
insert into lekar_poseta (lekar, poseta)
values (3, 6);
insert into lekar_poseta (lekar, poseta)
values (2, 7);
--------------
insert into izvestaj (opis, poseta)
values ('ovo je strasno', 4);
insert into izvestaj (opis, poseta)
values ('ovo je strasno', 5);
insert into izvestaj (opis, poseta)
values ('ovo je strasno', 6);
insert into izvestaj (opis, poseta)
values ('ovo je strasno', 7);
------------------
update poseta
set izvestaj = 1
where id = 4;
update poseta
set izvestaj = 2
where id = 5;
update poseta
set izvestaj = 3
where id = 6;
update poseta
set izvestaj = 4
where id = 7;
-------------
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('lek', 'lek1', 'sifra1');
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('lek', 'lek2', 'sifra2');
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('lek', 'lek3', 'sifra3');
---------------------
insert into korisnik (tip, ime, prezime, email, lozinka)
values ('sestra', 'baba', 'deda', 'asd1', 'asd');
insert into korisnik (tip, ime, prezime, email, lozinka)
values ('sestra', 'mama', 'tata', 'asd2', 'asd');
-----------------
insert into terapija (izvestaj, sestra)
values (1, 4);
insert into terapija (izvestaj, sestra)
values (2, 5);
insert into terapija (izvestaj, sestra)
values (3, 4);
insert into terapija (izvestaj, sestra)
values (4, 5);
-------------
insert into terapija_lek (terapija, lek)
values (1, 1);
insert into terapija_lek (terapija, lek)
values (1, 2);
insert into terapija_lek (terapija, lek)
values (1, 3);
---------------
insert into terapija_lek (terapija, lek)
values (2, 1);
insert into terapija_lek (terapija, lek)
values (2, 2);
insert into terapija_lek (terapija, lek)
values (3, 2);
insert into terapija_lek (terapija, lek)
values (4, 2);
--------------
update izvestaj set terapija = 1
where id = 1;
update izvestaj set terapija = 2
where id = 2;
update izvestaj set terapija = 3
where id = 3;
update izvestaj set terapija = 4
where id = 4;
--------------
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('dijagnoza', 'dij1', 'asd1');
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('dijagnoza', 'dij2', 'asd2');
insert into stavka_sifrarnika (tip, naziv, sifra)
values ('dijagnoza', 'dij3', 'asd3');
--imam 1, 2, 3, 4 izvestaj
--imam 4, 5, 6 dijagnoze

insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 5);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 6);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (2, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 5);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 4);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 5);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 6);
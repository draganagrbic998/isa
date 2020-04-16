insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra) values 
('pacijent', 'nasmejlservis@gmail.com', 'asd', 'qwe', 'qwe', '123', '123', '123', '123', true, true);
insert into karton (broj_osiguranika, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa, pacijent) 
values ('123', 10, 10, 10, 10, 1, 1);
update korisnik
set karton = 1
where id = 1;
---------------
insert into klinika (naziv, opis, adresa)
values ('moja klinika', 'super klinika', 'moja adresa');
insert into tip_posete (pregled, naziv, klinika, cena, sati, minute)
values (true, 'super pregled', 1, 200, 1, 30);
insert into sala (broj, naziv, klinika)
values ('21', 'sala jedna mala', 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ('2020-04-20 12:00', null, 1, 1, 1, 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ('2020-04-21 12:00', 0.2, 1, 1, 1, 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ('2020-04-22 12:00', 0.2, 1, 1, 1, 1);
insert into korisnik (tip, ime, prezime, email, lozinka, klinika, pocetno_vreme, krajnje_vreme, specijalizacija,  telefon, drzava, grad, adresa, aktivan, promenjena_sifra)
values ('lekar', 'baba', 'deda', 'dragana.grbic.98@uns.ac.rs', 'asd', 1,  '2020-04-20 10:00', '2020-04-20 20:00', 1, 'asd', 'asd', 'asd', 'asd', true, true);
insert into korisnik (tip, ime, prezime, email, lozinka, klinika, pocetno_vreme, krajnje_vreme, specijalizacija,  telefon, drzava, grad, adresa, aktivan, promenjena_sifra)
values ('lekar', 'mama', 'tata', 'draganaasd@gmail.com', 'asd', 1, '2020-04-20 10:00', '2020-04-20 20:00', 1, 'asd', 'asd', 'asd', 'asd', true, true);
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
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ( '2020-04-01 10:00', 0.2, 3, 1, 1, 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ( '2020-04-02 10:00', 0.2, 3, 1, 1, 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ( '2020-04-03 10:00', 0.2, 3, 1, 1, 1);
insert into poseta (datum, popust, stanje, karton, sala, tip_posete)
values ( '2020-04-04 10:00', 0.2, 3, 1, 1, 1);
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
insert into lek ( naziv, sifra)
values ('lek1', 'sifra1');
insert into lek ( naziv, sifra)
values ('lek2', 'sifra2');
insert into lek ( naziv, sifra)
values ( 'lek3', 'sifra3');
---------------------
insert into korisnik (tip, ime, prezime, email, lozinka, telefon, drzava, grad, adresa, aktivan, promenjena_sifra)
values ('sestra', 'baba', 'deda', 'asd1', 'asd', 'asd', 'asd', 'asd', 'asd', true, true);
insert into korisnik (tip, ime, prezime, email, lozinka, telefon, drzava, grad, adresa, aktivan, promenjena_sifra)
values ('sestra', 'mama', 'tata', 'asd2', 'asd', 'asd', 'asd', 'asd', 'asd', true, true);
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
insert into dijagnoza ( naziv, sifra)
values ('dij1', 'asd1');
insert into dijagnoza ( naziv, sifra)
values ( 'dij2', 'asd2');
insert into dijagnoza ( naziv, sifra)
values ( 'dij3', 'asd3');
--imam 1, 2, 3, 4 izvestaj
--imam 4, 5, 6 dijagnoze

insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 2);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (1, 3);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (2, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 2);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (3, 3);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 1);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 2);
insert into izvestaj_dijagnoza (izvestaj, dijagnoza)
values (4, 3);
-----------------------
insert into poseta (datum, popust, stanje, sala, tip_posete)
values ('2020-04-20 12:00:00', 0.2, 0, 1, 1);
insert into poseta (datum, popust, stanje, sala, tip_posete)
values ('2020-04-22 12:00:00', 0.2, 0, 1, 1);
----------------------
insert into lekar_poseta (lekar, poseta)
values (2, 8);
insert into lekar_poseta (lekar, poseta)
values (3, 9);
-----------------
insert into korisnik (tip, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjena_sifra) values 
('super', 'petar@gmail.com', 'aaa', 'p', 'n', '123', '123', '123', '123', true, true);
insert into korisnik (tip, ime, prezime, email, lozinka, klinika, aktivan, promenjena_sifra,  telefon, drzava, grad, adresa)
values ('admin', 'm', 't', 'milica@gmail.com', 'aaa', 1, true, true, 'asd', 'asd', 'asd', 'asd');
insert into korisnik (tip, ime, prezime, email, lozinka, klinika,pocetno_vreme, krajnje_vreme, specijalizacija, aktivan, promenjena_sifra, telefon, drzava, grad, adresa)
values ('lekar', 'velja', 'pantic', 'velja@gmail.com', 'aaa', 1,'2020-04-02 10:00','2020-04-02 17:00', 1, true, true, 'asd', 'asd', 'asd', 'asd');
insert into korisnik (tip, ime, prezime, email, lozinka, klinika,pocetno_vreme, krajnje_vreme, specijalizacija, aktivan, promenjena_sifra, telefon, drzava, grad, adresa)
values ('lekar', 'irina', 'sajak', 'irina@gmail.com', 'aaa', 1, '2020-04-02 10:00','2020-04-02 17:00', 1, true, true, 'asd', 'asd', 'asd', 'asd');
insert into korisnik (tip, ime, prezime, email, lozinka, klinika, pocetno_vreme, krajnje_vreme, specijalizacija, aktivan, promenjena_sifra, telefon, drzava, grad, adresa)
values ('lekar', 'miroslav', 'glisic', 'glisa@gmail.com', 'aaa', 1,'2020-04-02 10:00','2020-04-02 17:00', 1, true, true, 'asd', 'asd', 'asd', 'asd');
--DIJAGNOZE
insert into stavka_sifrarnika (tip, sifra, naziv) values ('dijagnoza',  'sifra1', 'uzasna dijagnoza');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('dijagnoza',  'sifra2', 'ok dijagnoza');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('dijagnoza',  'sifra3', 'moze i gora dijagnoza');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('dijagnoza',  'sifra4', 'za preziveti dijagnoza');

--LEKOVI
insert into stavka_sifrarnika (tip, sifra, naziv) values ('lek',  'sifra5', 'uzasan lek');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('lek',  'sifra6', 'ok lek');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('lek',  'sifra7', 'moze i gori lek');
insert into stavka_sifrarnika (tip, sifra, naziv) values ('lek',  'sifra8', 'za preziveti lek');

--KLINIKE
insert into klinika (naziv, opis, adresa) values ('neka klinika', 'uzas jedan', 'blabla');
insert into klinika (naziv, opis, adresa) values ('neka klinika tamo daleko', 'ima i gorih', 'blabla');
insert into klinika (naziv, opis, adresa) values ('nikad nije kasno', 'boze me sacuvaj', 'blabla');
insert into klinika (naziv, opis, adresa) values ('kod nas je najbolje', 'dolazite ovamo', 'blabla');

--SALE
insert into sala (broj, naziv, klinika) values ('broj1', 'sala1', 1);
insert into sala (broj, naziv, klinika) values ('broj2', 'sala2', 2);
insert into sala (broj, naziv, klinika) values ('broj3', 'sala3', 3);
insert into sala (broj, naziv, klinika) values ('broj4', 'sala4', 4);

--PREGLED (TIP POSETE)
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'super pregled', 1, 100.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'ok pregled', 2, 200.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'mmm pregled', 3, 300.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'bljak pregled', 4, 50.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'super pregled2', 1, 2100.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'ok pregled2', 2, 1200.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'mmm pregled2', 3, 1300.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (true, 'bljak pregled2', 4, 150.0);

--OPERACIJA (TIP POSETE)
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'super operacija', 1, 100.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'ok operacija', 2, 200.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'mmm operacija', 3, 300.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'bljak operacija', 4, 50.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'super operacija2', 1, 1100.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'ok operacija2', 2, 1200.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'mmm operacija2', 3, 1300.0);
insert into tip_posete (pregled, naziv, klinika, cena) values (false, 'bljak operacija2', 4, 150.0);

--ZAHTEVI ZA REGISTRACIJU 
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('asd@gmail.com', 'ja sam budala', 'pera', 'peric', '123123123', '656565', 'srbija', 'novi sad', 'blabla');
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('qwe@gmail.com', 'a sta ti radis ovde????', 'djoka', 'djokic', '898989', '777777', 'srbija', 'novi sad', 'blabla');
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('uio@gmail.com', 'aj se skloni', 'peraqweqwe', 'pericqweqwe', '123123123', '656565', 'srbija', 'novi sad', 'blabla');
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('wer@gmail.com', 'molim te idi', 'darkovic', 'darko', '123123123', '656565', 'srbija', 'novi sad', 'blabla');
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('iop@gmail.com', 'zdravo ti???', 'marko', 'markovic', '123123123', '656565', 'srbija', 'novi sad', 'blabla');
insert into zahtev_registracija (email, lozinka, ime, prezime, telefon, broj_osiguranika, drzava, grad, adresa)
values ('ddd@gmail.com', 'PA STA!!!!!!!!!!!!!!!', 'miki', 'mikic', '44444', '123123', 'srbija', 'novi sad', 'blabla');


--SUPER ADMINI (KORISNIK)
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, drzava, grad, adresa) 
values ('super', true, true, 'super@gmail.com', 'ja sam super', 'super', 'super', '123123', 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, drzava, grad, adresa) 
values ('super', true, true, 'super2@gmail.com', 'ja sam super2', 'super2', 'super2', '123123', 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, drzava, grad, adresa) 
values ('super', true, true, 'super3@gmail.com', 'ja sam super3', 'super3', 'super3', '123123', 'srbija', 'novi sad', 'blabla');

--ADMINI (KORISNIK)
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, klinika,  drzava, grad, adresa) 
values ('admin', true, true, 'admin@gmail.com', 'ja sam admin', 'admin', 'admin', '123123', 1, 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, klinika, drzava, grad, adresa) 
values ('admin', true, true, 'admin2@gmail.com', 'ja sam admin2', 'admin2', 'admin2', '123123',  2, 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  klinika, drzava, grad, adresa) 
values ('admin', true, true, 'admin3@gmail.com', 'ja sam admin3', 'admin3', 'admin3', '123123',  3, 'srbija', 'novi sad', 'blabla');

--SESTRE (KORISNIK)
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, klinika, drzava, grad, adresa) 
values ('sestra', true, true, 'sestra@gmail.com', 'ja sam sestra', 'sestra', 'sestra', '123123', 1, 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  klinika, drzava, grad, adresa) 
values ('sestra', true, true, 'sestra2@gmail.com', 'ja sam sestra2', 'sestra2', 'sestra2', '123123', 2, 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, klinika, drzava, grad, adresa) 
values ('sestra', true, true, 'sestra3@gmail.com', 'ja sam sestra3', 'sestra3', 'sestra3', '123123', 3, 'srbija', 'novi sad', 'blabla');

--LEKARI (KORISNIK)
--treba dodati radno vreme
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, klinika, specijalizacija,  pocetno_vreme, krajnje_vreme) 
values ('lekar', true, true, 'lekar@gmail.com', 'ja sam lekar', 'lekar', 'lekar', '123123', 'srbija', 'novi sad', 'blabla', 1, 'izivljavanje',  to_date('2:00', 'HH:MI'), to_date('12:00', 'HH:MI'));
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, klinika, specijalizacija,  pocetno_vreme, krajnje_vreme) 
values ('lekar', true, true, 'lekar2@gmail.com', 'ja sam lekar2', 'lekar2', 'lekar2', '123123', 'srbija', 'novi sad', 'blabla', 2,'istresanje',  to_date('2:00', 'HH:MI'), to_date('12:00', 'HH:MI'));
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  drzava, grad, adresa,klinika, specijalizacija,  pocetno_vreme, krajnje_vreme) 
values ('lekar', true, true, 'lekar3@gmail.com', 'ja sam lekar3', 'lekar3', 'lekar3', '123123','srbija', 'novi sad', 'blabla',  3, 'provociranje',  to_date('2:00', 'HH:MI'), to_date('12:00', 'HH:MI'));

--PACIJENT (KORISNIK)
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  drzava, grad, adresa) 
values ('pacijent', true, true, 'pacijent@gmail.com', 'ja sam pacijent', 'pacijent', 'pacijent', '123123', 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  drzava, grad, adresa) 
values ('pacijent', true, true, 'pacijent2@gmail.com', 'ja sam pacijent2', 'pacijent2', 'pacijent2', '123123', 'srbija', 'novi sad', 'blabla');
insert into korisnik (tip, aktivan, promenjena_sifra, email, lozinka, ime, prezime, telefon,  drzava, grad, adresa) 
values ('pacijent', true, true, 'pacijent3@gmail.com', 'ja sam pacijent3', 'pacijent3', 'pacijent3', '123123', 'srbija', 'novi sad', 'blabla');

--KARTON
insert into karton (broj_osiguranika, pacijent, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa)
values ('123', 1, 10, 10, 10, 10, 1);
insert into karton (broj_osiguranika, pacijent, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa)
values ('1234', 2, 20, 20, 20, 20, 2);
insert into karton (broj_osiguranika, pacijent, visina, tezina, leva_dioptrija, desna_dioptrija, krvna_grupa)
values ('1235', 3, 30, 30, 30, 30, 3);
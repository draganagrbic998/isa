# Projekat iz predmeta Internet softverske arhitekture

Članovi tima:
1. Dragana Grbić SW22/2017
2. Milica Poparić SW21/2017
3. Petar Nikolić SW31/2017

Korišćene tehnologije:
1. spring framework
2. vue.js
3. H2 baza podataka (korišćena prilikom razvoja aplikacije)
4. Postgres baza podataka (korišćena prilikom deployment-a aplikacije)
5. bootstrap

Korišćeni alati:
1. alat za integraciju - TravisCI
2. alat za analizu kvaliteta koda - SonarCloud
3. deployment izvršen na Heroku (environment tab)

Pokretanje projekta:
1. importovati projekat u Eclipse workspace: Import -> Maven -> Existing Maven Project
2. obrisati ceo sadržaj "application.properties" fajla i prekopirati sadržaj "h2.txt" fajla u njega
3. preimenovati "data-postgres.sql" fajl na "data.sql"
4. instalirati sve dependency-je iz pom.xml-a
5. desni klik na projekat -> Run As -> Java Application/Spring Boot app

Link ka heroku aplikaciji: https://nasaaplikacija.herokuapp.com/

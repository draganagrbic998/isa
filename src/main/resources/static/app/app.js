//Registracija & prijava:
const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const poslatZahtev = {template: '<poslatZahtev></poslatZahtev>'}
const zahteviPacijenti = {template: '<zahteviPacijenti></zahteviPacijenti>'}
const aktivirajNalog = {template: '<aktivirajNalog></aktivirajNalog>'}

//Home stranice:
const pacijentHome = {template: '<pacijentHome></pacijentHome>'}
const superAdminHome = {template: '<superAdminHome></superAdminHome>'}
const adminHome = {template: '<adminHome></adminHome>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const sestraHome = {template: '<sestraHome></sestraHome>'}

//Profili & slicno:
const profil = {template: '<profil></profil>'}
const karton = {template: '<karton></karton>'}
const termini = {template: '<termini></termini>'}
const bolesti = {template: '<bolesti></bolesti>'}
const profilAdmin = {template: '<profilAdmin></profilAdmin>'}
const klinikeSlobodno = {template: '<klinikeSlobodno></klinikeSlobodno>'}
const klinikeLekari = {template: '<klinikeLekari></klinikeLekari>'}
const overaRecepata = {template: '<overaRecepata></overaRecepata>'}
const promenaSifre = {template: '<promenaSifre></promenaSifre>'}

//Dodavanja:
const registracijaKlinike = {template: '<registracijaKlinike></registracijaKlinike>'}
const registracijaSuperAdmina = {template: '<registracijaSuperAdmina></registracijaSuperAdmina>'}
const registracijaAdmina = {template: '<registracijaAdmina></registracijaAdmina>'}
const registracijaLekara = {template: '<registracijaLekara></registracijaLekara>'}
const dodajDijagnozu = {template: '<dodajDijagnozu></dodajDijagnozu>'}
const dodajLek = {template: '<dodajLek></dodajLek>'}
const dodajTipPosete = {template: '<dodajTipPosete></dodajTipPosete>'}
const dodajSalu = {template: '<dodajSalu></dodajSalu>'}

//Pretrage:
const dijagnozePretraga = {template: '<dijagnozePretraga></dijagnozePretraga>'}
const lekoviPretraga = {template: '<lekoviPretraga></lekoviPretraga>'}
const tipPregledaPretraga = {template: '<tipPregledaPretraga></tipPregledaPretraga>'}
const lekariPretraga = {template: '<lekariPretraga></lekariPretraga>'}
const sestrePretraga = {template: '<sestrePretraga></sestrePretraga>'}


const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}, 
		{path: '/poslatZahtev', component: poslatZahtev}, 
		{path: '/aktivirajNalog', component: aktivirajNalog}, 
		{path: '/pacijentHome', component: pacijentHome}, 
		{path: '/superAdminHome', component: superAdminHome},
		{path: '/adminHome', component: adminHome},
		{path: '/lekarHome', component: lekarHome},
		{path: '/sestraHome', component: sestraHome},
		{path: '/profil', component: profil}, 
		{path: '/karton', component: karton}, 
		{path: '/termini', component: termini}, 
		{path: '/bolesti', component: bolesti}, 
		{path: '/registracijaKlinike', component: registracijaKlinike},
		{path: '/registracijaSuperAdmina', component: registracijaSuperAdmina},
		{path: '/registracijaAdmina', component: registracijaAdmina},
		{path: '/registracijaLekara', component: registracijaLekara},
		{path: '/lekariPretraga', component: lekariPretraga},
		{path: '/dodajDijagnozu', component: dodajDijagnozu},
		{path: '/dijagnozePretraga', component: dijagnozePretraga},
		{path: '/dodajLek', component: dodajLek},
		{path: '/lekoviPretraga', component: lekoviPretraga},
		{path: '/dodajTipPosete', component: dodajTipPosete},
		{path: '/dodajSalu', component: dodajSalu},
		{path: '/klinikeSlobodno', component: klinikeSlobodno}, 
		{path: '/klinikeLekari', component: klinikeLekari},
		{path: '/zahteviPacijenti', component: zahteviPacijenti},
		{path: '/profilAdmin', component: profilAdmin},
		{path: '/tipPregledaPretraga', component: tipPregledaPretraga},
		{path: '/sestrePretraga', component: sestrePretraga},
		{path: '/overaRecepata', component: overaRecepata}, 
		{path: '/promenaSifre', component: promenaSifre}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

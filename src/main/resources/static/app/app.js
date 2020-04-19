const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const poslatZahtev = {template: '<poslatZahtev></poslatZahtev>'}
const pacijentHome = {template: '<pacijentHome></pacijentHome>'}
const superAdminHome = {template: '<superAdminHome></superAdminHome>'}
const adminHome = {template: '<adminHome></adminHome>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const profil = {template: '<profil></profil>'}
const karton = {template: '<karton></karton>'}
const termini = {template: '<termini></termini>'}
const bolesti = {template: '<bolesti></bolesti>'}
const registracijaKlinike = {template: '<registracijaKlinike></registracijaKlinike>'}
const registracijaAdmina = {template: '<registracijaAdmina></registracijaAdmina>'}
const registracijaLekara = {template: '<registracijaLekara></registracijaLekara>'}
const lekariPretraga = {template: '<lekariPretraga></lekariPretraga>'}
const dodajDijagnozu = {template: '<dodajDijagnozu></dodajDijagnozu>'}
const dijagnozePretraga = {template: '<dijagnozePretraga></dijagnozePretraga>'}
const dodajLek = {template: '<dodajLek></dodajLek>'}
const lekoviPretraga = {template: '<lekoviPretraga></lekoviPretraga>'}
const dodajTipPosete = {template: '<dodajTipPosete></dodajTipPosete>'}
const dodajSalu = {template: '<dodajSalu></dodajSalu>'}
const klinikeSlobodno = {template: '<klinikeSlobodno></klinikeSlobodno>'}
const klinikeLekari = {template: '<klinikeLekari></klinikeLekari>'}

const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}, 
		{path: '/poslatZahtev', component: poslatZahtev}, 
		{path: '/pacijentHome', component: pacijentHome}, 
		{path: '/superAdminHome', component: superAdminHome},
		{path: '/adminHome', component: adminHome},
		{path: '/lekarHome', component: lekarHome},
		{path: '/profil', component: profil}, 
		{path: '/karton', component: karton}, 
		{path: '/termini', component: termini}, 
		{path: '/bolesti', component: bolesti}, 
		{path: '/registracijaKlinike', component: registracijaKlinike},
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
		{path: '/klinikeLekari', component: klinikeLekari}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

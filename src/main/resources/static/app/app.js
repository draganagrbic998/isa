const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const poslatZahtev = {template: '<poslatZahtev></poslatZahtev>'}
const pacijentHome = {template: '<pacijentHome></pacijentHome>'}
const profil = {template: '<profil></profil>'}
const karton = {template: '<karton></karton>'}
const termini = {template: '<termini></termini>'}
const bolesti = {template: '<bolesti></bolesti>'}
const registracijaLekara = {template: '<registracijaLekara></registracijaLekara>'}
const registracijaAdmina = {template: '<registracijaAdmina></registracijaAdmina>'}
const registracijaKlinike = {template: '<registracijaKlinike></registracijaKlinike>'}
const dodajDijagnozu = {template: '<dodajDijagnozu></dodajDijagnozu>'}
const dijagnozePretraga = {template: '<dijagnozePretraga></dijagnozePretraga>'}
const dodajLek = {template: '<dodajLek></dodajLek>'}
const lekoviPretraga = {template: '<lekoviPretraga></lekoviPretraga>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const adminKCHome = {template: '<adminKCHome></adminKCHome>'}
const adminKlinikeHome = {template: '<adminKlinikeHome></adminKlinikeHome>'}
const lekariPretraga = {template: '<lekariPretraga></lekariPretraga>'}
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
		{path: '/profil', component: profil}, 
		{path: '/karton', component: karton}, 
		{path: '/termini', component: termini}, 
		{path: '/bolesti', component: bolesti}, 
		{path: '/registracijaLekara', component: registracijaLekara},
		{path: '/registracijaAdmina', component: registracijaAdmina},
		{path: '/registracijaKlinike', component: registracijaKlinike},
		{path: '/dodajDijagnozu', component: dodajDijagnozu},
		{path: '/dijagnozePretraga', component: dijagnozePretraga},
		{path: '/dodajLek', component: dodajLek},
		{path: '/lekoviPretraga', component: lekoviPretraga},
		{path: '/lekarHome', component: lekarHome},
		{path: '/adminKCHome', component: adminKCHome},
		{path: '/adminKlinikeHome', component: adminKlinikeHome},
		{path: '/lekariPretraga', component: lekariPretraga},
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

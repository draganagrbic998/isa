const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const registracijaLekara = {template: '<registracijaLekara></registracijaLekara>'}
const registracijaAdminaKlinike = {template: '<registracijaAdminaKlinike></registracijaAdminaKlinike>'}
const registracijaKlinike = {template: '<registracijaKlinike></registracijaKlinike>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const adminKCHome = {template: '<adminKCHome></adminKCHome>'}
const lekarBrisanje = {template: '<lekarBrisanje></lekarBrisanje>'}
const poslatZahtev = {template: '<poslatZahtev></poslatZahtev>'}
const pacijentHome = {template: '<pacijentHome></pacijentHome>'}
const profil = {template: '<profil></profil>'}
const karton = {template: '<karton></karton>'}
const termini = {template: '<termini></termini>'}

const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}, 
		{path: '/registracijaLekara', component: registracijaLekara},
		{path: '/registracijaAdminaKlinike', component: registracijaAdminaKlinike},
		{path: '/registracijaKlinike', component: registracijaKlinike},
		{path: '/lekarHome', component: lekarHome},
		{path: '/adminKCHome', component: adminKCHome},
		{path: '/lekarBrisanje', component: lekarBrisanje}, 
		{path: '/poslatZahtev', component: poslatZahtev}, 
		{path: '/pacijentHome', component: pacijentHome}, 
		{path: '/profil', component: profil}, 
		{path: '/karton', component: karton}, 
		{path: '/termini', component: termini}, 

		
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

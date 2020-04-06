const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const poslat_zahtev = {template: '<poslat_zahtev></poslat_zahtev>'}
const registracijaLekara = {template: '<registracijaLekara></registracijaLekara>'}
const registracijaKlinike = {template: '<registracijaKlinike></registracijaKlinike>'}
const lekarHome = {template: '<lekarHome></lekarHome>'}
const adminKCHome = {template: '<adminKCHome></adminKCHome>'}
const lekarBrisanje = {template: '<lekarBrisanje></lekarBrisanje>'}
const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}, 
		{path: '/poslat_zahtev', component: poslat_zahtev},
		{path: '/registracijaLekara', component: registracijaLekara},
		{path: '/registracijaKlinike', component: registracijaKlinike},
		{path: '/lekarHome', component: lekarHome},
		{path: '/adminKCHome', component: adminKCHome},
		{path: '/lekarBrisanje', component: lekarBrisanje}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

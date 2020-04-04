const prijava = {template: '<prijava></prijava>'}
const registracija = {template: '<registracija></registracija>'}
const poslat_zahtev = {template: '<poslat_zahtev></poslat_zahtev>'}

const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}, 
		{path: '/poslat_zahtev', component: poslat_zahtev}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

const registracija = {template: '<registracija></registracija>'}
const prijava = {template: '<prijava></prijava>'}

const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: prijava},
		{path: '/registracija', component: registracija}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

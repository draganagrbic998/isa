const registracija = {template: '<registracija></registracija>'}

const router = new VueRouter({

	mode: 'hash', 
	routes: [
		{path: '/', component: registracija}
	]
	
});

var app = new Vue({
	router: router, 
	el: '#main_div'
});

Vue.component('prijava', {

	data: function(){
		return {
			user: {
				'email': '', 
				'lozinka': ''
			}, 
			greskaEmail: '', 
			greskaLozinka: '', 
			greskaServer: '', 
			greska: false
		}
	},
	
	template: `
	
		<div class="prijava">
		
			<h1>Prijava</h1>
			
			<table>
			
				<tr><td class="left">Email: </td><td><input type="text" v-model="user.email"></td><td>{{greskaEmail}}</td></tr>
				<tr><td class="left">Lozinka: </td><td><input type="password" v-model="user.lozinka"></td><td>{{greskaLozinka}}</td></tr>
				<tr><td colspan="3"><br><button v-on:click="prijava()">PRIJAVA</button><br></td></tr>
				<tr><td colspan="3">{{greskaServer}}</td></tr>
				<br>
				<tr><td colspan="6">Niste registrovani? <router-link to="/registracija">Registruj se</router-link></td></tr>
				<tr><td colspan="6">Registruj lekara <router-link to="/registracijaLekara">Registruj lekara</router-link></td></tr>
				<tr><td colspan="6">Obrisi lekara <router-link to="/lekarBrisanje">Obrisi lekara</router-link></td></tr>
			
			</table>
		
		</div>
	
	`, 
	
	methods: {
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaLozinka = '';
			this.greskaServer = '';
			this.greska = false;
		}, 
		
		prijava: function(){
			
			this.osvezi();
			
			if (this.user.email == ''){
				this.greskaEmail = "Niste uneli email adresu. ";
				this.greska = true;
			}
			
			if (this.user.lozinka == ''){
				this.greskaLozinka = "Niste uneli lozinku. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			alert("OVA FUNKCTIONALNOST JOS UVEK NIJE IMPLEMENTIRANA, ALI JE SLANJE ZAHTEVA ZA REGISTRACIJU IMPLEMENTIRANO!");
			
			
		}
		
	}
	
});

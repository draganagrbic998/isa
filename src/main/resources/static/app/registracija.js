Vue.component('registracija', {

	data: function(){
		return {
			zahtev: {
				'emailDTO': '', 
				'lozinkaDTO': '', 
				'imeDTO': '', 
				'prezimeDTO': '', 
				'telefonDTO': '', 
				'brojOsiguranikaDTO': ''
			}, 
			novaLozinka: '',
			ponovljenaLozinka: '', 
			greskaEmail: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '', 
			greskaBrojOsiguranika: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog pacijenta</h1>
			
			<br>
			
			<div>
			
				<table>
				
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="zahtev.emailDTO"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="zahtev.imeDTO"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="zahtev.prezimeDTO"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="zahtev.telefonDTO"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Broj osiguranika: </td><td class="right"><input type="text" v-model="zahtev.brojOsiguranikaDTO"></td><td>{{greskaBrojOsiguranika}}</td></tr>
					<tr><td class="left">Lozinka: </td><td class="right"><input type="password" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><td class="left">Ponovljena lozinka: </td><td class="right"><input type="password" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>
					<br>
					<tr><td colspan="3"><button v-on:click="posalji_zahtev()">POSALJI ZAHTEV</button><br></td></tr>
					
				
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}
	}, 
	
	methods: {
		
		emailProvera: function emailIsValid(email){
    		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
		}, 
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaNovaLozinka = '';
			this.greskaPonovljenaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaBrojOsiguranika = '';
			this.greska = false;
		}, 
		
		posalji_zahtev: function(){
			
			this.osvezi();
			this.zahtev.lozinka = this.novaLozinka;
			
			if (!this.emailProvera(this.zahtev.emailDTO)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.zahtev.imeDTO == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.zahtev.prezimeDTO == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.zahtev.telefonDTO)) || parseInt(this.zahtev.telefonDTO) < 0){
				this.greskaTelefon = "Telefon nije ispravan. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.zahtev.brojOsiguranikaDTO)) || parseInt(this.zahtev.brojOsiguranikaDTO) < 0){
				this.greskaBrojOsiguranika = "Broj osiguranika nije ispravan. ";
				this.greska = true;
			}
			
			if (this.novaLozinka == ''){
				this.greskaNovaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.ponovljenaLozinka != this.novaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			alert("MAMA MIA");
			
		}
		
	}
	
});

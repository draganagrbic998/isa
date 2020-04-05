Vue.component('registracija', {

	data: function(){
		return {
			zahtev: {
				'id': null,
				'noviEmail': '', 
				'novaLozinka': '', 
				'novoIme': '', 
				'novoPrezime': '', 
				'noviTelefon': '', 
				'noviBrojOsiguranika': '', 
				'novaDrzava': '', 
				'novaAdresa': '', 
				'noviGrad': ''
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
			greskaDrzava: '', 
			greskaAdresa: '', 
			greskaGrad: '',
			greska: false
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog pacijenta</h1>
			
			
			<div>
			
				<table>
				
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="zahtev.noviEmail"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="zahtev.novoIme"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="zahtev.novoPrezime"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="zahtev.noviTelefon"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Broj osiguranika: </td><td class="right"><input type="text" v-model="zahtev.noviBrojOsiguranika"></td><td>{{greskaBrojOsiguranika}}</td></tr>
					<tr><td class="left">Drzava: </td><td class="right"><input type="text" v-model="zahtev.novaDrzava"></td><td>{{greskaDrzava}}</td></tr>
					<tr><td class="left">Grad: </td><td class="right"><input type="text" v-model="zahtev.noviGrad"></td><td>{{greskaGrad}}</td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="zahtev.novaAdresa"></td><td>{{greskaAdresa}}</td></tr>

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
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		posalji_zahtev: function(){
			
			this.osvezi();
			this.zahtev.novaLozinka = this.novaLozinka;
			
			if (!this.emailProvera(this.zahtev.noviEmail)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.zahtev.novoIme == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.zahtev.novoPrezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.zahtev.noviTelefon)) || parseInt(this.zahtev.noviTelefon) < 0){
				this.greskaTelefon = "Telefon nije ispravan. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.zahtev.noviBrojOsiguranika)) || parseInt(this.zahtev.noviBrojOsiguranika) < 0){
				this.greskaBrojOsiguranika = "Broj osiguranika nije ispravan. ";
				this.greska = true;
			}
			
			if (this.zahtev.novaDrzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.zahtev.noviGrad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.zahtev.novaAdresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
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
			
			axios.post("/zahtevRegistracija/kreiranje", this.zahtev)
			.then(response => {
				this.$router.push("/poslat_zahtev");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
		
	}
	
});

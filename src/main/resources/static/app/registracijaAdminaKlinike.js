Vue.component('registracijaAdminaKlinike', {

	data: function(){
		return {
			admin: {
				'id': null,
				'emailAdmin': '', 
				'lozinkaAdmin': '', 
				'imeAdmin': '', 
				'prezimeAdmin': '', 
				'telefonAdmin': '',  
				'novaDrzava': '', 
				'novaAdresa': '', 
				'noviGrad': '', 
				'novaKlinika': ''
			}, 
			novaLozinka: '',
			ponovljenaLozinka: '', 
			greskaEmail: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '', 
			greskaDrzava: '', 
			greskaAdresa: '', 
			greskaGrad: '',
			greska: false, 
			klinike: [], 
			nazivKlinike: ''
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog administratora klinike</h1>
			
			
			<div>
			
				<table>
				
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="admin.emailAdmin"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="admin.imeAdmin"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="admin.prezimeAdmin"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="admin.telefonAdmin"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Drzava: </td><td class="right"><input type="text" v-model="admin.novaDrzava"></td><td>{{greskaDrzava}}</td></tr>
					<tr><td class="left">Grad: </td><td class="right"><input type="text" v-model="admin.noviGrad"></td><td>{{greskaGrad}}</td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="admin.novaAdresa"></td><td>{{greskaAdresa}}</td></tr>
					<tr><td class="left">Lozinka: </td><td class="right"><input type="password" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><td class="left">Ponovljena lozinka: </td><td class="right"><input type="password" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>

					<tr><td class="left">Klinika: </td><td class="right"><select v-model="nazivKlinike">
						<option v-for="k in klinike">{{k.naziv}}</option>
					</select></td><td>{{greskaKlinika}}</td></tr>

					
					<br>
					<tr><td colspan="3"><button v-on:click="registruj_admina_klinike()">KREIRAJ PROFIL</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}, 
		
		nazivKlinike: function(){
			for (let k of this.klinike){
				if (k.naziv == this.nazivKlinike)
					this.admin.novaKlinika = k.id;
			}
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
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		registruj_admina_klinike: function(){
			
			this.osvezi();
			this.admin.lozinkaAdmin = this.novaLozinka;
			
			if (!this.emailProvera(this.admin.emailAdmin)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.admin.imeAdmin == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.admin.prezimeAdmin == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.admin.telefonAdmin)) || parseInt(this.admin.telefonAdmin) < 0){
				this.greskaTelefon = "Telefon nije ispravan. ";
				this.greska = true;
			}
			
			if (this.admin.novaDrzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.admin.noviGrad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.admin.novaAdresa == ''){
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
			
			axios.post("/adminKlinike/kreiranje", this.admin)
			.then(response => {
				//ovde da obavesti da je kreiran profil
				this.$router.push("/adminKCHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	},
	mounted () {
		axios
        .get("/klinika/pregled")
        .then(response => (this.klinike = response.data));
	},
	
});

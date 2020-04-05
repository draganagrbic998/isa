Vue.component('registracijaLekara', {

	data: function(){
		return {
			lekar: {
				'emailLekar': '', 
				'lozinkaLekar': '', 
				'imeLekar': '', 
				'prezimeLekar': '', 
				'telefonLekar': '',  
				'novaDrzava': '', 
				'novaAdresa': '', 
				'novaSpecijalizacija': 'Oftamolog',
				'novaKlinika': '1',
				'noviGrad': ''
			}, 
			klinike: null,
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
			greska: false
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog lekara</h1>
			
			
			<div>
			
				<table>
				
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="lekar.emailLekar"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="lekar.imeLekar"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="lekar.prezimeLekar"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="lekar.telefonLekar"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Drzava: </td><td class="right"><input type="text" v-model="lekar.novaDrzava"></td><td>{{greskaDrzava}}</td></tr>
					<tr><td class="left">Grad: </td><td class="right"><input type="text" v-model="lekar.noviGrad"></td><td>{{greskaGrad}}</td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="lekar.novaAdresa"></td><td>{{greskaAdresa}}</td></tr>
					<tr><td class="left">Lozinka: </td><td class="right"><input type="password" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><td class="left">Ponovljena lozinka: </td><td class="right"><input type="password" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>
					<tr><td class="left">Specijalizacija: </td>
						<td class="right"><select v-model = "lekar.novaSpecijalizacija">
						<option>Oftamolog</option>
						<option>Dermatolog</option>
						</select></td></tr>
					<tr><td class="left">Id klinike: </td>
						<td class="right"><select v-model="lekar.novaKlinika">
						<option v-for="k in klinike">{{k.id}}</option>
						</select></td></tr>
					
					<br>
					<tr><td colspan="3"><button v-on:click="registruj_lekara()">KREIRAJ PROFIL</button><br></td></tr>
					
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
		
		registruj_lekara: function(){
			
			this.osvezi();
			this.lekar.lozinkaLekar = this.novaLozinka;
			
			if (!this.emailProvera(this.lekar.emailLekar)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.lekar.imeLekar == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.lekar.prezimeLekar == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.lekar.telefonLekar)) || parseInt(this.lekar.telefonLekar) < 0){
				this.greskaTelefon = "Telefon nije ispravan. ";
				this.greska = true;
			}
			
			
			
			if (this.lekar.novaDrzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.lekar.noviGrad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.lekar.novaAdresa == ''){
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
			
			axios.post("/registracijaLekar/kreiranje", this.lekar)
			.then(response => {
				//ovde da obavesti da je kreiran profil
				this.$router.push("/lekarHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	},
	mounted () {
		axios
        .get("/registracijaLekar/dobaviKlinike")
        .then(response => (this.klinike = response.data));
	},
	
});

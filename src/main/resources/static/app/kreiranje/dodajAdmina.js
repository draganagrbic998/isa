Vue.component("dodajAdmina", {

	data: function(){
		return {
			admin: {
				'id': null,
				'email': '', 
				'lozinka': '', 
				'ime': '', 
				'prezime': '', 
				'telefon': '',  
				'drzava': '', 
				'grad': '', 
				'adresa': '', 
				'klinika': '', 
				'aktivan': true, 
				'promenjenaSifra': false
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
			greskaGrad: '',
			greskaAdresa: '', 
			greskaKlinika: '',
			greska: false, 
			klinike: [], 
			nazivKlinike: ''
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog administratora</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="admin.email"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="admin.ime"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="admin.prezime"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="admin.telefon"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Drzava: </td><td class="right"><input type="text" v-model="admin.drzava"></td><td>{{greskaDrzava}}</td></tr>
					<tr><td class="left">Grad: </td><td class="right"><input type="text" v-model="admin.grad"></td><td>{{greskaGrad}}</td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="admin.adresa"></td><td>{{greskaAdresa}}</td></tr>
					<tr><td class="left">Lozinka: </td><td class="right"><input type="password" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><td class="left">Ponovljena lozinka: </td><td class="right"><input type="password" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>

					<tr><td class="left">Klinika: </td><td class="right"><select v-model="nazivKlinike">
						<option v-for="k in klinike">{{k.naziv}}</option>
					</select></td><td>{{greskaKlinika}}</td></tr>
					
					<tr><td><button v-on:click="dodajAdmina()">KREIRAJ PROFIL</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	mounted () {
		
		axios
        .get("/klinika/pregled")
        .then(response => {
        	this.klinike = response.data
        })
		.catch(reponse => {
			this.$router.push("/");
		});

	},
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}, 
		
		nazivKlinike: function(){
			for (let k of this.klinike){
				if (k.naziv == this.nazivKlinike)
					this.admin.klinika = k.id;
			}
		}
		
	}, 
	
	methods: {
		
		emailProvera: function(email){
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
			this.greskaKlinika = '';
			this.greska = false;
		}, 
		
		dodajAdmina: function(){
			
			this.osvezi();
			this.admin.lozinka = this.novaLozinka;
			
			if (!this.emailProvera(this.admin.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.admin.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.admin.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.admin.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.admin.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.admin.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.admin.adresa == ''){
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
			
			if (this.admin.klinika == ''){
				this.greskaKlinika = "Klinika ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/admin/kreiranje", this.admin)
			.then(response => {
				alert("Admin uspesno kreiran!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaEmail = "Unet email nije jedinstven.";
			});
			
		}
	}
	
});
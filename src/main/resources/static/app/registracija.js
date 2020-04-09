Vue.component("registracija", {
	
	data: function(){
		return{
			zahtev: {
				"id": null, 
				"email": '', 
				"lozinka": '', 
				"ime": '', 
				"prezime": '', 
				"telefon": '', 
				"brojOsiguranika": '', 
				"drzava": '', 
				"grad": '', 
				"adresa": ''
			}, 
			novaLozinka: '',
			ponovljenaLozinka: '',
			greskaEmail: '', 
			greskaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '',
			greskaBrojOsiguranika: '', 
			greskaDrzava: '', 
			greskaGrad: '', 
			greskaAdresa: '', 
			greskaPonovljenaLozinka: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="well" id="tableBox">
		
			<h1>Registracija novog pacijenta</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
					
						<th scope="row">Email: </th>
						<td><input type="text" v-model="zahtev.email" class="form-control"></td>
						<td>{{greskaEmail}}</td>
					
					</tr>
					
					<tr>
			
						<th scope="row">Ime: </th>
						<td><input type="text" v-model="zahtev.ime" class="form-control"></td>
						<td>{{greskaIme}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Prezime: </th>
						<td><input type="text" v-model="zahtev.prezime" class="form-control"></td>
						<td>{{greskaPrezime}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Telefon: </th>
						<td><input type="text" v-model="zahtev.telefon" class="form-control"></td>
						<td>{{greskaTelefon}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Broj osiguranika: </th>
						<td><input type="text" v-model="zahtev.brojOsiguranika" class="form-control"></td>
						<td>{{greskaBrojOsiguranika}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Drzava: </th>
						<td><input type="text" v-model="zahtev.drzava" class="form-control"></td>
						<td>{{greskaDrzava}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Grad: </th>
						<td><input type="text" v-model="zahtev.grad" class="form-control"></td>
						<td>{{greskaGrad}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="zahtev.adresa" class="form-control"></td>
						<td>{{greskaAdresa}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Lozinka: </th>
						<td><input type="password" v-model="novaLozinka" class="form-control"></td>
						<td>{{greskaLozinka}}</td>
					
					</tr>
					
					<tr>
					
						<th scope="row">Ponovljena lozinka: </th>
						<td><input type="password" v-model="ponovljenaLozinka" class="form-control"></td>
						<td>{{greskaPonovljenaLozinka}}</td>
					
					</tr>
					
					<tr>
					
						<td colspan="3"><button v-on:click="posaljiZahtev()" class="btn btn-primary">POSALJI ZAHTEV</button></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}
	}, 
	
	methods: {
		
		emailProvera: function(email){
    		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
		}, 
		
		osvezi: function(){
			
			this.greskaEmail = '';
			this.greskaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaBrojOsiguranika = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greskaPonovljenaLozinka = '';
			this.greska = false;
			
		}, 
		
		posaljiZahtev: function(){
			
			this.osvezi();
			this.zahtev.lozinka = this.novaLozinka;

			if (!this.emailProvera(this.zahtev.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.zahtev.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.zahtev.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.zahtev.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.zahtev.brojOsiguranika == ''){
				this.greskaBrojOsiguranika = "Broj osiguranika ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.zahtev.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.zahtev.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.zahtev.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.zahtev.lozinka == ''){
				this.greskaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.zahtev.lozinka != this.ponovljenaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/zahtevRegistracija/kreiranje", this.zahtev)
			.then(response => {
				this.$router.push("/poslatZahtev");
			})
			.catch(error => {
				alert("SERVER ERROR!!");
			});
			
		}
		
	}
	
});




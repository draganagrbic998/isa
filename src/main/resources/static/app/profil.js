Vue.component("profil", {
	
	data: function(){
		return{
			korisnik: {}, 
			novaLozinka: '', 
			ponovljenaLozinka: '', 
			greskaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '',
			greskaDrzava: '', 
			greskaGrad: '', 
			greskaAdresa: '', 
			greska: false
		}
	}, 
	
	template: `
	
	
		<div>
		
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/profil">
          <i class="fa fa-user"></i>
          Profil 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/karton">
          <i class="fa fa-address-book"></i>
          Karton 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/bolesti">
          <i class="fa fa-line-chart"></i>
          Istorija bolesti
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/termini">
          <i class="fa fa-calendar"></i>
          Zakazani termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeSlobodno">
          <i class="fa fa-bell"></i>
          Povoljni termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeLekari">
          <i class="fa fa-hotel"></i>
          Klinike centra
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>		
		</div>
	
		<div class="card" id="tableBox">
		
			<h1>Podaci o korisniku</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Email: </th>
						<td><input type="text" v-model="korisnik.email" class="form-control" disabled></td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Ime: </th>
						<td><input type="text" v-model="korisnik.ime" class="form-control"></td>
						<td>{{greskaIme}}</td>
					</tr>
					
					<tr>
						<th scope="row">Prezime: </th>
						<td><input type="text" v-model="korisnik.prezime" class="form-control"></td>
						<td>{{greskaPrezime}}</td>
					</tr>
					
					<tr>
						<th scope="row">Telefon: </th>
						<td><input type="text" v-model="korisnik.telefon" class="form-control"></td>
						<td>{{greskaTelefon}}</td>
					</tr>
					
					<tr>
						<th scope="row">Drzava: </th>
						<td><input type="text" v-model="korisnik.drzava" class="form-control"></td>
						<td>{{greskaDrzava}}</td>
					</tr>
					
					<tr>
						<th scope="row">Grad: </th>
						<td><input type="text" v-model="korisnik.grad" class="form-control"></td>
						<td>{{greskaGrad}}</td>
					</tr>
					
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="korisnik.adresa" class="form-control"></td>
						<td>{{greskaAdresa}}</td>
					</tr>
					
					<tr>
						<th scope="row">Nova lozinka: </th>
						<td><input type="password" v-model="novaLozinka" class="form-control"></td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Ponovljena lozinka: </th>
						<td><input type="password" v-model="ponovljenaLozinka" v-bind:disabled="novaLozinka==''" class="form-control"></td>
						<td>{{greskaLozinka}}</td>
					</tr>
					
					<tr>
						<td colspan="3"><button v-on:click="izmeni()" class="btn btn-primary">IZMENI</button></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/profil")
		.then(response => {
			this.korisnik = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		}
	}, 
	
	methods: {
		
		osvezi: function(){
			this.greskaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		izmeni: function(){
			
			this.osvezi();
			
			if (this.korisnik.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.korisnik.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.korisnik.telefon == ''){
				this.greskaTelefon = "Telefon ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.korisnik.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.korisnik.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.korisnik.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka != '' && this.ponovljenaLozinka != this.novaLozinka){
				this.greskaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			this.korisnik.lozinka = this.novaLozinka != '' ? this.novaLozinka : this.korisnik.lozinka;
			
			axios.post("/pacijent/izmena", this.korisnik)
			.then(response => {
				this.$router.push("/pacijentHome");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
		
	}
	
});
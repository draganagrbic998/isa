Vue.component("adminHome", {
	data: function(){
		return{
			klinika: {},
			zahteviBroj: '',
			greskaNaziv: '', 
			greskaOpis: '', 
			greskaAdresa: '', 
			greska: false
		}
	}, 
	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">{{klinika.naziv}}</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Medicinsko osoblje
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/registracijaLekara">Dodaj medicinskog radnika</a>
          <a class="dropdown-item" href="#/lekariPretraga">Pretraga lekara</a>
          <a class="dropdown-item" href="#/sestrePretraga">Pretraga medicinara</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Tipovi pregleda
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajTipPosete">Dodaj tip pregleda</a>
          <a class="dropdown-item" href="#/tipPregledaPretraga">Pretraga tipova</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-hotel">
          </i>
          Sale
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajSalu">Dodaj salu</a>
          <a class="dropdown-item" href="#">Pretraga sala</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/kreirajPregled">
          <i class="fa fa-newspaper-o"></i>
          Kreiraj pregled
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav ">
      <li class="nav-item">
        <a class="nav-link" href="#/profilAdmin">
          <i class="fa fa-user">
          </i>
          Profil
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#/obradaZahtevGodisnji">
          <i class="fa fa-globe">
            <span class="badge badge-success">{{zahteviBroj}}</span>
          </i>
          Zahtevi odmor
        </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
		
		
		<div class="card" id="tableBox">
		
			<h1>Osnovne informacije o klinici</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Naziv klinike: </th>
						<td><input type="text" v-model="klinika.naziv" class="form-control"></td>
						<td>{{greskaNaziv}}</td>
					</tr>
					
					<tr>
						<th scope="row">Opis: </th>
						<td><input type="text" v-model="klinika.opis" class="form-control"></td>
						<td>{{greskaOpis}}</td>
					</tr>
					
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="klinika.adresa" class="form-control"></td>
						<td>{{greskaAdresa}}</td>
					</tr>
					<tr>
						<td colspan="3"><button v-on:click="izmeni()" class="btn btn-primary">IZMENI</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>
	`, 	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaOpis = '';
			this.greskaAdresa= '';
			this.greska = false;
		}, 
		
		izmeni: function(){
			
			this.osvezi();
			
			if (this.klinika.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.klinika.opis == ''){
				this.greskaOpis = "Opis ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.klinika.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			

			
			if (this.greska) return;
			
			axios.post("/klinika/izmena", this.klinika)
			.then(response => {
				alert("Podaci uspesno izmenjeni.");
				this.$router.push("/adminHome");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});	
		}
	},

	
	mounted(){
		axios.get("/user/check/admin")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/klinika/admin/pregled")
		.then(response => {this.klinika = response.data})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/zahtevOdmor/zahteviKlinika")
		.then(response => {this.zahteviBroj = response.data.length})
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});
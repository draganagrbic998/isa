Vue.component('lekariPretraga', {
	data: function(){
		return{
			lekari: {},
			pretraga: '',
			backupLekari: {},
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">PRETRAGA LEKARA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminKlinikeHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Adresa
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/registracijaLekara">Dodaj lekara</a>
          <a class="dropdown-item" href="#">Pretraga lekara</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Specijalizacija
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Dodaj tip pregleda</a>
          <a class="dropdown-item" href="#">Pretraga tipova</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-hotel">
          </i>
          Radno vreme
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Dodaj salu</a>
          <a class="dropdown-item" href="#">Pretraga sala</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
    </ul>
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Search</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Ime </th>
			<th> Prezime </th>
		</tr>
		
		<tr v-for="l in lekari">
			<td>{{l.ime}}</td>
			<td>{{l.prezime}}</td>
			<td><button v-on:click="deleteDoctor(l.id)" class="btn"><i class="fa fa-trash fa-2x"></i>Obrisi</button></td></tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
		</div>
	
	`, 
	mounted(){
		axios.get("/lekar/dobaviLekare")
		.then(response => {
			this.lekari = response.data;
			this.backupLekari = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		search: function(){
			this.nemaRezultata = '';
			this.lekari = [];
            let lowerPretraga = (this.pretraga).toLowerCase();
            for (let l of this.backupLekari){
            	let imePrezime = (l.ime.concat(" ",l.prezime)).toLowerCase();
            	let prezimeIme = (l.prezime.concat(" ", l.ime)).toLowerCase();
            	if (lowerPretraga.includes(" ")) { //ime i prezime
            		let passedImePrezime = (this.pretraga != '') ? (imePrezime.includes(lowerPretraga) || imePrezime === lowerPretraga) : true;
                    let passedPrezimeIme = (this.pretraga != '') ? (prezimeIme.includes(lowerPretraga) || prezimeIme === lowerPretraga) : true;
                    if (passedImePrezime || passedPrezimeIme ) this.lekari.push(l);
            	}
            	else { //ili ime ili prezime
            		let passedIme = (this.pretraga != '') ? (l.ime.toLowerCase().includes(lowerPretraga)) : true;
                    let passedPrezime = (this.pretraga != '') ? (l.prezime.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedIme  || passedPrezime) this.lekari.push(l);
            	}
                                
            }
            if (this.lekari.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		deleteDoctor: function(id) {
			axios.delete("/lekar/brisanje/" + id, id)
			.then(response => {
				alert("Lekar " + id + " uspesno obrisan!");
				this.$router.push("/adminKlinikeHome");
			})
			.catch(error => {
				alert("Lekar ne postoji u bazi podataka!");
			});

		}
		
	},
	
});
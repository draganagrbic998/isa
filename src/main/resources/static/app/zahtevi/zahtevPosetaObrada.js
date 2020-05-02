Vue.component("zahtevPosetaObrada", {
	
	data: function(){
		return{
			sale: {},
			backup: {},
			zahtevi: {},
			backup: {},
			zahtevSelected: {},
			salaSelected: {},
			selectedSala: false,
			selectedZahtev: false,
			trebaSlobodan: false,
			pretraga: '',
			datum: '',
			vreme: '',
			slobodan: {},
			greska: false,
			greskaDatum: '',
			greskaVreme: '',
			kalendarZauzetosti: {},
			nemaRezultata: ''
		}
	}, 

	template: `
	
	<div>
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a v-if="selectedZahtev==false && selectedSala==false" class="navbar-brand" href="#">ZAHTEVI ZA POSETU</a>
  <a v-else-if="selectedZahtev" class="navbar-brand" href="#">PRETRAGA SALA</a>
  <a v-else class="navbar-brand" href="#">REZERVACIJA SALE</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminHome">
          <i class="fa fa-home"></i>
          Pocetna stranica
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <button v-if="selectedSala" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="rezervisiPredlog()">>Predlog: {{formatiraj(slobodan)}}</button>
      </ul>
       <form class="form-inline my-2 my-lg-0">
      <input v-if="selectedZahtev" class="form-control mr-sm-2" type="text" placeholder="Unesite naziv/broj sale" aria-label="Search" v-model="pretraga">
      <button v-if="selectedZahtev" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
      
      
      <input v-if="selectedSala" class="form-control mr-sm-2" type="text" v-model="vreme" placeholder="Unesite vreme" aria-label="Search">
      <input v-if="selectedSala" class="form-control mr-sm-2" type="date" v-model="datum" placeholder="Odaberite datum" aria-label="Search">
      {{greskaDatum}}
      <button v-if="selectedSala" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="reserve()">>Rezervisi</button>
      
    </form>
  </div>
</nav>
	<div v-if="selectedZahtev==false && selectedSala==false" >
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th>Lekar</th>
			<th>Pacijent</th>
			<th>Naziv pregleda</th>
			<th>Tip pregleda</th>
			<th>Datum i vreme</th>
		</tr>
		
		<tr v-for="z in zahtevi" >
			<td> {{z.lekar}} </td>
			<td> {{z.pacijent}} </td>
			<td> {{z.naziv}} </td>
			<td v-if="z.pregled">Pregled</td>
			<td v-else>Operacija</td>
			<td> {{formatiraj(z.datum)}} </td> 
			<td v-if="zahtevi.length != 0"> <button v-on:click="selektovanZahtev(z)" class="btn"><i class="fa fa-ticket"></i>Rezervisi</button></td></tr>
		</table>	
	</div>
		<div v-else-if="selectedZahtev">
			
		<table class="table">
		<tr bgcolor="#f2f2f2">
			<th>Naziv </th>
			<th> Broj </th>
		</tr>
		
		<tr v-for="s in sale">
			<td>{{s.naziv}}</td>
			<td>{{s.broj}}</td>
			<td><button v-on:click="detaljiSala(s)" class="btn"><i class="fa fa-ticket"></i>REZERVISI</button></td></tr>
		</table>	
		<h3>{{nemaRezultata}}</h3>
		</div>
		
		<div v-else-if="selectedSala">
			<table class="table">
				<tr bgcolor="#f2f2f2">
				<th>Pocetak</th>
				<th>Kraj</th>
			</tr>
			
			<tr v-for="d in salaSelected.kalendar">
				<td>{{formatiraj(d.pocetak)}}</td>
				<td>{{formatiraj(d.kraj)}}</td></tr>
			</table>	
		
		</div>
	</div>
	`, 
	
	mounted(){

		axios.get("/zahtevPoseta/klinika/pregled")
		.then(response => {
			this.zahtevi = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sale = response.data;
			this.backup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	watch: {
		trebaSlobodan : function(){
			if (this.trebaSlobodan) {
				this.trebaSlobodan = false;
				this.nadjiTermin();
			}	
		},
		
	},
	methods: {
		rezervisiPredlog : function() {
			this.zahtevSelected.datum = this.slobodan;
			this.posaljiZahtev(); 
		
		},
		
		vremePromena: function() {

			if (!this.vreme.includes(':') && (this.vreme.length==2)) {
				this.vreme = this.vreme.concat(":00"); 
			}
			if (!this.vreme.includes(':') && (this.vreme.length==1)) {
				this.vreme = '0'.concat(this.vreme,":00"); 
			}
			if (this.vreme.includes(':') && (this.vreme.length==5)) {
				this.vreme=this.vreme;
			}
			return this.vreme; 
		},
		
		nadjiTermin: function() {
			axios.get("/sala/admin/SlobodniTermin")
			.then(response => {
				this.slobodan = response.data;
			})
			.catch(response => {
				console.log("greska");
			});
		},
		
		reserve: function() {
			this.osvezi();
			this.vremePromena();
			
			if (this.datum == '') {
				this.greskaDatum = "Odaberite datum!";
				this.greska = true;
			}
			
			if (this.vreme == '') {
				this.greskaVreme = "Morate uneti vreme.";
				this.greska = true;
			}
			
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			
			if (this.greska) {return;}
			this.zahtevSelected.idSale = this.salaSelected.id;
			this.zahtevSelected.datum = this.datum.concat(" ",this.vreme);
			
			this.posaljiZahtev();
		},
		detaljiSala : function(s) {
			this.zahtevSelected.idSale = s.id;
			this.salaSelected = s;	
			this.posaljiZahtev();
		},
		
		posaljiZahtev: function() {
			axios.post("/sala/admin/rezervacijaSale", this.zahtevSelected)
			.then(response => {
				alert("Uspesno rezervisano!");
				this.trebaSlobodan = false;
				this.$router.push("/adminHome");
			})
			.catch(response => {
				this.trebaSlobodan = true;
				//this.salaSelected = s;	
				this.selectedSala = true;
				this.selectedZahtev = false;
			});
		},
		
		selektovanZahtev: function(z) {
			this.zahtevSelected = z;
			this.selectedZahtev = true;
			this.selectedSala = false;
			this.trebaSlobodan = false;
		},
		
		search: function() {
			console.log("pretraga");
			this.sale = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let s of this.backup){
            	let passedNaziv = (this.pretraga != '') ? (s.naziv.toLowerCase().includes(lowerPretraga)) : true;
                let passedBroj = (this.pretraga != '') ? (s.broj.toLowerCase().includes(lowerPretraga)) : true;                    
                if (passedNaziv || passedBroj) this.sale.push(s);
            }
            
            if (this.sale.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		formatiraj: function (date) {
			
			  date = new Date(date);
			  let year = date.getFullYear();
			  let month = (1 + date.getMonth()).toString();
			  month = month.length > 1 ? month : '0' + month;
			  let day = date.getDate().toString();
			  day = day.length > 1 ? day : '0' + day;
			  let hours = date.getHours().toString();
			  hours = hours.length > 1 ? hours : '0' + hours;
			  let minutes = date.getMinutes().toString();
			  minutes = minutes.length > 1 ? minutes : '0' + minutes;
			  return day + '/' + month + '/' + year + " " + hours + ":" + minutes;
			  
		},
		
		osvezi: function(){
			this.greskaDatum = "";
			this.greska = false;
		}
		
	}
		
});
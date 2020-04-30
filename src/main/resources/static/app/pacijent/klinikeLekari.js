Vue.component("klinikeLekari", {
	
	data: function(){
		return{
			klinike: [], 
			klinikeBackup: [], 
			lekariBackup: [], 
			tipovi: [], 
			selectedKlinika: {}, 
			klinikaSelected: false, 
			selectedLekar: {}, 
			lekarSelected: false, 
			tipPregleda: '', 
			datumPregleda: null, 
			lokacijaKlinike: '', 
			ocenaKlinike: 0, 
			imeLekara: '', 
			prezimeLekara: '', 
			ocenaLekara: 0, 
			greskaPretraga: '', 
			pretraga: false, 
			zakazivanje: false, 
			zahtev: {}, 
			imePrezime: '', 
			datum: '',
			id: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav" style="margin-left: 100px;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>    
    
    <ul class="navbar-nav mr-auto" style="margin-left: 150px;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="/#/klinikeLekari" v-on:click="naziv_sort()">
          <i class="fa fa-coffee"></i>
          Naziv 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="/#/klinikeLekari" v-on:click="adresa_sort()">
          <i class="fa fa-globe"></i>
          Adresa 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="/#/klinikeLekari" v-on:click="ocena_sort()">
          <i class="fa fa-star"></i>
          Ocena 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeLekari" v-on:click="refresh()">
          <i class="fa fa-hotel"></i>
          Klinike centra
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
 
  </div>
</nav>
	
	</div>
	
		<div class="row">
		
			<div v-if="zakazivanje" class="card" id="box">
			
				<h1>Detalji zakazivanja</h1><br>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Datum pregleda: </th>
							<td><input type="text" v-model="datum" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Lekar: </th>
							<td><input type="text" v-model="imePrezime" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Tip pregleda: </th>
							<td><input type="text" v-model="tipPregleda" class="form-control" disabled></td>
						</tr>	
						<tr>
							<th scope="row">Cena pregleda: </th>
							<td><input type="text" v-model="selectedKlinika.cena" class="form-control" disabled></td>
						</tr>
						<tr>
							<th scope="row">Trajanje pregleda: </th>
							<td><input type="text" v-model="selectedKlinika.trajanje" class="form-control" disabled></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-primary" v-on:click="zakaziPregled()">ZAKAZI</button></td>
						</tr>
					</tbody>
				
				</table>
			
			</div>
		
			<div v-else-if="lekarSelected" class="row">
			
				<div class="card col" v-bind:id="id">
					
					<h1>Detalji lekara</h1><br>
					
					<table class="table">
					
						<tbody>
						
							<tr>
							<th scope="row">Ime: </th>
							<td><input type="text" v-model="selectedLekar.ime" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Prezime: </th>
							<td><input type="text" v-model="selectedLekar.prezime" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Telefon: </th>
							<td><input type="text" v-model="selectedLekar.telefon" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Email: </th>
							<td><input type="text" v-model="selectedLekar.email" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Ocena: </th>
							<td><input type="text" v-model="selectedLekar.ocena" class="form-control" disabled></td>
						</tr>
						
						</tbody>
					
					</table>
				
				</div>
				
				<div v-if="selectedLekar.satnica.length>0" class="container col" id="satnica">
				
					<h2>Satnica</h2><br>
					
					<table class="table">
					
						<thead>
						
							<th scope="col">Vreme</th>
							<th scope="col">Zakazivanje</th>
						
						</thead>
						
						<tbody>
						
							<tr v-for="s in selectedLekar.satnica">
								
								<td style="min-width: 150px">{{formatiraj(s)}}</td>
								<td colspan="2"><button class="btn btn-primary" v-on:click="zakazi(s)">ZAKAZI</button></td>
								
							</tr>
						
						</tbody>
					
					</table>
					
				
				</div>
			
			</div>
			
			<div v-else-if="klinikaSelected" class="col-md-7">
			
				<div id="details" style="min-width: 900px">
			
				<h2>Detalji klinike</h2>
				
				<span style="margin-right: 40px;">
					
					<tr></tr>
					<tr>
						<th scope="row">Naziv: </th>
						<td><input type="text" v-model="selectedKlinika.naziv" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="row">Adresa: </th>
						<td><input type="text" v-model="selectedKlinika.adresa" class="form-control" disabled></td>
					</tr>
					
				</span>
				<span style="margin-right: 40px">
					<tr>
						<th scope="row">Opis: </th>
					</tr>
					<tr>
						<td rowspan="2"><textarea disabled>{{selectedKlinika.opis}}</textarea></td>
					</tr>
				
				</span>
				<span style="margin-right: 40px">
					<tr></tr>
					<tr>
						<th scope="row">Ocena: </th>
					</tr>
					<tr>
						<td><input type="text" v-model="selectedKlinika.ocena" class="form-control" disabled></td>

					</tr>
				</span>
			
			</div>
			
			<div class="container" id="cosak" style="margin-top: 2%">
			
				<h2>Zaposleni lekari</h2><br>
				
				<table class="table table-hover">
					
					<thead>
						<tr>
							<th scope="col">Ime</th>
							<th scope="col">Prezime</th>
							<th scope="col">Ocena</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="l in selectedKlinika.lekari" v-on:click="selectLekar(l)" v-if="!pretraga || l.satnica.length>0">
							<td>{{l.ime}}</td>
							<td>{{l.prezime}}</td>
							<td>{{l.ocena}}</td>

						</tr>
					
					</tbody>
				</table>
			
			</div>
			
			</div>
		
			<div v-else class="container col-md-7" id="cosak">
				
				<h1>Klinike</h1><br>
				
				<table class="table table-hover">
					
					<thead>
					
						<tr>
							<th scope="col">Naziv</th>
							<th scope="col">Adresa</th>
							<th scope="col">Ocena</th>
							<th v-if="pretraga" scope="col">Trajanje</th>
							<th v-if="pretraga" scope="col">Cena</th>

						</tr>
					</thead>
					
					<tbody>
						
						<tr v-for="k in klinike" v-on:click="selectKlinika(k)">
							<td>{{k.naziv}}</td>
							<td>{{k.adresa}}</td>
							<td>{{k.ocena}}</td>
							<td v-if="pretraga">{{k.trajanje}}</td>
							<td v-if="pretraga">{{k.cena}}</td>

						</tr>
					
					</tbody>
				
				</table>
			
			</div>
			
			<div v-if="!lekarSelected && !zakazivanje" class="card form-group col-md-5" id="pretraga">
			
				<h3>Pretraga</h3>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Tip pregleda: </th>
							<td><select v-model="tipPregleda" class="form-control">
							<option v-for="t in tipovi">
								{{t}}
							</option>
							</select></td>
						</tr>
						<tr>
							<th scope="row">Datum pregleda: </th>
							<td><input type="date" v-model="datumPregleda" class="form-control" onKeyDown="return false"></td>
						</tr>
						<tr>
							<td><button v-on:click="pretrazi()" class="btn btn-primary">PRETRAZI</button></td>
							<td>{{greskaPretraga}}</td>
						</tr>
						<tr v-if="!klinikaSelected">
							<th scope="row">Adresa klinike: </th>
							<td><input type="text" v-model="lokacijaKlinike" class="form-control"></td>
						</tr>
						<tr v-if="!klinikaSelected">
							<th scope="row">Ocena klinike: </th>
							<td><input type="number" v-model="ocenaKlinike" class="form-control" min="0" max="10" onKeyDown="return false"></td>
						</tr>
						<tr v-if="klinikaSelected">
							<th scope="row">Ime lekara: </th>
							<td><input type="text" v-model="imeLekara" class="form-control"></td>
							
						</tr>
						<tr v-if="klinikaSelected">
						
							<th scope="row">Prezime lekara: </th>
							<td><input type="text" v-model="prezimeLekara" class="form-control"></td>
						</tr>
						<tr v-if="klinikaSelected">
							<th scope="row">Ocena lekara: </th>
							<td><input type="number" v-model="ocenaLekara" class="form-control" min="0" max="10" onKeyDown="return false"></td>

						</tr>
						<tr>
							<td colspan="2" v-if="!lekarSelected"><button v-on:click="filtriraj()" class="btn btn-primary">FILTIRAJ</button></td>
						</tr>
					</tbody>
				
				</table>
			
			</div>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/klinika/pretraga")
		.then(response => {
			this.klinike = response.data;
			this.klinikeBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/tipPosete/nazivi")
		.then(response => {
			this.tipovi = response.data
		})
		.catch(response => {
			this.$router.push("/");

		});
		
	}, 
	
	methods: {
		
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
			this.greskaPretraga = '';
		},
		
		selectKlinika: function(klinika){
			this.osvezi();
			this.klinikaSelected = true;
			this.lekarSelected = false;
			this.zakazivanje = false;
			this.selectedKlinika = klinika;
			this.lekariBackup = this.selectedKlinika.lekari;
		}, 
		
		selectLekar: function(lekar){
			this.osvezi();
			this.klinikaSelected = false;
			this.lekarSelected = true;
			this.zakazivanje = false;
			this.selectedLekar = lekar;
			this.id = this.selectedLekar.satnica.length == 0 ? "centar" : "levo";
			
		}, 
		
		pretrazi: function(){
			
			this.osvezi();
			
			if (this.tipPregelda == '' || this.datumPregleda == null){
				this.greskaPretraga = "Unesite tip pregleda i datum. ";
				return;
			}
			if (new Date(this.datumPregleda) <= new Date()){
				this.greskaPretraga = "Datum mora biti veci od trenutnog. ";
				return;
			}
			
			axios.post("/klinika/pretraga", {"tipPregleda": this.tipPregleda, "datumPregleda": this.datumPregleda})
			.then(response => {
				
				this.klinike = response.data;
				this.klinikeBackup = response.data;
				this.pretraga = true;
				let found = false;
				
				for (let i of this.klinike){
					if (i.id == this.selectedKlinika.id){
						this.selectedKlinika = i;
						this.lekariBackup = i.lekari;
						found = true;
						break;
					}
				}
				if (!found){
					this.selectedKlinika.lekari = [];
					this.lekariBackup = [];
				}
				
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},
		
		filtriraj: function(){
			
			if (!this.klinikaSelected){
				this.klinike = [];
				for (let i of this.klinikeBackup){
					if ((i.adresa == this.lokacijaKlinike || this.lokacijaKlinike == '') && i.ocena >= this.ocenaKlinike)
						this.klinike.push(i);
				}
			}
			else {
				this.selectedKlinika.lekari = [];
				for (let i of this.lekariBackup){
					if ((i.ime == this.imeLekara || this.imeLekara == '') && (i.prezime == this.prezimeLekara || this.prezimeLekara == '') && i.ocena >= this.ocenaLekara)
					this.selectedKlinika.lekari.push(i);
				}
			}
			
		}, 
		
		zakazi: function(vreme){
			this.klinikaSelected = false;
			this.lekarSelected = false;
			this.zakazivanje = true;
			this.zahtev = {"lekar": this.selectedLekar.id, "datum": vreme, "id": null};
			this.imePrezime = this.selectedLekar.ime + " " + this.selectedLekar.prezime;
			this.datum = this.formatiraj(this.zahtev.datum);
		}, 
		
		zakaziPregled: function(){
			
			axios.post("/zahtevPoseta/kreiranje", this.zahtev)
			.then(response => {
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}, 
		
		refresh: function(){
			location.reload();
		}, 
		
		naziv_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].naziv > lista[i].naziv) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		}, 
		
		adresa_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].adresa > lista[i].adresa) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		}, 
		
		ocena_sort(){
			let lista = this.klinike;
			this.klinike = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].ocena < lista[i].ocena) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.klinike = lista;
		}
		
	}, 
	
});
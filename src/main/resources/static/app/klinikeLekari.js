Vue.component("klinikeLekari", {
	
	data: function(){
		return{
			klinike: [], 
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
			greskaPretraga: '', 
			pretraga: false, 
			zakazivanje: false, 
			klinikeBackup: [], 
			lekariBackup: [], 
			zahtev: {}, 
			id: '', 
			imePrezime: '', 
			ocenaLekara: 0, 
			tipovi: []
		}
	}, 
	
	template: `
	
		<div class="form-row">
		
			<div v-if="zakazivanje" class="card" id="box">
			
				<h1>Detalji zakazivanja</h1><br>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Datum pregleda: </th>
							<td><input type="text" v-model="zahtev.datum" class="form-control" disabled></td>
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
					
					<h1>Detalji lekara</h1>
					
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
							<th scope="row">Email: </th>
							<td><input type="text" v-model="selectedLekar.email" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Telefon: </th>
							<td><input type="text" v-model="selectedLekar.telefon" class="form-control" disabled></td>
						</tr>
					
						<tr>
						
							<th scope="row">Ocena: </th>
							<td><input type="text" v-model="selectedLekar.ocena" class="form-control" disabled></td>
						</tr>
						
						</tbody>
					
					</table>
				
				</div>
				
				<div class="container col" v-if="selectedLekar.satnica.length>0" id="satnica">
				
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
			
			<div v-else-if="klinikaSelected">
			
				<div id="details">
			
				<h2>Detalji klinike</h2>
				
				<span style="margin-right: 40px">
					
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
						<td rowspan="2"><textarea disabled id="slobodno">{{selectedKlinika.opis}}</textarea></td>
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
			
			<div class="container" style="margin-left: 30px; margin-top: 30px;">
			
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
		
			<div v-else class="container form-group col-md-7" style="margin-left: 20px; margin-top: 20px;">
				
				<h1>Klinike</h1>
				
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
			
			<div class="card form-group col-md-5" id="pretraga" v-if="!lekarSelected">
			
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
							<th scope="row">Lokacija klinike: </th>
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
	
	`, 
	
	mounted(){
		
		axios.get("/klinika/pretraga")
		.then(response => {
			this.klinike = response.data;
			this.klinikeBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/profil");
		});
		
		axios.get("/tipPosete/svi/nazivi")
		.then(response => {
			this.tipovi = response.data
		})
		.catch(response => {
			this.$router.push("/profil");

		});
		
	}, 
	
	methods: {
		
		osvezi: function(){
			this.greskaPretraga = '';
		},
		
		selectKlinika: function(klinika){
			this.osvezi();
			this.klinikaSelected = true;
			this.selectedKlinika = klinika;
			this.lekariBackup = this.selectedKlinika.lekari;
		}, 
		
		selectLekar: function(lekar){
			this.osvezi();
			this.selectedLekar = lekar;
			this.lekarSelected = true;
			this.klinikaSelected = false;
			this.id = this.selectedLekar.satnica.length == 0 ? "centar" : "levo";
			
		}, 
		
		pretrazi: function(){
			
			this.osvezi();
			
			if (this.tipPregelda == '' || this.datumPregleda == null){
				this.greskaPretraga = "Unesite tip pregleda i datum. ";
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
		
		zakazi: function(vreme){
			this.zakazivanje = true;
			this.zahtev = {"lekar": this.selectedLekar.id, "datum": vreme, "id": null};
			//uradi tooglovanje ostalih flegova
			this.imePrezime = this.selectedLekar.ime + " " + this.selectedLekar.prezime;

		}, 
		
		zakaziPregled: function(){
			
			axios.post("/pacijent/individualan/termin", this.zahtev)
			.then(response => {
				location.reload();
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
			else{
				this.selectedKlinika.lekari = [];
				for (let i of this.lekariBackup){
					if ((i.ime == this.imeLekara || this.imeLekara == '') && (i.prezime == this.prezimeLekara || this.prezimeLekara == '') && i.ocena >= this.ocenaLekara)
					this.selectedKlinika.lekari.push(i);
				}
			}
			
		}, 
		
		formatiraj: function (date) {
			  date = new Date(date);
			  var year = date.getFullYear();

			  var month = (1 + date.getMonth()).toString();
			  month = month.length > 1 ? month : '0' + month;

			  var day = date.getDate().toString();
			  day = day.length > 1 ? day : '0' + day;
			  var hours = date.getHours().toString();
			  var minutes = date.getMinutes().toString();
			  hours = hours.length > 1 ? hours : '0' + hours;
			  minutes = minutes.length > 1 ? minutes : '0' + minutes;
			  return month + '/' + day + '/' + year + " " + hours + ":" + minutes;
		}
		
	}, 
	
});
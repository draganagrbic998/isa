Vue.component("bolesti", {
	
	data: function(){
		return{
			bolesti: [], 
			selectedBolest: {}, 
			selected: false, 
			klinikaOcena: 0, 
			selectedLekar: {}, 
			lekarSelected: false, 
			lekarOcena: 0, 
			datum: ''
		}
	}, 
	
	template: `
	
		<div class="card" v-if="lekarSelected" id="box">
		
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
					
					<tr>
						<th scope="row"><button class="btn btn-primary" v-on:click="oceniLekar()">OCENI</button></th>
						<td><input type="number" min="0" max="10" v-model="lekarOcena" class="form-control" disable onKeyDown="return false"></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
		<div class="form-row" v-else-if="selected">
		
			<div class="card" style="width: 30rem;" id="left">
			
				<h1>Detalji bolesti</h1><br>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Klinika: </th>
							<td><input type="text" v-model="selectedBolest.klinika" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Ocena: </th>
							<td><input type="text" v-model="selectedBolest.ocena" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Datum: </th>
							<td><input type="text" v-model="datum" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Tip posete: </th>
							<td><input type="text" v-model="selectedBolest.tipPosete" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Naziv posete: </th>
							<td><input type="text" v-model="selectedBolest.nazivPosete" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row"><button class="btn btn-primary" v-on:click="oceniKlinika()">OCENI</button></th>
							<td><input type="number" min="0" max="10" v-model="klinikaOcena" class="form-control" disable onKeyDown="return false"></td>
						</tr>
					
					</tbody>
				
				</table>
				
				<label style="font-size: 25px">Izvestaj</label>
				<textarea disabled>{{selectedBolest.izvestaj}}</textarea>
			
			</div>
			
			<div class="form-group col-md-5" style="margin-top: 3%">
				
				<h1>Lekari</h1><br>
				
				
				<table class="table table-hover">
					
					<thead>
						<tr>
							<th scope="col">Ime</th>
							<th scope="col">Prezime</th>
							<th scope="col">Ocena</th>
						</tr>
					
					</thead>
					
					<tbody>
						
						<tr v-for="l in selectedBolest.lekari" v-on:click="selectLekar(l)">
							<td>{{l.ime}}</td>
							<td>{{l.prezime}}</td>
							<td>{{l.ocena}}</td>
						</tr>
					
					</tbody>
				
				</table>
				
				<h1>Dijagnoze</h1><br>
				
				<table class="table">
				
					<thead>
					
						<tr>
							<th scope="col">Sifra</th>
							<th scope="col">Naziv</th>
						</tr>
					
					</thead>
					
					<tbody>
					
						<tr v-for="d in selectedBolest.dijagnoze">
							<td>{{d.sifra}}</td>
							<td>{{d.naziv}}</td>
						</tr>
					
					</tbody>
				
				</table><br>
				
				<h1>Recepti</h1><br>
				
				<table class="table">
				
					<thead>
					
						<tr>
						
							<th scope="col">Sifra</th>
							<th scope="col">Naziv</th>
							<th scope="col">Sestra</th>
						
						</tr>
					
					</thead>
					
					<tbody>
					
						<tr v-for="r in selectedBolest.recepti">
						
							<td>{{r.sifra}}</td>
							<td>{{r.naziv}}</td>
							<td>{{r.sestra}}</td>

						</tr>
					
					</tbody>
				
				</table>
			
			</div>
			
		
		</div>
	
		<div v-else class="container">
		
			<h1>Istorija bolesti</h1>
			
			<table class="table table-hover">
			
				<thead>
				
					<tr>
					
						<th scope="col">Datum</th>
						<th scope="col">Tip posete</th>
						<th scope="col">Naziv posete</th>
						<th scope="col">Broj dijagnoza</th>
						<th scope="col">Broj recepata</th>

					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="b in bolesti" v-on:click="selectBolest(b)">
					
						<td>{{formatiraj(b.datum)}}</td>
						<td>{{b.tipPosete}}</td>
						<td>{{b.nazivPosete}}</td>
						<td>{{b.dijagnoze.length}}</td>
						<td>{{b.recepti.length}}</td>

					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	mounted(){

		axios.get("/pacijent/bolesti")
		.then(response => {
			this.bolesti = response.data;
		})
		.catch(response => {
			this.$router.push("/profil");
		});
		
	}, 
	
	methods: {
		
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
		},
		
		selectBolest: function(bolest){
			this.selectedBolest = bolest;
			this.selected = true;
			this.datum = this.formatiraj(this.selectedBolest.datum);
		}, 
		
		oceniLekar: function(){
			
			axios.post("/lekar/ocenjivanje/" + this.selectedBolest.id, {"id": this.selectedLekar.id, "ocena": this.lekarOcena})
			.then(response => {
				this.selectedBolest = response.data;
				this.selected = true;
				this.lekarSelected = false;
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},
		
		oceniKlinika: function(){
			
			axios.post("/klinika/ocenjivanje/" + this.selectedBolest.id, {"id": this.selectedBolest.klinikaId, "ocena": this.klinikaOcena})
			.then(response => {
				this.selectedBolest = response.data;
				this.selected = true;
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},
		
		selectLekar: function(lekar){
			
			this.selectedLekar = lekar;
			this.lekarSelected = true;
			
		}
	}
	
});
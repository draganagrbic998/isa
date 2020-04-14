Vue.component("bolesti", {
	
	data: function(){
		return{
			bolesti: [], 
			selectedBolest: {}, 
			selected: false
		}
	}, 
	
	template: `
	
		<div class="form-row" v-if="selected">
		
			<div class="card" style="width: 30rem; height: 40rem;" id="left">
			
				<h1>Detalji bolesti</h1><br>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Klinika: </th>
							<td><input type="text" v-model="selectedBolest.klinika" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Datum: </th>
							<td><input type="text" v-model="selectedBolest.datum" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Tip posete: </th>
							<td><input type="text" v-model="selectedBolest.tipPosete" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row">Naziv posete: </th>
							<td><input type="text" v-model="selectedBolest.nazivPosete" class="form-control" disabled></td>
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
						</tr>
					
					</thead>
					
					<tbody>
						
						<tr v-for="l in selectedBolest.lekari" v-on:click="selectLekar(l)">
							<td>{{l.ime}}</td>
							<td>{{l.prezime}}</td>

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
					
						<td>{{b.datum}}</td>
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
		selectBolest: function(bolest){
			this.selectedBolest = bolest;
			this.selected = true;
		}
	}
	
});
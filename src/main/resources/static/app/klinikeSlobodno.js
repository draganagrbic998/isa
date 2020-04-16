Vue.component("klinikeSlobodno", {
	
	data: function(){
		return{
			klinike: [], 
			selectedKlinika: {}, 
			klinikaSelected: false, 
			selectedPoseta: {}, 
			posetaSelected: false
		}
	}, 
	
	template: `
	
		<div v-if="posetaSelected" class="card" id="box">
		
			<h1>Detalji posete</h1><br>
			
			<table class="table">
				
				<tbody>
					<tr>
						<th scope="col">Naziv: </th>
						<td><input type="text" v-model="selectedPoseta.naziv" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Datum: </th>
						<td><input type="text" v-model="selectedPoseta.datum" class="form-control" disabled></td>
					</td>
					<tr>
						<th scope="col">Originalna cena: </th>
						<td><input type="text" v-model="selectedPoseta.cena" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Popust: </th>
						<td><input type="text" v-model="selectedPoseta.popust" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Sala: </th>
						<td><input type="text" v-model="selectedPoseta.sala" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Lekari: </th>
						<td><select class="form-control" v-bind:size="selectedPoseta.lekari.length" disabled multiple>
							<option v-for="l in selectedPoseta.lekari">
								{{l}}
							</option>
						</select></td>
					</tr>
					<tr>	
						<td colspan="2"><button class="btn btn-primary" v-on:click="zakazi()">ZAKAZI</button></td>
					</tr>	
				</tbody>
			
			</table>
		
		</div>
	
		<div v-else-if="klinikaSelected" class="form-row">
		
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
			
			<div class="form-group col-md-7" style="margin-left: 30px; margin-top: 30px">
			
				<h2>Slobodni termini</h2><br>
				
				<table class="table table-hover">
				
					<thead>
						<tr>
							<th scope="col">Datum</th>
							<th scope="col">Naziv</th>
							<th scope="col">Popust</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="p in selectedKlinika.posete" v-on:click="selectPoseta(p)">
							<td>{{p.datum}}</td>
							<td>{{p.naziv}}</td>
							<td>{{p.popust}}</td>

						</tr>
					</tbody>
				</table>
			
			</div>
		
		</div>
	
		<div v-else class="container">
		
			<h1>Klinike</h1>
			
			<table class="table table-hover">
			
				<thead>
					<th scope="col">Naziv</th>
					<th scope="col">Adresa</th>
					<th scope="col">Ocena</th>
				</thead>
				
				<tbody>
					<tr v-for="k in klinike" v-on:click="selectKlinika(k)">
						<td>{{k.naziv}}</td>
						<td>{{k.adresa}}</td>
						<td>{{k.ocena}}</td>
					</tr>
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	
	mounted(){
		
		axios.get("/klinika/slobodno")
		.then(response => {
			this.klinike = response.data;
		})
		.catch(response => {
			this.$router.push("/profil");
		});
		
	}, 
	
	methods: {
		
		
		
		selectKlinika: function(klinika){
			this.selectedKlinika = klinika;
			this.klinikaSelected = true;
		}, 
		
		selectPoseta: function(poseta){
			this.posetaSelected = true;
			this.selectedPoseta = poseta;
		}, 
		
		zakazi: function(){
			
			axios.get("/klinika/zakazi/" + this.selectedPoseta.id)
			.then(response => {
				this.selectedKlinika = response.data;
				this.klinikaSelected = true;
				this.posetaSelected = false;
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
	}
	
});
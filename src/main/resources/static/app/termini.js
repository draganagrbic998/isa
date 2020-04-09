Vue.component("termini", {
	
	data: function(){
		return{
			termini: [], 
			selectedTermin: {}, 
			selected: false
		}
	}, 
	
	template: `
	
		<div class="well" id="box" v-if="selected">
		
			<h1>Detalji termina</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
				
					<th scope="row">Datum: </th>
					<td><input type="text" v-model="selectedTermin.pocetak" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Klinika: </th>
					<td><input type="text" v-model="selectedTermin.klinika" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Adresa: </th>
					<td><input type="text" v-model="selectedTermin.adresa" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Originalna cena: </th>
					<td><input type="text" v-model="selectedTermin.originalnaCena" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Popust: </th>
					<td><input type="text" v-model="selectedTermin.popust" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Naziv termina: </th>
					<td><input type="text" v-model="selectedTermin.nazivPosete" class="form-control" disabled></td>
					
				
				</tr>
				
				<tr>
				
					<th scope="row">Sala: </th>
					<td><input type="text" v-model="selectedTermin.sala" class="form-control" disabled></td>
					
				
				</tr>
				
				
				
				<tr>
				
					<th scope="row">Lekari: </th>
					<td><select class="form-control"  v-bind:size="selectedTermin.lekari.length" disabled multiple>
					
						<option v-for="l in selectedTermin.lekari">
							{{l}}
						</option>
					
					</select>
					</td>
				
				</tr>
				<tr>
				
					<td colspan="2"><button v-on:click="otkazi()" class="btn btn-primary">OTKAZI</button></td>
				
				</tr>
				</tbody>
			
			</table>
			
		
		</div>
	
		<div v-else class="container">
		
			<h1>Zakazani termini</h1>
			
			<table class="table table-hover">
			
				<thead>
					
					<tr>
						<th scope="col">Datum</th>
						<th scope="col">Klinika</th>
						<th scope="col">Vrsta termina</th>
						<th scope="col">Cena</th>
					
					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="t in termini" v-on:click="selectTermin(t)">
				
						<td>{{t.pocetak}}</td>
						<td>{{t.klinika}}</td>
						<td>{{t.tipPosete}}</td>
						<td>{{t.novaCena}}</td>

					</tr>
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/termini")
		.then(response => {
			this.termini = response.data;
		})
		.catch(response => {
			this.$router.push("/profil");
		});
		
	}, 
	
	methods: {
		selectTermin: function(termin){
			this.selected = true;
			this.selectedTermin = termin;
		}, 
		
		otkazi: function(){
			axios.delete("/pacijent/otkazi/termin/" + this.selectedTermin.id)
			.then(response => {
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		}
	}
	
});
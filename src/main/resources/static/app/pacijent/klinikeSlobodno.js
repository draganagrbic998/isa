Vue.component("klinikeSlobodno", {
	
	data: function(){
		return{
			klinike: [], 
			selectedKlinika: {}, 
			klinikaSelected: false, 
			selectedPoseta: {}, 
			posetaSelected: false, 
			datum: ''
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
        <a class="nav-link" href="/#/klinikeSlobodno" v-on:click="naziv_sort()">
          <i class="fa fa-coffee"></i>
          Naziv 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="/#/klinikeSlobodno" v-on:click="adresa_sort()">
          <i class="fa fa-globe"></i>
          Adresa 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="/#/klinikeSlobodno" v-on:click="ocena_sort()">
          <i class="fa fa-star"></i>
          Ocena 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul> 
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeSlobodno" v-on:click="refresh()">
          <i class="fa fa-bell"></i>
          Povoljni termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>

  </div>
</nav>		
		</div>
	
		<div v-if="posetaSelected" class="card" id="box">
		
			<h1>Detalji posete</h1><br>
			
			<table class="table">
				
				<tbody>
					<tr>
						<th scope="col">Datum: </th>
						<td><input type="text" v-model="datum" class="form-control" disabled></td>
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
						<th scope="col">Tip pregleda: </th>
						<td><input type="text" v-model="selectedPoseta.naziv" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Trajanje: </th>
						<td><input type="text" v-model="selectedPoseta.trajanje" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Sala: </th>
						<td><input type="text" v-model="selectedPoseta.sala" class="form-control" disabled></td>
					</tr>
					<tr>
						<th scope="col">Lekari: </th>
						<td><select v-bind:size="selectedPoseta.lekari.length" class="form-control" disabled multiple>
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
	
		<div v-else-if="klinikaSelected" class="row">
		
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
			
			<div class="container col-md-8" style="margin-left: 30px; margin-top: 30px">
			
				<h2>Slobodni termini</h2><br>
				
				<table class="table table-hover">
				
					<thead>
						<tr>
							<th scope="col">Datum</th>
							<th scope="col">Tip pregleda</th>
							<th scope="col">Popust</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="p in selectedKlinika.posete" v-on:click="selectPoseta(p)">
							<td>{{formatiraj(p.datum)}}</td>
							<td>{{p.naziv}}</td>
							<td>{{p.popust}}</td>
						</tr>
					</tbody>
				</table>
			
			</div>
		
		</div>
	
		<div v-else class="container" id="cosak">
		
			<h1>Klinike</h1><br>
			
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
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/klinika/slobodno")
		.then(response => {
			this.klinike = response.data;
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
		
		selectKlinika: function(klinika){
			this.selectedKlinika = klinika;
			this.klinikaSelected = true;
			this.posetaSelected = false;
		}, 
		
		selectPoseta: function(poseta){
			this.selectedPoseta = poseta;
			this.posetaSelected = true;
			this.klinikaSelected = false;
			this.datum = this.formatiraj(this.selectedPoseta.datum);
		}, 
		
		zakazi: function(){
			
			axios.get("/poseta/zakazi/" + this.selectedPoseta.id)
			.then(response => {
				this.selectKlinika(response.data);
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
	}
	
});
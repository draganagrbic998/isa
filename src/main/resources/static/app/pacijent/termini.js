Vue.component("termini", {
	
	data: function(){
		return{
			termini: [], 
			terminiBackup: [],
			selectedTermin: {}, 
			selected: false, 
			datum: '', 
			pretraga: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;" v-if="!selected">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;" v-if="selected">
        <a class="nav-link" href="#/termini" v-on:click="refresh()">
          <i class="fa fa-reply"></i>
          Nazad
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
      <form class="form-inline my-2 my-lg-0" v-if="!selected">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretraga" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretraga</button>
    </form>
  </div>
</nav>		
		</div>
	
		<div v-if="selected" class="card" id="box">
		
			<h2>Detalji posete</h2><br>
			
			<table>
			
			<tr>
				<th scope="row" style="width: 40%;">Datum: </th>
				<td><input type="text" v-model="datum" class="form-control" disabled></td>
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
				<td><input type="text" v-model="selectedTermin.cena" class="form-control" disabled></td>
			</tr>
				
			<tr>
				<th scope="row">Popust: </th>
				<td><input type="text" v-model="selectedTermin.popust" class="form-control" disabled></td>
			</tr>
				
			<tr>
				<th scope="row">Naziv posete: </th>
				<td><input type="text" v-model="selectedTermin.naziv" class="form-control" disabled></td>
			</tr>
					
			<tr>
				<th scope="row">Trajanje posete: </th>
				<td><input type="text" v-model="selectedTermin.trajanje" class="form-control" disabled></td>
			</tr>
				
			<tr>
				<th scope="row">Sala: </th>
				<td><input type="text" v-model="selectedTermin.sala" class="form-control" disabled></td>
			</tr>
				
			<tr>
				<th scope="row">Lekari: </th>
				<td><select class="form-control" v-bind:size="selectedTermin.lekari.length" disabled multiple>
					<option v-for="l in selectedTermin.lekari">
						{{l}}
					</option>
				</select></td>
				</tr>
				<br>
				<tr>
				<td><button v-on:click="otkazi()" class="btn btn-outline-success my-2 my-sm-0" v-bind:hidden="new Date().addHours(24) >= new Date(selectedTermin.datum)">OTKAZI</button></td>
			</tr>
			
			</table>
		
		</div>
	
		<div v-else class="container" id="cosak">
		
			<h2>Zakazane posete</h2><br>
			
			<table v-if="termini.length>0" class="table table-hover">
			
				<thead>
					
					<tr>
					
						<th scope="col">Datum</th>
						<th scope="col">Klinika</th>
						<th scope="col">Tip posete</th>
						<th scope="col">Cena</th>
					
					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="t in termini" v-on:click="selectTermin(t)">
				
						<td>{{formatiraj(t.datum)}}</td>
						<td>{{t.klinika}}</td>
						<td>{{t.tipPosete}}</td>
						<td>{{t.novaCena}}</td>

					</tr>
				</tbody>
			
			</table>
			
			<h3 v-if="termini.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/termini")
		.then(response => {
			this.termini = response.data;
			this.terminiBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		Date.prototype.addHours = function(h){
		    this.setHours(this.getHours()+h);
		    return this;
		}
		
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
		
		refresh: function(){
			location.reload();
		}, 
		
		selectTermin: function(termin){
			this.selectedTermin = termin;
			this.selected = true;
			this.datum = this.formatiraj(this.selectedTermin.datum);
		}, 
		
		otkazi: function(){

			let temp = confirm("Da li ste sigurni?");
        	if (!temp) return;

			axios.delete("/poseta/otkazi/" + this.selectedTermin.id)
			.then(response => {
				alert("Poseta uspesno otkazana!");
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		}, 
		
		search: function(){

			this.termini = [];
			let lowerPretraga = this.pretraga.toLowerCase();
			
			for (let t of this.terminiBackup){
				let klinikaPassed = lowerPretraga != '' ? t.klinika.toLowerCase().includes(lowerPretraga) : true;
				let tipPosetePassed = lowerPretraga != '' ? t.tipPosete.toLowerCase().includes(lowerPretraga) : true;
				if (klinikaPassed || tipPosetePassed) this.termini.push(t);
			}
			
		}
		
	}
	
});
Vue.component("termini", {
	
	data: function(){
		return{
			termini: [], 
			selectedTermin: {}, 
			selected: false, 
			datum: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/profil">
          <i class="fa fa-user"></i>
          Profil 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/karton">
          <i class="fa fa-address-book"></i>
          Karton 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/bolesti">
          <i class="fa fa-line-chart"></i>
          Istorija bolesti
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/termini" v-on:click="refresh()">
          <i class="fa fa-calendar"></i>
          Zakazani termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeSlobodno">
          <i class="fa fa-bell"></i>
          Povoljni termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeLekari">
          <i class="fa fa-hotel"></i>
          Klinike centra
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>		
		</div>
	
		<div v-if="selected" class="card" id="box">
		
			<h1>Detalji termina</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Datum: </th>
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
						<th scope="row">Naziv termina: </th>
						<td><input type="text" v-model="selectedTermin.naziv" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Trajanje termina: </th>
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
				
					<tr>
						<td colspan="2"><button v-on:click="otkazi()" class="btn btn-primary" v-bind:hidden="new Date().addHours(24) >= new Date(selectedTermin.datum)">OTKAZI</button></td>
					</tr>
					
				</tbody>
			
			</table>
		
		</div>
	
		<div v-else class="container" id="cosak">
		
			<h1>Zakazani termini</h1><br>
			
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
				
						<td>{{formatiraj(t.datum)}}</td>
						<td>{{t.klinika}}</td>
						<td>{{t.tipPosete}}</td>
						<td>{{t.novaCena}}</td>

					</tr>
				</tbody>
			
			</table>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/termini")
		.then(response => {
			this.termini = response.data;
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
			  return month + '/' + day + '/' + year + " " + hours + ":" + minutes;
			  
		},
		
		
		selectTermin: function(termin){
			this.selectedTermin = termin;
			this.selected = true;
			this.datum = this.formatiraj(this.selectedTermin.datum);
		}, 
		
		otkazi: function(){
			axios.delete("/poseta/otkazi/" + this.selectedTermin.id)
			.then(response => {
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		}, 
		
		refresh: function(){
			location.reload();
		}
		
	}
	
});
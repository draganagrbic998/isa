Vue.component("bolesti", {
	
	data: function(){
		return{
			bolesti: [], 
			selectedBolest: {}, 
			bolestSelected: false, 
			selectedLekar: {}, 
			lekarSelected: false, 
			klinikaOcena: 0, 
			lekarOcena: 0, 
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
        <a class="nav-link" href="#/bolesti" v-on:click="refresh()">
          <i class="fa fa-line-chart"></i>
          Istorija bolesti
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/termini">
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
	
		<div v-if="lekarSelected" class="card" id="box">
		
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
						<th scope="row"><button class="btn btn-primary" v-on:click="oceniLekar()">OCENI LEKARA</button></th>
						<td><input type="number" min="0" max="10" v-model="lekarOcena" class="form-control" disable onKeyDown="return false"></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
		<div v-else-if="bolestSelected" class="row">
		
			<div class="card" id="left">
			
				<h1>Detalji bolesti</h1><br>
				
				<table class="table">
				
					<tbody>
					
						<tr>
							<th scope="row">Datum: </th>
							<td><input type="text" v-model="datum" class="form-control" disabled></td>
						</tr>
					
						<tr>
							<th scope="row">Klinika: </th>
							<td><input type="text" v-model="selectedBolest.klinika" class="form-control" disabled></td>
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
							<th scope="row">Ocena klinike: </th>
							<td><input type="text" v-model="selectedBolest.ocenaKlinike" class="form-control" disabled></td>
						</tr>
						
						<tr>
							<th scope="row"><button class="btn btn-primary" v-on:click="oceniKlinika()">OCENI KLINIKU</button></th>
							<td><input type="number" min="0" max="10" v-model="klinikaOcena" class="form-control" disable onKeyDown="return false"></td>
						</tr>
					
					</tbody>
				
				</table>
				
				<label style="font-size: 25px">Izvestaj</label>
				<textarea disabled>{{selectedBolest.izvestaj}}</textarea>
			
			</div>
			
			<div class="col-md-5" style="margin-top: 3%">
				
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
	
		<div v-else class="container" id="cosak">
		
			<h1>Istorija bolesti</h1><br>
			
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
		
		</div>
	
	`, 
	
	mounted(){

		axios.get("/pacijent/bolesti")
		.then(response => {
			this.bolesti = response.data;
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
			  return month + '/' + day + '/' + year + " " + hours + ":" + minutes;
			  
		},
		
		selectBolest: function(bolest){
			this.selectedBolest = bolest;
			this.bolestSelected = true;
			this.lekarSelected = false;
			this.datum = this.formatiraj(this.selectedBolest.datum);
		}, 
		
		selectLekar: function(lekar){
			this.selectedLekar = lekar;
			this.lekarSelected = true;
			this.bolestSelected = false;
		},
		
		oceniKlinika: function(){
			
			axios.post("/klinika/ocenjivanje/" + this.selectedBolest.posetaId, {"id": this.selectedBolest.klinikaId, "ocena": this.klinikaOcena})
			.then(response => {
				this.selectBolest(response.data);
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},
		
		oceniLekar: function(){
			
			axios.post("/lekar/ocenjivanje/" + this.selectedBolest.posetaId, {"id": this.selectedLekar.id, "ocena": this.lekarOcena})
			.then(response => {
				this.selectBolest(response.data);
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
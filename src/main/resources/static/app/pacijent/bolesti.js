Vue.component("bolesti", {
	
	data: function(){
		return{
			bolesti: [], 
			bolestiBackup: [],
			selectedBolest: {}, 
			bolestSelected: false, 
			selectedLekar: {}, 
			lekarSelected: false, 
			klinikaOcena: 0, 
			lekarOcena: 0, 
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
      <li class="nav-item active" style="min-width: 100px;" v-if="!bolestSelected && !lekarSelected">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;" v-if="bolestSelected || lekarSelected">
        <a class="nav-link" href="#/bolesti" v-on:click="refresh()">
          <i class="fa fa-reply"></i>
          Nazad
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item dropdown" v-if="bolestSelected && !lekarSelected" style="margin-left: 50px;">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-star"></i>
          Oceni kliniku
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown" id="pretraga">
          <form>
         	<table class="table">
         	<tr>
				<td><input type="number" min="0" max="10" v-model="klinikaOcena" class="form-control" disable onKeyDown="return false"></td>
			</tr>
			<tr>
				<th scope="row"><button class="btn btn-outline-success my-2 my-sm-0" v-on:click="oceniKlinika()">OCENI KLINIKU</button></th>
			</tr>
         	</table>
          </form>
        </div>
      </li>
      <li class="nav-item dropdown" v-if="!bolestSelected && lekarSelected" style="margin-left: 50px;">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-star"></i>
          Oceni lekara
          <span class="sr-only">(current)</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown" id="pretraga">
          <form>
         	<table class="table">
         	<tr>
				<td><input type="number" min="0" max="10" v-model="lekarOcena" class="form-control" disable onKeyDown="return false"></td>
			</tr>
         	<tr>
				<th scope="row"><button class="btn btn-outline-success my-2 my-sm-0" v-on:click="oceniLekar()">OCENI LEKARA</button></th>			
			</tr>
         	</table>
          </form>
        </div>
      </li>

    </ul>   
    <form class="form-inline my-2 my-lg-0" v-if="!bolestSelected && !lekarSelected">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretraga" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Pretraga</button>
    </form>
  </div>
</nav>		
		</div>
	
		<div v-if="lekarSelected" class="card" id="box">
		
			<h2>Detalji lekara</h2><br>
			
			<table class="table">
			
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
						<td><input type="text" v-model="selectedLekar.prosecnaOcena" class="form-control" disabled></td>
					</tr>
					
			</table>
		
		</div>
	
		<div v-else-if="bolestSelected" class="container">
		
			<div class="row" id="red">
			
				<div class="col card" id="okvir">
			
				<h2>Detalji bolesti</h2><br>
				
					<table id="rasiri">
				
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
						
				</table><br>
				
				<label style="font-size: 25px">Izvestaj</label>
				<textarea disabled>{{selectedBolest.izvestaj}}</textarea>
			
			</div>
			
			<div class="col" style="overflow: scroll; max-height: 500px;">
				
				<h2>Lekari</h2>
				
				<table class="table table-hover table-bordered">
					
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
							<td>{{l.prosecnaOcena}}</td>
						</tr>
					
					</tbody>
				
				</table>
				
				<h2>Dijagnoze</h2>
				
				<table v-if="selectedBolest.dijagnoze.length>0" class="table table-bordered">
				
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
				
				</table>
				
				<h3 v-if="selectedBolest.dijagnoze.length==0" style="color: #90EE90">
					Nema dijagnoza
				</h3>
				
				<h2>Recepti</h2>
				
				<table v-if="selectedBolest.recepti.length>0" class="table table-bordered">
				
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
				
				<h3 v-if="selectedBolest.recepti.length==0" style="color: #90EE90">
					Nema recepata
				</h3>
			
			</div>
			
			</div>
		
		</div>
	
		<div v-else class="container" id="cosak">
		
			<h2>Istorija bolesti</h2><br>
			
			<table v-if="bolesti.length>0" class="table table-hover">
			
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
			
			<h3 v-if="bolesti.length==0" style="color: #00CED1;">
				Nema rezultata pretrage.
			</h3>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){

		axios.get("/pacijent/bolesti")
		.then(response => {
			this.bolesti = response.data;
			this.bolestiBackup = response.data;
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
		
		refresh: function(){
			if (this.bolestSelected)
				location.reload();
			else
				this.selectBolest(this.selectedBolest);
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
				for (let i of this.selectedBolest.lekari){
					if (i.id == this.selectedBolest.selectedLekar)
						this.selectLekar(i);
				}
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		},  
		
		search: function(){
			this.bolesti = [];
			let lowerPretraga = this.pretraga.toLowerCase();
			
			for (let b of this.bolestiBackup){
				let tipPosetePassed = this.pretraga != '' ? b.tipPosete.toLowerCase().includes(lowerPretraga) : true;
				let nazivPosetePassed = this.pretraga != '' ? b.nazivPosete.toLowerCase().includes(lowerPretraga) : true;
				if (tipPosetePassed || nazivPosetePassed) this.bolesti.push(b);
			}		
			
		}
		
	}
	
});
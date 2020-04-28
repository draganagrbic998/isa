Vue.component("lekarPacijenti", {
	data: function(){
		return{
			pacijenti: {},
			selectedPacijent: {}, 
			selected: false, 
			backup: {},
			pomocna: {},
			nemaRezultata: '',
			pretraga: ''
		}
	}, 

	template: `
<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">LISTA PACIJENATA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		<li class="nav-item active">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
		</li>
		</ul>
  </div>
		
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Sortiraj po
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" @click.prevent="sort_ime()" href="#">imenu</a>
          <a class="dropdown-item" @click.prevent="sort_prezime()" href="#">prezimenu</a>
          <a class="dropdown-item" @click.prevent="sort_email()" href="#">emailu</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>
   </div>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretrazite" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>

</nav>	
		<div v-if="selected" class="row">
		
			<div class="card" id="left">
			
				<h1>Karton</h1><br>
				
				<table class="table">
				
				<tbody>
				
					<tr>
						<th scope="row">Broj osiguranika: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.brojOsiguranika" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Visina: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.visina" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Tezina: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.tezina" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Leva dioptrija: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.levaDioptrija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Desna dioptrija: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.desnaDioptrija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Krvna grupa: </th>
						<td><input type="text" v-model="selectedPacijent.kartonObj.krvnaGrupa" class="form-control" disabled></td>
					</tr>
				</tbody>
			</table>		
		</div>
		
			<div class="col-md-5" style="margin-top: 3%">
				
				<h1>Izvestaji</h1><br>
				
				<table class="table table-hover">
					
					<thead>
						<tr>
							<th scope="col">Opis</th>
							<th scope="col">Terapija</th>
							<th scope="col">Poseta</th>
						</tr>
					
					</thead>
					
					<tbody>
						
						<tr v-for="i in selectedPacijent.stariIzvestaji" >
							<td>{{i.opis}}</td>
							<td>{{i.terapija}}</td>
							<td>{{i.posetaNaziv}}</td>
						</tr>
					
					</tbody>
				
				</table>
				</div>
	</div>
	<div v-else class="row">
		<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Ime </th>
			<th> Prezime </th>
		</tr>
		
		<tr v-for="p in pacijenti" v-on:click="selektovanPacijent(p)">
			<td>{{p.ime}}</td>
			<td>{{p.prezime}}</td>
			
		</tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	</div>
	`, 
	methods: {
		selektovanPacijent: function(p) {
			this.selectedPacijent = p;
			this.selected = true;
		},
		search: function(){
			
			this.pacijenti = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backup){
            	let imePrezime = (l.ime.concat(" ",l.prezime)).toLowerCase();
            	let prezimeIme = (l.prezime.concat(" ", l.ime)).toLowerCase();
            	if (lowerPretraga.includes(" ")) { //ime i prezime
            		let passedImePrezime = (this.pretraga != '') ? (imePrezime.includes(lowerPretraga) || imePrezime === lowerPretraga) : true;
                    let passedPrezimeIme = (this.pretraga != '') ? (prezimeIme.includes(lowerPretraga) || prezimeIme === lowerPretraga) : true;
                    if (passedImePrezime || passedPrezimeIme ) this.pacijenti.push(l);
            	}
            	else { //ili ime ili prezime
            		let passedIme = (this.pretraga != '') ? (l.ime.toLowerCase().includes(lowerPretraga)) : true;
                    let passedPrezime = (this.pretraga != '') ? (l.prezime.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedIme  || passedPrezime) this.pacijenti.push(l);
            	}
                                
            }
            
            if (this.pacijenti.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		osvezi : function() {
			this.nemaRezultata = "";
			this.pretraga = "";
		},
		sort_ime: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].ime < lista[i].ime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}, 
		sort_prezime: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].prezime < lista[i].prezime) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}, 
		sort_email: function(){
			let lista = this.pacijenti;
			this.pacijenti = [];
			for (let i in lista) {
				for (let j in lista) {
					if (lista[j].email < lista[i].email) {
						let temp = lista[j];
						lista[j] = lista[i];
						lista[i] = temp;
					}
				}
			}		
			this.pacijenti = lista;
		}
	},
	
	mounted(){
		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/pacijent/lekar/pacijenti") 
		.then(response => {
			this.pacijenti = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});
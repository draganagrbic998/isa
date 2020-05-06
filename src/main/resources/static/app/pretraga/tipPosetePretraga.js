Vue.component("tipPosetePretraga", {
	
	data: function(){
		return{
			tipoviPregleda: {},
			backup: {},
			tipSelected: {}, 
			selectedCenovnik: false,
			selected: false, 
			brisanjeSelected: false,
			operacija: 'Operacija',
			pregled: 'Pregled',
			pretraga: '',
			greskaSati: '',
			greskaMinuti: '',
			greskaCena: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">PRETRAGA TIPOVA PREGLEDA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminHome">
          <i class="fa fa-home"></i>
          Pocetna stranica
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
      <ul v-if="selected==false" class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" @click.prevent="selektovanCenovnik()" href="#">
          <i class="fa fa-money"></i>
          Cenovnik
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
       <form v-if="selected==false" class="form-inline my-2 my-lg-0">
      <input v-if="selected==false" class="form-control mr-sm-2" type="text" placeholder="Unesite naziv tipa pregleda" aria-label="Search" v-model="pretraga">
      <button v-if="selected==false" class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>
  </div>
</nav>
	<div v-if="selected==false" class="row">
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Naziv </th>
			<th> Tip </th>
		</tr>
		
		<tr v-for="t in tipoviPregleda" >
			<td v-on:click="selektovanTip(t)">{{t.naziv}}</td>
			<td v-if="t.pregled" v-on:click="selektovanTip(t)">pregled</td>
			<td v-else v-on:click="selektovanTip(t)">operacija</td>
			<td v-if="selectedCenovnik" v-on:click="selektovanTip(t)"> {{t.cena}}</td>
			<td><button v-on:click="deleteTipPosete(t.id)" class="btn"><i class="fa fa-trash "></i>Obrisi</button></td></tr>
	
	</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	<div v-else-if="brisanjeSelected==false && selected">
		<div class="card" id="left">
			
				<h1>Tip posete</h1><br>
				
				<table class="table">
				
				<tbody>
					<tr>
						<th scope="row">Naziv: </th>
						<td colspan="2"><input type="text" v-model="tipSelected.naziv" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Tip: </th>
						<td v-if="tipSelected.pregled"><input type="text" v-model="pregled" class="form-control" disabled></td>
						<td v-else> <input type="text" v-model="operacija" class="form-control" disabled></td>
					</tr>
					
					<tr>
						<th scope="row">Sati: </th>
						<td><input type="number" min="0" v-model="tipSelected.sati" class="form-control"></td>
						<td>{{greskaSati}}</td>
					</tr>
					
					<tr>
						<th scope="row">Minuti: </th>
						<td><input type="number" min="30" v-model="tipSelected.minute" class="form-control"></td>
						<td>{{greskaMinuti}}</td>
					</tr>
					
					<tr>
						<th scope="row">Cena: </th>
						<td><input type="number" min="1000" v-model="tipSelected.cena" class="form-control"></td>
						<td>{{greskaCena}}</td>
					</tr>
					<tr>
					<td colspan="3">
						<button class="btn btn-outline-success my-2 my-sm-0" v-on:click="izmeni()">Izmeni</button>
					</td>
					</tr>
				</tbody>
			</table>		
		</div>
	</div>
	</div>
	
	`, 
	
	mounted(){
		
		axios.get("/tipPosete/admin/pregled")
		.then(response => {
			this.tipoviPregleda = response.data;
			this.backup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		osvezi: function() {
			greskaSati = '',
			greskaMinuti = '',
			greskaCena = '',
			this.greska = false;
		},
		izmeni: function() {
			this.osvezi();
			if (this.tipSelected.sati==''){
				this.greskaSati = "Unesite broj sati";
				this.greska = true;
			}
			if (this.tipSelected.minute==''){
				this.greskaMinuti = "Unesite broj minuta";
				this.greska = true;
			}
			if (this.tipSelected.cena ==''){
				this.greskaCena= "Unesite cenu";
				this.greska = true;
			}
			if (this.greska){return;}
			axios.post("/tipPosete/izmena", this.tipSelected)
			.then(response => {
				alert('Izmene uspesno sacuvane!');
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		},
		selektovanTip: function(t) {
			this.tipSelected = t;
			this.selected = true;
		},
		
		selektovanCenovnik: function() {
			this.selectedCenovnik = true;
			
		},
		
		
		search: function(){
			
			this.tipoviPregleda = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let l of this.backup){
        		let passed = (this.pretraga != '') ? (l.naziv.toLowerCase().includes(lowerPretraga)) : true;
                if (passed) this.tipoviPregleda.push(l);            	
            }
            
            if (this.tipoviPregleda.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteTipPosete: function(id) {
			axios.delete("/tipPosete/brisanje/" + id)
			.then(response => {
				alert("Tip posete uspesno obrisan!");
				location.reload();
			})
			.catch(error => {
				alert("Postoje zakazane posete sa odabranim tipom i nije moguce brisanje!");
				location.reload();
			});
		}
		
	},
	
});
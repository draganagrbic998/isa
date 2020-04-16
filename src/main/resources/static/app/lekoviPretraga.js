Vue.component('lekoviPretraga', {
	
	data: function(){
		return{
			lekovi: [],
			backupLekovi: [],
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/lekoviPretraga">PRETRAGA LEKOVA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminKCHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/lekoviPretraga" v-on:click="sifra_sort()">
          <i class="fa fa-address-book">
          </i>
          Sifra
        </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/lekoviPretraga" v-on:click="naziv_sort()">
          <i class="fa fa-address-book">
          </i>
          Naziv
        </a>
      </li>
    </ul>
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Search</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Sifra </th>
			<th> Naziv </th>
		</tr>
		
		<tr v-for="l in lekovi">
			<td>{{l.sifra}}</td>
			<td>{{l.naziv}}</td>
			<td><button v-on:click="deleteLek(l.id, l.naziv)" class="btn"><i class="fa fa-trash fa-2x"></i>Obrisi</button></td></tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/lek/pregled")
		.then(response => {
			this.lekovi = response.data;
			this.backupLekovi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		sifra_sort: function(){
			for (let i in this.lekovi) {
				for (let j in this.lekovi) {
					if (this.lekovi[j].sifra > this.lekovi[i].sifra) {
						let temp = this.lekovi[j];
						this.lekovi[j] = this.lekovi[i];
						this.lekovi[i] = temp;
					}
				}
			}
		},
		
		naziv_sort: function(){
			for (let i in this.lekovi) {
				for (let j in this.lekovi) {
					if (this.lekovi[j].naziv > this.lekovi[i].naziv) {
						let temp = this.lekovi[j];
						this.lekovi[j] = this.lekovi[i];
						this.lekovi[i] = temp;
					}
				}
			}
		},
		
		search: function(){
			
			this.nemaRezultata = '';
			this.lekovi = [];
            let lowerPretraga = this.pretraga.toLowerCase();
            
            for (let l of this.backupLekovi){
            	
            	let sifraNaziv = (l.sifra.concat(" ",l.naziv)).toLowerCase();
            	let nazivSifra = (l.naziv.concat(" ", l.sifra)).toLowerCase();
            	
            	if (lowerPretraga.includes(" ")) { //sifra i naziv
            		let passedSifraNaziv = (this.pretraga != '') ? (sifraNaziv.includes(lowerPretraga) || sifraNaziv === lowerPretraga) : true;
                    let passedNazivSifra = (this.pretraga != '') ? (nazivSifra.includes(lowerPretraga) || nazivSifra === lowerPretraga) : true;
                    if (passedSifraNaziv || passedNazivSifra ) this.lekovi.push(l);
            	}
            	
            	else { //ili sifra ili naziv
            		let passedSifra = (this.pretraga != '') ? (l.sifra.toLowerCase().includes(lowerPretraga)) : true;
                    let passedNaziv = (this.pretraga != '') ? (l.naziv.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedSifra  || passedNaziv) this.lekovi.push(l);
            	}
                                
            }
            
            if (this.lekovi.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteLek: function(id, naziv) {
			
			axios.delete("/lek/brisanje/" + id)
			.then(response => {
				alert("Lek '" + naziv + "' uspesno obrisan!");
				this.$router.push("/adminKCHome");
			})
			
			.catch(error => {
				alert("Lek je u upotrebi i ne moze biti obrisan!");
			});
		}
		
	},
	
});
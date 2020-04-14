Vue.component('dijagnozePretraga', {
	data: function(){
		return{
			dijagnoze: [],
			pretraga: '',
			backupDijagnoze: [],
			nemaRezultata: ''
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/dijagnozePretraga">PRETRAGA DIJAGNOZA</a>
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
        <a class="nav-link" href="#/dijagnozePretraga" v-on:click="sifra_sort()">
          <i class="fa fa-address-book">
          </i>
          Sifra
        </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/dijagnozePretraga" v-on:click="naziv_sort()">
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
		
		<tr v-for="d in dijagnoze">
			<td>{{d.sifra}}</td>
			<td>{{d.naziv}}</td>
			<td><button v-on:click="deleteDijagnoza(d.id, d.naziv)" class="btn"><i class="fa fa-trash fa-2x"></i>Obrisi</button></td></tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
		</div>
	
	`, 
	mounted(){
		axios.get("/dijagnoza/dobaviDijagnoze")
		.then(response => {
			this.dijagnoze = response.data;
			this.backupDijagnoze = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		sifra_sort: function(){
			for (let i in this.dijagnoze) {
				for (let j in this.dijagnoze) {
					if (this.dijagnoze[j].sifra > this.dijagnoze[i].sifra) {
						let temp = this.dijagnoze[j];
						this.dijagnoze[j] = this.dijagnoze[i];
						this.dijagnoze[i] = temp;
					}
				}
			}
		},
		
		naziv_sort: function(){
			for (let i in this.dijagnoze) {
				for (let j in this.dijagnoze) {
					if (this.dijagnoze[j].naziv > this.dijagnoze[i].naziv) {
						let temp = this.dijagnoze[j];
						this.dijagnoze[j] = this.dijagnoze[i];
						this.dijagnoze[i] = temp;
					}
				}
			}
		},
		
		search: function(){
			this.nemaRezultata = '';
			this.dijagnoze = [];
			
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let d of this.backupDijagnoze){
            	let sifraNaziv = (d.sifra.concat(" ",d.naziv)).toLowerCase();
            	let nazivSifra = (d.naziv.concat(" ", d.sifra)).toLowerCase();
            	
            	if (lowerPretraga.includes(" ")) { //sifra i naziv
            		let passedSifraNaziv = (this.pretraga != '') ? (sifraNaziv.includes(lowerPretraga) || sifraNaziv === lowerPretraga) : true;
                    let passedNazivSifra = (this.pretraga != '') ? (nazivSifra.includes(lowerPretraga) || nazivSifra === lowerPretraga) : true;
                    if (passedSifraNaziv || passedNazivSifra ) this.dijagnoze.push(d);
            	}
            	else { //ili sifra ili naziv
            		let passedSifra = (this.pretraga != '') ? (d.sifra.toLowerCase().includes(lowerPretraga)) : true;
                    let passedNaziv = (this.pretraga != '') ? (d.naziv.toLowerCase().includes(lowerPretraga)) : true;                    
                    if (passedSifra  || passedNaziv) this.dijagnoze.push(d);
            	}
                                
            }
            if (this.dijagnoze.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteDijagnoza: function(id, naziv) {
			axios.delete("/dijagnoza/brisanje/" + id, id)
			.then(response => {
				alert("Dijagnoza '" + naziv + "' uspesno obrisana!");
				this.$router.push("/adminKCHome");
			})
			.catch(error => {
				if (error.response.status === 404)
					alert("Dijagnoza ne postoji u bazi podataka!");
				else if (error.response.status === 409)
					alert("Dijagnoza je u upotrebi i ne moze biti obrisana!");
				else
					alert("SERVER ERROR");
			});

		}
		
	},
	
});
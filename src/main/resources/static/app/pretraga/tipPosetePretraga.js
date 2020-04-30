Vue.component("tipPosetePretraga", {
	
	data: function(){
		return{
			tipoviPregleda: {},
			backup: {},
			pretraga: '',
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
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Unesite naziv tipa pregleda" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Naziv </th>
			<th> Tip </th>
		</tr>
		
		<tr v-for="t in tipoviPregleda">
			<td>{{t.naziv}}</td>
			<td v-if="t.pregled">pregled</td>
			<td v-else>operacija</td>
			<td><button v-on:click="deleteTipPosete(t.id)" class="btn"><i class="fa fa-trash "></i>Obrisi</button></td></tr>
	
	</table>	
		<h3>{{nemaRezultata}}</h3>
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
			});

		}
		
	},
	
});
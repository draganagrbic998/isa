Vue.component("salaPretraga", {
	
	data: function(){
		return{
			sale: {},
			backup: {},
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">PRETRAGA LEKARSKOG OSOBLJA</a>
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
      <input class="form-control mr-sm-2" type="text" placeholder="Pretrazite" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> Ime </th>
			<th> Prezime </th>
		</tr>
		
		<tr v-for="s in sale">
			<td>{{s.naziv}}</td>
			<td>{{s.broj}}</td>
			<td><button v-on:click="deleteSala(s.id)" class="btn"><i class="fa fa-trash"></i>Obrisi</button></td></tr>
	</table>	
	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`, 
	
	mounted(){
		
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sale = response.data;
			this.backup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		
		search: function(){
			
			this.sale = [];
			this.nemaRezultata = '';
            let lowerPretraga = (this.pretraga).toLowerCase();
            
            for (let s of this.backup){
            	let passedNaziv = (this.pretraga != '') ? (s.naziv.toLowerCase().includes(lowerPretraga)) : true;
                let passedBroj = (this.pretraga != '') ? (s.broj.toLowerCase().includes(lowerPretraga)) : true;                    
                if (passedNaziv || passedBroj) this.sale.push(l);                    
            }
            
            if (this.sale.length===0) {
            	this.nemaRezultata = "Nema rezultata pretrage."
            }
		},
		
		deleteSala: function(id) {
			axios.delete("/sala/brisanje/" + id)
			.then(response => {
				alert("Sala uspesno obrisana!");
				location.reload();
			})
			.catch(error => {
				alert("Sala je zakazana za posete i ne moze biti obrisana!");
			});

		}
		
	},
	
});
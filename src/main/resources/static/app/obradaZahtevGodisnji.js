Vue.component('obradaZahtevGodisnji', {
	
	data: function(){
		return{
			zahtevi: {},
			backup: {},
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">ZAHTEVI GODISNJI ODMOR</a>
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
			<th>Profesija</th>
			<th>Ime</th>
			<th>Prezime</th>
			<th>Pocetak</th>
			<th>Kraj</th>
		</tr>
		
		<tr v-for="z in zahtevi" >
			<td> {{z.profesija}} </td>
			<td> {{z.ime}} </td>
			<td> {{z.prezime}} </td>
			<td> {{z.pocetak}} </td>
			<td> {{z.kraj}} </td>
			<td><button v-on:click="potvrdiZahtev(z)" class="btn"><i class="fa fa-check"></i></button></td>
			<td><button v-on:click="odbijZahtev(z)" class="btn"><i class="fa fa-ban"></i></button></td></tr>
	</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`, 
	
	mounted(){
		//preuzeti zahtevi za godisnji
		axios.get("/zahtevOdmor/zahteviKlinika")
		.then(response => {this.zahtevi = response.data;
			this.backup = response.data;})
		.catch(reponse => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		potvrdiZahtev: function(z) {
			axios.post("/zahtevOdmor/potvrda/", z)
			.then(response => {
				alert("Zahtev potvrdjen!");
				this.$router.push("/obradaZahtevGodisnji");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});

		},
		//ne bi trebalo da ide na home page mora da moze jos da odbija
		odbijZahtev: function(z) {
			axios.post("/zahtevOdmor/odbijanje/",z)
			.then(response => {
				alert("Zahtev odbijen!");
				this.$router.push("/obradaZahtevGodisnji");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});

		}
		
	}
		

});
Vue.component("zahtevOdmorObrada", {
	
	data: function(){
		return{
			zahtevi: {},
			backup: {},
			razlog: '',
			greskaRazlog: '',
			pretraga: '',
			nemaRezultata: '',
			odbijanje: false,
			zahtevBrisanje: {},
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
			<td> {{formatiraj(z.pocetak)}} </td>
			<td> {{formatiraj(z.kraj)}} </td>
			<td><button v-on:click="potvrdiZahtev(z)" class="btn"><i class="fa fa-check"></i></button></td>
			<td><button v-on:click="odbijZahtev(z)" class="btn"><i class="fa fa-ban"></i></button></td></tr>
		<tr v-if="this.odbijanje"><td>Razlog za odbijanje:</td> <td><input type="text" v-model="razlog" name="name"></td><td>{{this.greskaRazlog}}</td> 
		<td><button v-on:click="posalji()" class="btn"><i class="fa fa-paper-plane"></i>Posalji</button></td></tr>
	
	</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`, 
	
	mounted(){

		axios.get("/zahtevOdmor/klinika/pregled")
		.then(response => {
			this.zahtevi = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
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
		
		osvezi: function(){
			this.greskaRazlog = "";
			this.greska = false;
		}, 
		
		potvrdiZahtev: function(z) {
			
			axios.post("/zahtevOdmor/potvrda", {"id": z.id, "razlog": ""})
			.then(response => {
				alert("Zahtev uspesno odobren!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});

		},
		
		odbijZahtev: function(z) {
			this.odbijanje = true;
			this.zahtevBrisanje = z;
		},
		
		posalji: function() {
			
			this.osvezi();
			if (this.razlog === '') {
				this.greskaRazlog = "Morate uneti razlog!";
				return;
			}
						
			axios.post("/zahtevOdmor/odbijanje/", {"id": this.zahtevBrisanje.id, "razlog": this.razlog})
			.then(response => {
				alert("Zahtev odbijen!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});

		}
		
	}
		
});
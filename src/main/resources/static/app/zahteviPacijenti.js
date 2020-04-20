Vue.component("zahteviPacijenti", {
	
	data: function(){
		return {
			zahtevi: [],
			selectedID: '',
			razlog: '',
			error: false
		}
	}, 

	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/zahteviPacijenti">ZAHTEVI ZA <br> REGISTRACIJU PACIJENATA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/superAdminHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#"">
          <i class="fa fa-address-book">
          </i>
          Sifra
        </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
	  <select v-model="selectedID">
       	<option v-for="z in zahtevi">{{z.id}}</option>
	  </select>
	  <input class="form-control mr-sm-2" type="text" v-model="razlog" placeholder="Razlog Odbijanja" aria-label="Razlog Odbijanja">
      <button class="btn btn-outline-success my-2 my-sm-0" onclick="potvrdi()">Potvrdi</button>
      <button class="btn btn-outline-success my-2 my-sm-0" onclick="odbij()">Odbij</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> ID </th>
			<th> Email </th>
			<th> Lozinka </th>
			<th> Ime </th>
			<th> Prezime </th>
			<th> Br. Osiguranika </th>
			<th> Telefon </th>
			<th> Drzava </th>
			<th> Grad </th>
			<th> Adresa </th>
		</tr>
		
		<tr v-for="z in zahtevi">
		    <td>{{z.id}}</td>
			<td>{{z.email}}</td>
			<td>{{z.lozinka}}</td>
			<td>{{z.ime}}</td>
			<td>{{z.prezime}}</td>
			<td>{{z.brojOsiguranika}}</td>
			<td>{{z.telefon}}</td>
			<td>{{z.drzava}}</td>
			<td>{{z.grad}}</td>
			<td>{{z.adresa}}</td>
		</tr>
	</table>	
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/zahtevRegistracija/pregled")
		.then(response => {
			this.zahtevi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	methods: {
		potvrdi: function() {
			this.error = false;
			
			let errorMessage = '';
			
			if (this.selectedID === "") {
				errorMessage.concat("Morate odabrati zahtev!");
			}
			
			if (this.error) {
				alert(errorMessage);
				return;
			}
			
			axios.post("/zahtevRegistracija/potvrda", this.selectedID)
			.then(response => {
				alert("Zahtev uspesno odobren!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		},
		
		odbij: function() {
			this.error = false;
			
			let errorMessage = '';
			
			if (this.selectedID === "") {
				errorMessage.concat("Morate odabrati zahtev!");
			}
			
			if (this.razlog === "") {
				errorMessage.concat("Morate uneti razlog odbijanja!");
			}
			
			if (this.error) {
				alert(errorMessage);
				return;
			}
			
			axios.post("/zahtevRegistracija/odbijanje", this.selectedID, this.razlog)
			.then(response => {
				alert("Zahtev uspesno odbijen!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		}
	},
	
});
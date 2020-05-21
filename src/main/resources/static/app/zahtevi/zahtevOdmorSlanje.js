Vue.component("zahtevOdmorSlanje", {
	data: function(){
		return{
			zahtev: {
				'id': null,
				'pocetak': '', 
				'kraj': '', 
				'odobren': false
			}, 
			zaposleni: '',
			greskaKraj: '', 
			greskaPocetak: '', 
			greska: false
		}
	}, 
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <a class="navbar-brand" href="#/zahtevOdmorSlanje">PREGLED GODISNJIH ODMORA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">

  </div>

</nav>

	<div class="card" id="tableBox">
		
		<h2 class="row justify-content-center">Zahtev za godisnji odmor</h2><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Datum pocetka: </th>
						<td><input type="date" v-model="zahtev.pocetak" class="form-control"></td>
						<td>{{greskaPocetak}}</td>
					</tr>
					
					<tr>
						<th scope="row">Datum kraja: </th>
						<td><input type="date" v-model="zahtev.kraj" class="form-control"></td>
						<td>{{greskaKraj}}</td>
					</tr>
				</tbody>
			</table>
			<div class="row justify-content-center">
				<td><button v-on:click="posalji()" class="btn btn-success">POSALJI</button></td>
			</div>
		
		</div>
		
	</div>
	
	`, 
	
	methods: {
		osvezi: function(){
			this.greskaPocetak = '';
			this.greskaKraj = '';
			this.greska = false;
		}, 
		
		posalji: function(){
			
			this.osvezi();
			
			if (this.zahtev.pocetak==='') {
				this.greskaPocetak = 'Odaberite pocetni datum';
				this.greska = true;
			}
			if (this.zahtev.kraj==='') {
				this.greskaKraj = 'Odaberite krajnji datum';
				this.greska = true;
			}
			
			if (this.greska) return;
						
			axios.post("/zahtevOdmor/kreiranje", this.zahtev)
			.then(response => {
				alert("Zahtev poslat!");
			})
			.catch(error => {
				alert("TERMIN ZAUZET!!");
			});
		}
	},
	
	mounted(){
		
		axios.get("/user/check/zaposleni")
		.catch(response => {
			this.$router.push("/");
		});
		
	}
	
});
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

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
   
    </ul>
  </div>
</nav>

	<div class="card" id="tableBox">
		
		<h1>Zahtev za godisnji odmor</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Datum pocetka: </th>
						<td><input type="date" v-model="zahtev.pocetak" name="name"></td>
						<td>{{greskaPocetak}}</td>
					</tr>
					
					<tr>
						<th scope="row">Datum kraja: </th>
						<td><input type="date" v-model="zahtev.kraj" name="name"></td>
						<td>{{greskaKraj}}</td>
					</tr>
					<tr>
						<td><button v-on:click="posalji()" class="btn btn-primary">POSALJI</button></td>
					</tr>
				</tbody>
			</table>
		
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
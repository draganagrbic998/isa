Vue.component("unosIzvestaja", {
	
	data: function(){
		return{
			izvestaj: {
				'id': null,
				'opis': '',
				'dijagnoze': [],
				'lekovi': []
			},
			dijagnoze: [],
			lekovi: []
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
		<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">UNOS IZVESTAJA \nO PREGLEDU</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		<li class="nav-item active">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Pocetna stranica
          <span class="sr-only">(current)</span>
          </a>
		</li>
		</ul>
       
		</div>
		</nav>
		
		</div>
	
		<div class="card" id="tableBox">
		
			<h1>Izvestaj o poseti</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Opis: </th>
						<td><input type="text" v-model="izvestaj.opis" class="form-control"></td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Dijagnoze: </th>
						<td>
							<select v-model="izvestaj.dijagnoze" multiple>
				                <option v-for="d in dijagnoze" :value="d.id">
				                    {{d.naziv}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>
					
					<tr>
						<th scope="row">Lekovi: </th>
						<td>
							<select v-model="izvestaj.lekovi" multiple>
				                <option v-for="l in lekovi" :value="l.id">
				                    {{l.naziv}}
				                </option>
			                </select>
			            </td>
						<td></td>
					</tr>

					<tr>
						<td colspan="3"><button v-on:click="zakaziNovuPosetu()" class="btn btn-primary">ZAKAZI NOVU POSETU</button></td>
					</tr>
					
					<tr>
						<td colspan="3"><button v-on:click="zavrsi()" class="btn btn-primary">ZAVRSI POSETU</button></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/poseta/proveriUToku")
		.catch(response => {
			alert('Trenutno nemate zapocetu posetu!');
			this.$router.push("/lekarHome");
		});
		
		axios.get("/dijagnoza/dobavi")
		.then(response => {
			this.dijagnoze = response.data;
		})
		.catch(response => {
			alert('SERVER ERROR!');
			this.$router.push("/");
		});
		
		axios.get("/lek/dobavi")
		.then(response => {
			this.lekovi = response.data;
		})
		.catch(response => {
			alert('SERVER ERROR!');
			this.$router.push("/");
		});
	}, 
	
	methods: {
		zakaziNovuPosetu: function() {
			this.$router.push("/zakaziPregled");
		},
		
		zavrsi: function() {
			axios.post("/poseta/zavrsi", this.izvestaj)
			.then(response => {
				alert('Izvestaj uspesno unesen!');
				this.$router.push("/lekarHome");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
		
	}
	
});
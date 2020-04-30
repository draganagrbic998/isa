Vue.component("dodajTipPosete", {

	data: function(){
		return {
			tipPosete: {
				'id': null,
				'pregled': '', 
				'naziv': '', 
				'sati': 0,  
				'minute': 0,
				'cena': 0,
				'klinika': null, 
				'aktivan': true
			}, 
			greskaPregled: '',
			greskaNaziv: '', 
			greskaSati: '', 
			greskaMinute: '', 
			greskaCena: '', 
			greska: false,  
			klinika: null
		}
	}, 
	
	template: `
	
	<div>
	<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">KREIRANJE TIPA POSETE</a>
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
       
		</div>
		</nav>
		
		<div class="registracija">

			<div>
			
				<table>
				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="tipPosete.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<tr><td class="left">Pregled: </td><td class="right"><select v-model="tipPosete.pregled">
						<option value="true">pregled</option>
						<option value="false">operacija</option>
					</select></td><td>{{greskaPregled}}</td></tr>
					<tr><td class="left">Sati: </td><td class="right"><input type="text" v-model="tipPosete.sati"></td><td>{{greskaSati}}</td></tr>
					<tr><td class="left">Minute: </td><td class="right"><input type="text" v-model="tipPosete.minute"></td><td>{{greskaMinute}}</td></tr>
					<tr><td class="left">Cena: </td><td class="right"><input type="text" v-model="tipPosete.cena"></td><td>{{greskaCena}}</td></tr>
					<tr><td><button v-on:click="dodajTipPosete()">DODAJ</button><br></td></tr>
					
				</table>
				
			</div>
		</div>
		</div>
	
	`, 
	
	mounted () {
		axios
        .get("/klinika/admin/pregled")
		.then(response => {
			this.klinika = response.data
		})
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
	methods: {
	
		osvezi: function(){
			this.greskaPregled = '';
			this.greskaNaziv = '';
			this.greskaCena = '';
			this.greskaMinute = '';
			this.greskaSati = '';
			this.greska = false;
		}, 
		
		dodajTipPosete: function(){
			
			this.osvezi();
			this.tipPosete.klinika = this.klinika.id;

			if (this.tipPosete.pregled == ''){
				this.greskaPregled = "Pregled ne sme biti prazan.";
				this.greska = true;
			}
			
			if (this.tipPosete.naziv=='') {
				this.greskaNaziv = "Naziv ne sme biti prazan.";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.cena)) || parseInt(this.tipPosete.cena) <= 0){
				this.greskaCena = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.minute)) || parseInt(this.tipPosete.minute) <= 0){
				this.greskaMinute = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.tipPosete.sati)) || parseInt(this.tipPosete.sati) < 0){
				this.greskaSati = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/tipPosete/kreiranje", this.tipPosete)
			.then(response => {
				alert("Tip posete uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch(error => {
				this.greskaNaziv = "Unet naziv nije jedinstven. ";
			});
			
		}
	},
	
});

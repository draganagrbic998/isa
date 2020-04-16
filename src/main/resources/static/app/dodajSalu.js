Vue.component("dodajSalu", {

	data: function(){
		return {
			sala: {
				'id': null,
				'broj': '', 
				'naziv': '', 
				'klinika': null 
			}, 
			greskaBroj: '', 
			greskaNaziv: '', 
			greska: false,  
			klinika: null
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Dodavanje tipa pregleda</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Broj: </td><td class="right"><input type="text" v-model="sala.broj"></td><td>{{greskaBroj}}</td></tr>
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="sala.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<br>
					<tr><td colspan="3"><button v-on:click="dodaj_salu()">DODAJ</button><br></td></tr>
					
				</table>
			
			</div>
		
		</div>
	
	`, 
		
	methods: {
	
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaBroj = '';
			this.greska = false;
		}, 
		
		dodaj_salu: function(){
			
			this.osvezi();
	
			if (this.sala.broj == ''){
				this.greskaBroj= "Broj sale ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.sala.naziv=='') {
				this.greskaNaziv = "Naziv ne sme biti prazan.";
				this.greska = true;
			}
			
			if (this.greska) return;
			this.sala.klinika = this.klinika.id;
			
			axios.post("/sala/kreiranje", this.sala)
			.then(response => {
				alert("Sala uspesno kreirana!");
				this.$router.push("/adminKlinikeHome");
			})
			.catch(error => {
				alert("Broj sale mora biti jedinstven!!");
			});
			
		}
	},
	mounted () {
		axios
        .get("/klinika/vratiKliniku")
        .then(response => (this.klinika = response.data));
	},
	
});

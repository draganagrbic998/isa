Vue.component("dodajKliniku", {

	data: function(){
		return {
			klinika: {
				'id': null,
				'naziv': '', 
				'opis': '', 
				'adresa': '', 
			}, 
			greskaNaziv: '', 
			greskaAdresa: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija nove klinike</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="klinika.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<tr><td class="left">Opis: </td><td class="right"><input type="text" v-model="klinika.opis"></td><td></td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="klinika.adresa"></td><td>{{greskaAdresa}}</td></tr>
					<tr><td><button v-on:click="dodajKliniku()">KREIRAJ PROFIL KLINIKE</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/superadmin")
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		dodajKliniku: function(){
			
			this.osvezi();
			
			if (this.klinika.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.klinika.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/klinika/kreiranje", this.klinika)
			.then(response => {
				alert("Klinika uspesno kreirana!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaNaziv = "Unet naziv nije jedinstven.";
			});
			
		}
	}
	
});

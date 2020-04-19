Vue.component("dodajDijagnozu", {

	data: function(){
		return {
			dijagnoza: {
				'id': null,
				'sifra': '', 
				'naziv': ''
			}, 
			greskaSifra: '', 
			greskaNaziv: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Dodavanje nove dijagnoze</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Sifra: </td><td class="right"><input type="text" v-model="dijagnoza.sifra"></td><td>{{greskaSifra}}</td></tr>				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="dijagnoza.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<br>
					<tr><td colspan="3"><button v-on:click="dodaj_dijagnozu()">DODAJ DIJAGNOZU</button><br></td></tr>

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
			this.greskaSifra = '';
			this.greskaNaziv = '';
			this.greska = false;
		}, 
		
		dodaj_dijagnozu: function(){
			
			this.osvezi();

			if (this.dijagnoza.sifra == ''){
				this.greskaSifra = "Sifra ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.dijagnoza.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/dijagnoza/kreiranje", this.dijagnoza)
			.then(response => {
				alert("Dijagnoza uspesno kreirana!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaSifra = "Uneta sifra vec postoji!!";
			});
			
		}
	}
	
});

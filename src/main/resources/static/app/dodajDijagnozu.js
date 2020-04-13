Vue.component('dodajDijagnozu', {

	data: function(){
		return {
			dijagnoza: {
				'id': null,
				'sifra': '', 
				'naziv': ''
			}, 
			greskaNaziv: '', 
			greskaSifra: '', 
			greska: false,
			dijagnoze: []
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
	
	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaSifra = '';
			this.greska = false;
		}, 
		
		proveri_sifru: function(){
			axios.get("/dijagnoza/pribavi")
			.then(response => {
				this.dijagnoze = response.data;
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
			for (d in this.dijagnoze) {
				if (d.sifra === this.dijagnoza.sifra) {
					return false;
				}
			}
			
			return true;
		},
		
		dodaj_dijagnozu: function(){
			
			this.osvezi();

			if (this.dijagnoza.sifra == ''){
				this.greskaSifra = "Sifra ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (!this.proveri_sifru()){
				this.greskaSifra = "Dijagnoza sa ovom sifrom vec postoji. ";
				this.greska = true;
			}
			
			if (this.dijagnoza.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/dijagnoza/dodavanje", this.dijagnoza)
			.then(response => {
				this.$router.push("/adminKCHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	}
	
});

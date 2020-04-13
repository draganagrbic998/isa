Vue.component('dodajLek', {

	data: function(){
		return {
			lek: {
				'id': null,
				'sifra': '', 
				'naziv': ''
			}, 
			greskaNaziv: '', 
			greskaSifra: '', 
			greska: false,
			lekovi: []
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Dodavanje novog leka</h1>
			
			<div>
			
				<table>
					<tr><td class="left">Sifra: </td><td class="right"><input type="text" v-model="lek.sifra"></td><td>{{greskaSifra}}</td></tr>				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="lek.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<br>
					<tr><td colspan="3"><button v-on:click="dodaj_lek()">DODAJ LEK</button><br></td></tr>
					
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
			axios.get("/lek/dobaviLekove")
			.then(response => {
				this.dijagnoze = response.data;
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
			for (let l of this.lekovi) {
				if (l.sifra === this.lek.sifra) {
					return false;
				}
			}
			
			return true;
		},
		
		dodaj_lek: function(){
			
			this.osvezi();

			if (this.lek.sifra == ''){
				this.greskaSifra = "Sifra ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (!this.proveri_sifru()){
				this.greskaSifra = "Lek sa ovom sifrom vec postoji. ";
				this.greska = true;
			}
			
			if (this.lek.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/lek/dodavanje", this.lek)
			.then(response => {
				this.$router.push("/adminKCHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	}
	
});

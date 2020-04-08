Vue.component('registracijaKlinike', {

	data: function(){
		return {
			klinika: {
				'id': null,
				'naziv': '', 
				'opis': '', 
				'adresa': '', 
			}, 
			greskaNaziv: '', 
			greskaDrzava: '', 
			greskaAdresa: '', 
			greskaGrad: '',
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
					<br>
					<tr><td colspan="3"><button v-on:click="registruj_kliniku()">KREIRAJ PROFIL KLINIKE</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	methods: {
		
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greska = false;
		}, 
		
		registruj_kliniku: function(){
			
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
				//ovde da obavesti da je kreiran profil
				this.$router.push("/adminKCHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	}
	
});

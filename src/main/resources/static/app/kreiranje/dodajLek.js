Vue.component("dodajLek", {

	data: function(){
		return {
			lek: {
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
		
			<h1>Dodavanje novog leka</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Sifra: </td><td class="right"><input type="text" v-model="lek.sifra"></td><td>{{greskaSifra}}</td></tr>				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="lek.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<tr><td><button v-on:click="dodajLek()">DODAJ LEK</button><br></td></tr>
					
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
		
		dodajLek: function(){
			
			this.osvezi();

			if (this.lek.sifra == ''){
				this.greskaSifra = "Sifra ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.lek.naziv == ''){
				this.greskaNaziv = "Naziv ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/lek/kreiranje", this.lek)
			.then(response => {
				alert("Lek uspesno kreiran!");
				this.$router.push("/superAdminHome");
			})
			.catch(error => {
				this.greskaSifra = "Uneta sifra nije jedinstvena.";
			});
			
		}
	}
	
});

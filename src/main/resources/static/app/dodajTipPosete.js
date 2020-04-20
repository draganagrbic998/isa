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
			greskaCena: '', 
			greskaSati: '', 
			greskaMinute: '', 
			greska: false,  
			klinika: null
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Dodavanje tipa pregleda</h1>
			
			<div>
			
				<table>
				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="tipPosete.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<tr><td class="left">Pregled: </td><td class="right"><select v-model="tipPosete.pregled">
						<option value="true">pregled</option>
						<option value="false">operacija</option>
					</select></td><td>{{greskaPregled}}</td></tr>
					<tr><td class="left">Sati: </td><td class="right"><input type="text" v-model="tipPosete.sati"></td><td>{{greskaSati}}</td></tr>
					<tr><td class="left">Minuti: </td><td class="right"><input type="text" v-model="tipPosete.minute"></td><td>{{greskaMinuti}}</td></tr>
					<tr><td class="left">Cena: </td><td class="right"><input type="text" v-model="tipPosete.cena"></td><td>{{greskaCena}}</td></tr>
					<tr><td colspan="3"><button v-on:click="dodaj_tp()">DODAJ</button><br></td></tr>
					
				</table>
				
			</div>
		
		</div>
	
	`, 
	
	
	methods: {
	
		osvezi: function(){
			this.greskaPregled = '';
			this.greskaNaziv = '';
			this.greskaCena = '';
			this.greskaMinute = '';
			this.greskaSati = '';
			this.greska = false;
		}, 
		
		dodaj_tp: function(){
			
			this.osvezi();
			
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
			this.tipPosete.klinika = this.klinika.id;
			
			axios.post("/tipPosete/kreiranje", this.tipPosete)
			.then(response => {
				alert("Tip posete uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch(error => {
				this.greskaNaziv = "Naziv mora biti jedinstven!!";
			});
			
		}
	},
	
	mounted () {
		axios
        .get("/klinika/admin/pregled")
		.then(response => this.klinika = response.data)
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
});

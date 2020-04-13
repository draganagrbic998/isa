Vue.component('noviTipPosete', {

	data: function(){
		return {
			tipPosete: {
				'id': null,
				'pregled': null, 
				'naziv': '', 
				'cena': null,
				'klinika': null, 
				'sati': null,  
				'minuti': null
			}, 
			greskaNaziv: '', 
			greskaPregled: '', 
			greskaCena: '', 
			greskaKlinika: '', 
			greskaSati: '', 
			greskaMinuti: '', 
			greska: false,  
			klinika: {},
			klinike: [],
			nazivKlinike: ''
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Dodavanje tipa pregleda</h1>
			
			
			<div>
			
				<table>
				
					<tr><td class="left">Naziv: </td><td class="right"><input type="text" v-model="tipPosete.naziv"></td><td>{{greskaNaziv}}</td></tr>
					<tr><td class="left">Pregled: </td><td class="right"><select v-model="tipPosete.pregled">
						<option :value="null"></option>
						<option :value="true">Pregled</option>
						<option :value="false">Operacija</option>
					</select></td><td>{{greskaPregled}}</td></tr>
					<tr><td class="left">Sati: </td><td class="right"><input type="text" v-model="tipPosete.sati"></td><td>{{greskaSati}}</td></tr>
					<tr><td class="left">Minuti: </td><td class="right"><input type="text" v-model="tipPosete.minuti"></td><td>{{greskaMinuti}}</td></tr>
					<tr><td class="left">Cena: </td><td class="right"><input type="text" v-model="tipPosete.cena"></td><td>{{greskaCena}}</td></tr>
					<tr><td class="left">Klinika: </td><td class="right"><select v-model="nazivKlinike">
						<option v-for="k in klinike">{{k.naziv}}</option>
					</select></td><td>{{greskaKlinika}}</td></tr>
					<br>
					<tr><td colspan="3"><button v-on:click="dodaj_tp()">DODAJ</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	watch: {
		nazivKlinike: function(){
			for (let k of this.klinike){
				if (k.naziv === this.nazivKlinike)
					this.tipPosete.klinika = k.id;
			}
		}
		
	}, 
	
	methods: {
	
		osvezi: function(){
			this.greskaNaziv = '';
			this.greskaPregled = '';
			this.greskaCena = '';
			this.greskaMinuti = '';
			this.greskaSati = '';
			this.greskaKlinika= '';
			this.greska = false;
		}, 
		
		dodaj_tp: function(){
			
			this.osvezi();
			
			if (this.tipPosete.naziv=='') {
				this.greskaNaziv = "Naziv ne sme biti prazan.";
				this.greska = true;
			}
			
			if (this.tipPosete.pregled == null){
				this.greskaPrelged = "Pregled ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.tipPosete.cena == '' || isNaN(parseInt(this.tipPosete.cena)) || parseInt(this.tipPosete.cena) < 0){
				this.greskaCena = "Neispravan podatak. ";
				this.greska = true;
			}
			
			
			if (this.tipPosete.minuti == '' || isNaN(parseInt(this.tipPosete.minuti)) || parseInt(this.tipPosete.minuti) < 0){
				this.greskaMinuti = "Neispravan podatak. ";
				this.greska = true;
			}
			
			
			if (this.tipPosete.sati == '' || isNaN(parseInt(this.tipPosete.sati)) || parseInt(this.tipPosete.sati) < 0){
				this.greskaSati = "Neispravan podatak. ";
				this.greska = true;
			}
			
			if (this.tipPosete.klinika == ''){
				this.greskaKlinika = "Klinika ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/tipPosete/kreiranje", this.tipPosete)
			.then(response => {
				alert("Tip posete uspesno kreiran!");
				this.$router.push("/adminKlinikeHome");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
			
		}
	},
	mounted () {
		axios
        .get("/klinika/pregled")
        .then(response => (this.klinike = response.data));
	},
	
});

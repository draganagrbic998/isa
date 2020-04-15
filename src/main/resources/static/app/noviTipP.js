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
				'minute': null
			}, 
			greskaNaziv: '', 
			greskaPregled: '', 
			greskaCena: '', 
			greskaSati: '', 
			greskaMinuti: '', 
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
						<option :value="null"></option>
						<option :value="true">Pregled</option>
						<option :value="false">Operacija</option>
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
			this.greskaNaziv = '';
			this.greskaPregled = '';
			this.greskaCena = '';
			this.greskaMinuti = '';
			this.greskaSati = '';
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
			
			
			if (this.tipPosete.minute == '' || isNaN(parseInt(this.tipPosete.minute)) || parseInt(this.tipPosete.minute) < 0){
				this.greskaMinuti = "Neispravan podatak. ";
				this.greska = true;
			}
			
			
			if (this.tipPosete.sati == '' || isNaN(parseInt(this.tipPosete.sati)) || parseInt(this.tipPosete.sati) < 0){
				this.greskaSati = "Neispravan podatak. ";
				this.greska = true;
			}
			
			this.tipPosete.klinika = this.klinika.id;
			console.log(this.tipPosete.klinika+" ovo je klinika");
			if (this.greska) return;
			
			axios.post("/tipPosete/kreiranje", this.tipPosete)
			.then(response => {
				alert("Tip posete uspesno kreiran!");
				this.$router.push("/adminKlinikeHome");
			})
			.catch(error => {
				//alert("SERVER ERROR!");
				this.greskaNaziv = "Naziv mora biti jedinstven! "
			});
			
		}
	},
	mounted () {
		axios
        .get("/klinika/vratiKliniku")
		.then(response => (this.klinika = response.data));
	},
	
});

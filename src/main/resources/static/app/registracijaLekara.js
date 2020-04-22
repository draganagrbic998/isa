Vue.component("registracijaLekara", {

	data: function(){
		return {
			lekar: {
				'id': null,
				'email': '', 
				'lozinka': '', 
				'ime': '', 
				'prezime': '', 
				'telefon': '',  
				'drzava': '', 
				'grad': '', 
				'adresa': '', 
				'pocetnoVreme': '',
				'krajnjeVreme': '',
				'klinika': '',
				'specijalizacija': '',
				"aktivan": true, 
				"promenjenaSifra": false
			}, 
			tip: 'lekar',
			novaLozinka: '',
			ponovljenaLozinka: '', 
			greskaEmail: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greskaIme: '', 
			greskaPrezime: '', 
			greskaTelefon: '', 
			greskaDrzava: '', 
			greskaGrad: '',
			greskaAdresa: '', 
			greskaPocetak: '',
			greskaKraj: '',
			greskaSpec: '',
			greska: false, 
			pocetak: '',
			kraj: '',
			specijalizacije: [], 
			nazivSpecijalizacije: '',
			klinika: null
		}
	}, 
	
	template: `
	
		<div class="registracija">
		
			<h1>Registracija novog lekara</h1>
			
			<div>
			
				<table>
					<tr><td class="left">Profesija: </td><td class="right"><select v-model="tip">
						<option>lekar</option>
						<option>medicinska sestra</option>
					</select></td><td></td></tr>
					<tr><td class="left">Email: </td><td class="right"><input type="text" v-model="lekar.email"></td><td>{{greskaEmail}}</td></tr>
					<tr><td class="left">Ime: </td><td class="right"><input type="text" v-model="lekar.ime"></td><td>{{greskaIme}}</td></tr>
					<tr><td class="left">Prezime: </td><td class="right"><input type="text" v-model="lekar.prezime"></td><td>{{greskaPrezime}}</td></tr>
					<tr><td class="left">Telefon: </td><td class="right"><input type="text" v-model="lekar.telefon"></td><td>{{greskaTelefon}}</td></tr>
					<tr><td class="left">Drzava: </td><td class="right"><input type="text" v-model="lekar.drzava"></td><td>{{greskaDrzava}}</td></tr>
					<tr><td class="left">Grad: </td><td class="right"><input type="text" v-model="lekar.grad"></td><td>{{greskaGrad}}</td></tr>
					<tr><td class="left">Adresa: </td><td class="right"><input type="text" v-model="lekar.adresa"></td><td>{{greskaAdresa}}</td></tr>
					<tr><td class="left">Pocetak smene: </td><td class="right"><input type="text" v-model="pocetak"></td><td>{{greskaPocetak}}</td></tr>
					<tr><td class="left">Kraj smene: </td><td class="right"><input type="text" v-model="kraj"></td><td>{{greskaKraj}}</td></tr>
					<tr><td class="left">Lozinka: </td><td class="right"><input type="password" v-model="novaLozinka"></td><td>{{greskaNovaLozinka}}</td></tr>
					<tr><td class="left">Ponovljena lozinka: </td><td class="right"><input type="password" v-model="ponovljenaLozinka"></td><td>{{greskaPonovljenaLozinka}}</td></tr>
					<tr v-if="this.tip==='lekar'" ><td class="left">Specijalizacija: </td><td class="right"><select v-model="nazivSpecijalizacije">
						<option v-for="s in specijalizacije">{{s.naziv}}</option>
					</select></td><td>{{greskaSpec}}</td></tr>
					
					<br>
					<tr><td colspan="3"><button v-on:click="registracija()">KREIRAJ PROFIL</button><br></td></tr>
					
				</table>
				
			
			</div>
		
		</div>
	
	`, 
	
	mounted () {
		
		axios
        .get("/klinika/admin/pregled")
		.then(response => (this.klinika = response.data))
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios 
		.get("/tipPosete/admin/pregled")
		.then(response => (this.specijalizacije = response.data))
		.catch(reponse => {
			this.$router.push("/");
		});
	},
	
	watch: {
		novaLozinka: function(){
			if (this.novaLozinka == '')
				this.ponovljenaLozinka = '';
		},
		
		nazivSpecijalizacije: function(){
			for (let s of this.specijalizacije){
				if (s.naziv === this.nazivSpecijalizacije)
					this.lekar.specijalizacija = s.id;
			}
		}
	}, 
	
	methods: {
		
		pocetakF: function() {

			if (!this.pocetak.includes(':') && (this.pocetak.length==2)) {
				this.lekar.pocetnoVreme = this.pocetak.concat(":00"); 
			}
			if (!this.pocetak.includes(':') && (this.pocetak.length==1)) {
				this.lekar.pocetnoVreme = '0'.concat(this.pocetak,":00"); 
			}
			if (this.pocetak.includes(':') && (this.pocetak.length==5)) {
				this.lekar.pocetnoVreme=this.pocetak;
			}
			console.log(this.lekar.pocetnoVreme);
			return this.lekar.pocetnoVreme; 
		},
			
		krajF: function() {
			if (!this.kraj.includes(':') && (this.kraj.length==2)) {
				this.lekar.krajnjeVreme = this.kraj.concat(":00"); 
			}
			if (!this.kraj.includes(':') && (this.kraj.length==1)) {
				this.lekar.krajnjeVreme = '0'.concat(this.kraj,":00");
			}
			if (this.kraj.includes(':') && (this.kraj.length==5)) {
				this.lekar.krajnjeVreme=this.kraj;
			}
			console.log(this.lekar.krajnjeVreme);
			return this.lekar.krajnjeVreme;
		},
		
		emailProvera: function emailIsValid(email){
    		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
		}, 
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaNovaLozinka = '';
			this.greskaPonovljenaLozinka = '';
			this.greskaIme = '';
			this.greskaPrezime = '';
			this.greskaTelefon = '';
			this.greskaDrzava = '';
			this.greskaGrad = '';
			this.greskaAdresa = '';
			this.greskaPocetak = '';
			this.greskaKraj = '';
			this.greskaSpec = '';
			this.greska = false;
		}, 
		registruj_sestru() {
			axios.post("/sestra/kreiranje", this.lekar)
			.then(response => {
				alert("Medicinska sestra uspesno kreirana!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				this.greskaEmail = "Email mora biti jedinstven. ";
			});
		}, 
		registruj_lekara() {
			axios.post("/lekar/kreiranje", this.lekar)
			.then(response => {
				alert("Lekar uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				this.greskaEmail = "Email mora biti jedinstven. ";
			});
		},
		
		registracija: function(){
			
			this.osvezi();
			this.pocetakF();
			this.krajF();
			this.lekar.lozinka = this.novaLozinka;
			
			if (!this.pocetak.includes(':') && ((this.pocetak.length>2 || this.pocetak.length<1) || parseInt(this.pocetak)>25)) {
				this.greskaPocetak = "Nespravan format";
				this.greska = true;
			}
			if (this.pocetak.includes(':') && (this.pocetak.length != 5)) {
				this.greskaPocetak = "Nespravan format";
				this.greska = true;
			}
			
			if (!this.kraj.includes(':') && ((this.kraj.length > 2 || this.kraj.length < 1) || parseInt(this.kraj)>25)) {
				this.greskaKraj = "Nespravan format";
				this.greska = true;
			}
			if (this.kraj.includes(':') && (this.kraj.length != 5)) {
				this.greskaKraj = "Nespravan format";
				this.greska = true;
			}
			
			
			if (!this.emailProvera(this.lekar.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.lekar.ime == ''){
				this.greskaIme = "Ime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (this.lekar.prezime == ''){
				this.greskaPrezime = "Prezime ne sme biti prazno. ";
				this.greska = true;
			}
			
			if (isNaN(parseInt(this.lekar.telefon)) || parseInt(this.lekar.telefon) < 0){
				this.greskaTelefon = "Telefon nije ispravan. ";
				this.greska = true;
			}
			
			if (this.lekar.drzava == ''){
				this.greskaDrzava = "Drzava ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.lekar.grad == ''){
				this.greskaGrad = "Grad ne sme biti prazan. ";
				this.greska = true;
			}
			
			if (this.lekar.adresa == ''){
				this.greskaAdresa = "Adresa ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka == ''){
				this.greskaNovaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.ponovljenaLozinka != this.novaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.tip==='lekar' && this.lekar.specijalizacija == '') {
				this.greskaSpec = "Specijalizacija na sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			this.lekar.klinika = this.klinika.id;
			
			if (this.tip === "lekar") {
				this.registruj_lekara();
			}
			else {
				this.registruj_sestru();
			}
				
			
			
		}
	}
	
});

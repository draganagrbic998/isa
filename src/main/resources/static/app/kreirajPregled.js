Vue.component("kreirajPregled", {
	data: function(){
		return {
			pregled: {
				'id': null,
				'datum': '', 
				'vreme': '', 
				'sala': '',
				'tip': '',
				'lekar': '',
				'popust': ''
			}, 
			nazivTipaPregleda: '',
			nazivSale: '',
			imePrezimeLekara: '',
			greskaDatum: '', 
			greskaPopust: '',
			greskaVreme: '', 
			greskaLekar: '',
			greskaSala: '',
			greskaTip: '',
			greska: false,  
			vreme: '',
			lekari: {},
			sale: {},
			tipoviPregleda: {}
		}
	}, 
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
   
    </ul>
  </div>
</nav>
<div class="card" id="tableBox">
		
			<h1>Kreiraj slobodni pregled</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Datum: </th>
						<td><input type="date" v-model="pregled.datum" name="name"></td>
						<td>{{greskaDatum}}</td>
					</tr>
					
					<tr>
						<th scope="row">Vreme: </th>
						<td><input type="text" v-model="vreme" name="name"></td>
						<td>{{greskaVreme}}</td>
					</tr>
					
					<tr><th scope="row">Tip pregleda: </th>
						<td><select v-model="nazivTipaPregleda">
						<option v-for="p in tipoviPregleda">{{p.naziv}}</option>
					</select></td> <td>{{greskaTip}}</td> </tr>
					
					<tr><th scope="row">Sala: </th>
						<td><select v-model="nazivSale">
						<option v-for="s in sale">{{s.naziv}}</option>
					</select></td> <td>{{greskaSala}}</td></tr>
					
					<tr><th scope="row">Lekar: </th>
						<td><select v-model="imePrezimeLekara">
						<option v-for="l in lekari">{{l.ime}} {{l.prezime}}</option>
					</select></td> <td>{{greskaLekar}}</td> </tr>
					
					
					<tr>
						<th scope="row">Popust: </th>
						<td><input type="text" v-model="pregled.popust" name="name"></td>
						<td>{{greskaPopust}}</td>
					</tr>

					<tr>
						<td colspan="3"><button v-on:click="kreiraj()" class="btn btn-primary">KREIRAJ</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
		</div>
	
	`,
	watch: {
		nazivSale: function(){
			for (let s of this.sale){
				if (s.naziv === this.nazivSale)
					this.pregled.sala = s.id;
			}
		},
		nazivTipaPregleda: function(){
			for (let t of this.tipoviPregleda){
				if (t.naziv === this.nazivTipaPregleda)
					this.pregled.tip = t.id;
			}
		},
		imePrezimeLekara: function(){
			for (let l of this.lekari){
				if (l.ime.concat(" ", l.prezime) === this.imePrezimeLekara)
					this.pregled.lekar = l.id;
			}
		}
	
	},
	methods: {
		
		vremePromena: function() {

			if (!this.vreme.includes(':') && (this.vreme.length==2)) {
				this.pregled.vreme = this.vreme.concat(":00"); 
			}
			if (!this.vreme.includes(':') && (this.vreme.length==1)) {
				this.pregled.vreme = '0'.concat(this.vreme,":00"); 
			}
			if (this.vreme.includes(':') && (this.vreme.length==5)) {
				this.pregled.vreme=this.vreme;
			}
			return this.pregled.vreme; 
		},
		osvezi: function(){
			this.greskaVreme = '';
			this.greskaPopust= '';
			this.greskaLekar= '';
			this.greskaTip= '';
			this.greskaSala= '';
			this.greskaDatum = '';
			this.greska = false;
		},
		kreiraj : function() {
			this.osvezi();
			this.vremePromena();
			
			if (this.pregled.datum == '') {
				this.greskaDatum = "Unesite datum!";
				this.greska = true;
			}
			
			if (this.pregled.vreme == '') {
				this.greskaVreme = "Morate uneti vreme";
				this.greska = true;
			}
			if (this.pregled.tip == '') {
				this.greskaTip = "Odaberite tip pregleda";
				this.greska = true;
			}
			if (this.pregled.sala == '') {
				this.greskaSala = "Odaberite salu";
				this.greska = true;
			}
			
			if (this.pregled.lekar == '') {
				this.greskaLekar = "Odaberite lekara";
				this.greska = true;
			}
			if (isNaN(parseFloat(this.pregled.popust)) || parseFloat(this.pregled.popust) < 0){
				this.greskaPopust = "Popust nije ispravan. ";
				this.greska = true;
			}
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format";
				this.greska = true;
			}
			if (this.greska) {return;}
			
			axios.post("/poseta/admin/kreiraj", this.pregled)
			.then(response => {
				alert("Pregled uspesno kreiran!");
				this.$router.push("/adminHome");
			})
			.catch((error) => {
				alert("SERVER ERROR!");
			});
		},
	},
	
	mounted(){

		axios.get("/user/check/admin")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		// dobavi tipove posete
		axios.get("/tipPosete/admin/pregled")
		.then(response => {
			this.tipoviPregleda = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		// dobavi lekare
		axios.get("/lekar/admin/pregled")
		.then(response => {
			this.lekari = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		// dobavi sale
		axios.get("/sala/admin/pregled")
		.then(response => {
			this.sale = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}
	
});
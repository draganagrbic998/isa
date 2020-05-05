Vue.component("zahtevPosetaSlanje", {
	
	data: function(){
		return {
			pregled: {
				'id': null,
				'datum': '', 
				'vreme': '', 
				'lekar': '',
				'karton': '',
				'tip': ''
			}, 
			tipovi: {},
			nazivTipa: '',
			trenutni: {},
			lekar: {},
			greskaDatum: '', 
			greskaVreme: '',
			greskaTip: '',
			greska: false,  
			vreme: ''
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
		
			<h1>Zakazivanje pregleda</h1><br>
			
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
						<td><select v-model="nazivTipa">
						<option v-for="t in tipovi">{{t.naziv}}</option>
					</select></td><td>{{greskaTip}}</td></tr>
					
						<td><button v-on:click="zakazi()" class="btn btn-primary">ZAKAZI</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/poseta/zapoceto")
		.catch(reponse => {
			this.$router.push("/lekarHome");
		});
		
		axios.get("/tipPosete/lekar/pregled")
		.then(response => {
			this.tipovi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	
	watch: {
		nazivTipa : function(){
			for (let t of this.tipovi){
				if (t.naziv === this.nazivTipa)
					this.pregled.tipPosete = t.id;
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
			this.greskaDatum = '';
			this.greskaVreme = '';
			this.greskaTip = '';
			this.greska = false;
		},
		
		zakazi : function() {
			
			this.osvezi();
			this.vremePromena();
			
			if (this.pregled.tipPosete == '') {
				this.greskaTip = "Odaberite tip pregleda!";
				this.greska = true;
			}
			
			if (this.pregled.datum == '') {
				this.greskaDatum = "Unesite datum!";
				this.greska = true;
			}
			
			if (!this.vreme.includes(':') && ((this.vreme.length>2 || this.vreme.length<1) || parseInt(this.vreme)>25)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			if (this.vreme.includes(':') && (this.vreme.length != 5)) {
				this.greskaVreme = "Nespravan format.";
				this.greska = true;
			}
			
			if (this.greska) return;
						
			axios.post("/zahtevPoseta/kreiranje", this.pregled, null)
			.then(response => {
				alert("Zahtev uspesno poslat!");
				this.$router.push("/zapocetPregled");
			})
			.catch((error) => {
			    if (error.response.status === 404) {
			    	this.greskaVreme = "Vreme nije validno!"
			    	alert("VREME MORA ODGOVARATI VASEM RADNOM VREMENU!");
			    }
			    else {
			    	this.greskaDatum = "Datum nije validan!";
			    	alert("DATUM NIJE VALIDAN");
			    }
			});
		}
	}
	
});
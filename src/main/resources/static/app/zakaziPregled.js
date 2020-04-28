Vue.component("zakaziPregled", {
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
					
						<td colspan="3"><button v-on:click="zakazi()" class="btn btn-primary">ZAKAZI</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		</div>
	
	`, 
	watch: {
		nazivTipa : function(){
			for (let t of this.tipovi){
				if (t.naziv === this.nazivTipa)
					this.pregled.tip = t.id;
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
			this.greskaDatum = '';
			this.greskaTip = '';
			this.greska = false;
		},
		
		zakazi : function() {
			this.osvezi();
			this.vremePromena();
			
			if (this.pregled.tip == '') {
				this.greskaTip = "Odaberite tip pregleda!";
				this.greska = true;
			}
			
			if (this.pregled.datum == '') {
				this.greskaDatum = "Unesite datum!";
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
			
			this.pregled.lekar = this.lekar.id;
			this.pregled.karton = this.trenutni.karton; 
			
			axios.post("/zahtevPoseta/lekar/zakazi", this.pregled)
			.then(response => {
				alert("Vas zahtev je poslat");
				this.$router.push("/lekarHome");
			})
			.catch((error) => {
				alert("SERVER ERROR!");
			});
		}
		},
	mounted(){
			
		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/poseta/proveriUToku")
		.then(response => this.trenutni = response.data)
		.catch(reponse => {
			alert("Nijedan pregled nije u toku!");
			this.$router.push("/lekarHome");
		});
		
		axios.get("/lekar/profil")
		.then(response => {
			this.lekar = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		//dobavi tipove pregleda
		axios.get("/tipPosete/lekar/pregled")
		.then(response => {
			this.tipovi = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}
	
});
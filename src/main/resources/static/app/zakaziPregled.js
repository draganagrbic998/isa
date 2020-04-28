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
			trenutni: {},
			lekar: {},
			greskaDatum: '', 
			greskaVreme: '', 
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
						<td colspan="3"><button v-on:click="zakazi()" class="btn btn-primary">ZAKAZI</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		</div>
	
	`, 
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
			this.greska = false;
		},
		
		zakazi : function() {
			this.osvezi();
			this.vremePromena();
			
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
			this.pregled.tip = this.trenutni.tip;
			
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
		
	}
	
});
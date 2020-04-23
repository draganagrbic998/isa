Vue.component("promenaSifre", {
	
	data: function(){
		return{
			novaLozinka: '', 
			ponovljenaLozinka: '', 
			greskaNovaLozinka: '', 
			greskaPonovljenaLozinka: '', 
			greska: false
		}
	}, 
	
	template: `
	
		<div class="card" id="box">
		
			<h1>Promena sifre</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						<th scope="row">Lozinka: </th>
						<td><input type="password" v-model="novaLozinka" class="form-control"></td>
						<td>{{greskaNovaLozinka}}</td>
					</tr>
					
					<tr>
						<th scope="row">Ponovljena lozinka: </th>
						<td><input type="password" v-model="ponovljenaLozinka" class="form-control"></td>
						<td>{{greskaPonovljenaLozinka}}</td>
					</tr>
					<tr>
						<td colspan="3"><button v-on:click="izmeniLozinku()" class="btn btn-primary">IZMENI LOZINKU</button></td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	mounted(){
		axios.get("/user/check/sifra")
		.catch(reponse => {
			this.$router.push("/");
		});
	}, 
	
	methods: {
		osvezi: function(){
			
			this.greskaNovaLozinka = '';
			this.greskaPonovljenaLozinka = '';
			this.greska = false;
			
		}, 
		
		izmeniLozinku(){
			
			this.osvezi();
			
			if (this.novaLozinka == ''){
				this.greskaNovaLozinka = "Lozinka ne sme biti prazna. ";
				this.greska = true;
			}
			
			if (this.novaLozinka != this.ponovljenaLozinka){
				this.greskaPonovljenaLozinka = "Lozinke se ne poklapaju. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/user/lozinka", {"novaLozinka": this.novaLozinka, "ponovljenaLozinka": this.ponovljenaLozinka})
			.then(response => {
				if (response.data == "pacijent")
					this.$router.push("/pacijentHome");
				else if (response.data == "lekar")
					this.$router.push("/lekarHome");
				else if (response.data == "sestra")
					this.$router.push("/sestraHome")
				else if (response.data == "admin")
					this.$router.push("/adminHome");
				else if (response.data == "superadmin")
					this.$router.push("/superAdminHome");
				else
					this.$router.push("/promenaSifre");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
			
		}
	}
	
});
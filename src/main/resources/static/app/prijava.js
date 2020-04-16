Vue.component("prijava", {
	
	data: function(){
		return{
			user: {
				"email": '', 
				"lozinka": ''
			}, 
			greskaEmail: '', 
			greskaLozinka: '', 
			greskaPrijava: '',
			greska: false
		}
	}, 
	
	template: `
	
		<div class="card" style="width: 30rem; height: 30rem;" id="box">
		
			<h1>Prijava</h1><br>
			Email: <input type="text" v-model="user.email" class="form-control" placeholder="Email...">{{greskaEmail}}<br><br>
			Lozinka: <input type="password" v-model="user.lozinka" class="form-control" placeholder="Lozinka...">{{greskaLozinka}}<br><br>
			<button v-on:click="prijava()" class="btn btn-primary">PRIJAVA</button><br><br>
			Niste registrovani? <router-link to="/registracija">Registruj se</router-link><br><br>
			{{greskaPrijava}}<br><br>
		
		</div>
	
	`, 
	
	
	
	methods: {
		
		osvezi: function(){
			this.greskaEmail = '';
			this.greskaLozinka = '';
			this.greskaPrijava = '';
			this.greska = false;
		}, 
		
		prijava: function(){
			
			this.osvezi();
			
			if (this.user.email == ''){
				this.greskaEmail = "Niste uneli email adresu. ";
				this.greska = true;
			}
			
			if (this.user.lozinka == ''){
				this.greskaLozinka = "Niste uneli lozinku. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/user/prijava", this.user)
			.then(response => {
				if (response.data == "pacijent")
					this.$router.push("pacijentHome");
				else if (response.data == "lekar")
					this.$router.push("lekarHome");
				else if (response.data == "sestra")
					this.$router.push("sestraHome")
				else if (response.data == "admin")
					this.$router.push("adminKlinikeHome");
				else
					this.$router.push("adminKCHome");
			})
			.catch(response => {
				this.greskaPrijava = "Unet korisnik ne postoji. ";
			});
			
		}
		
	}
	
});

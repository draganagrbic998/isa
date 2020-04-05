Vue.component('lekarBrisanje', {

	data: function () {
	    return {
	     email: '',
	     greskaEmail : '',
	     greska: false
	    }
},
	template: `
	
		<div>
			<h1>Brisanje lekara</h1>
			<table>	
				<tr><td colspan="6"><router-link to="/">Pocetna stranica</router-link></td></tr>
				<br>
				<tr><td class="left">Email:</td><td class="right"><input type="text" v-model="email" name="name"></td> {{greskaEmail}}</tr> 
			</table>
			<button v-on:click="obrisiLekara">Obrisi</button>
		</div>
	
	`, 
	methods : {
		emailProvera: function emailIsValid(email){
    		return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
		}, 
		obrisiLekara : function() {
			if (!this.emailProvera(this.email)){
				this.greskaEmail = "Email nije ispravan. ";
				this.greska = true;
			}
			
			if (this.email === ''){
				this.greskaEmail = "Morate uneti email. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.post("/registracijaLekar/brisanje", this.email)
			.then(response => {
				alert("Lekar "+this.email+" uspesno obrisan!");
				this.$router.push("/");
			})
			.catch(error => {
				alert("Lekar ne postoji u bazi podataka!");
			});

		}
		
	}
});
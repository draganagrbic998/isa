Vue.component('lekarBrisanje', {

	data: function () {
		return{
			id: null, 
			greskaId: '', 
			greska: false
		}
	
	},
	
	template: `
	
		
		
		<div class="prijava">
		
			<h1>Brisanje lekara</h1>
			
			<table>
				<tr><td colspan="6"><router-link to="/">Pocetna stranica</router-link></td></tr>
				<br>

				<tr><td class="left">Id: </td><td><input type="text" v-model="id"></td><td>{{greskaId}}</td></tr>
				<tr><td colspan="3"><br><button v-on:click="obrisiLekara()">OBRISI</button><br></td></tr>

			
			</table>
		
		</div>
	
	`, 
	methods : {
	
		
		obrisiLekara : function() {
			this.greska = false;
			
			
			
			if (this.id === ''){
				this.greskaId = "Morate uneti id. ";
				this.greska = true;
			}
			
			if (this.greska) return;
			
			axios.delete("/lekar/brisanje/" + this.id, this.id)
			.then(response => {
				alert("Lekar "+this.id+" uspesno obrisan!");
				this.$router.push("/");
			})
			.catch(error => {
				alert("Lekar ne postoji u bazi podataka!");
			});

		}
		
	}
});
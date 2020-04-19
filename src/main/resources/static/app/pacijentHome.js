Vue.component("pacijentHome", {
	
	template: `
	
		<div class="card" id="box" style="text-align: center; font-size: 20px;">
			<router-link to="/profil">PROFIL</router-link><br><br>
			<router-link to="/karton">KARTON</router-link><br><br>
			<router-link to="/termini">ZAKAZANI TERMINI</router-link><br><br>
			<router-link to="/bolesti">ISTORIJA BOLESTI</router-link><br><br>
			<router-link to="/klinikeSlobodno">PREDEFINISANI TERMINI</router-link><br><br>
			<router-link to="/klinikeLekari">INDIVIDUALNI TERMINI</router-link><br><br>
		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/check/pacijent")
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});
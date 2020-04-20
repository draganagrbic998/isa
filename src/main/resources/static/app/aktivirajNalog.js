Vue.component("aktivirajNalog", {
	
	template: `
	
		<div class="card" style="text-align:center; font-size: 15px;">
		
			<h1>Nalog aktiviran</h1><br>
			Vas nalog je uspesno aktiviran!\nKliknite na sledeci link da bi ste se ulogovali: <br><br>
			<router-link to="/">Prijava</router-link><br><br>
		
		</div>	
	
	`,
	
	mounted() {
		let id = this.$route.query.id;
		
		if (id === "") {
			alert("ID NOT FOUND!\nPLEASE CONTACT SERVER SUPPORT.");
			return;
		}
				
		axios.post("/pacijent/aktiviraj/" + id, {})
		.then(response => {
		})
		.catch(response => {
			alert("SERVER ERROR!");
		});

	},

});

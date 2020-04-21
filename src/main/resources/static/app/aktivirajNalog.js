Vue.component("aktivirajNalog", {
	
	template: `
	
		<div class="card" style="text-align:center; font-size: 15px;">
		
			<h1>Nalog aktiviran</h1><br>
			Vas nalog je uspesno aktiviran!\nKliknite na sledeci link da bi ste se ulogovali: <br><br>
			<router-link to="/">PRIJAVA</router-link><br><br>
		
		</div>	
	
	`,
	
	mounted() {
		
		let id = this.$route.query.id;
		
		if (id === "") {
			alert("ID NOT FOUND!\nPLEASE CONTACT SERVER SUPPORT.");
			return;
		}
				
		axios.get("/pacijent/aktiviraj/" + id)
		.catch(error => {
			if (error.response.status === 409)
				alert("Vas nalog je vec aktiviran!");
			else
				alert("SERVER ERROR!");
		});

	},

});

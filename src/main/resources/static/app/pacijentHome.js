Vue.component("pacijentHome", {
	
	data: function(){
		return{
			imePrezime: ''
		}
	},
	
	template: `
	
		<div class="well" id="box" style="text-align: center; font-size: 20px;">
		
			<h1 style="font-size: 50px">Dobrodosli {{imePrezime}}!</h1><br>
			<router-link to="/profil">PROFIL</router-link><br><br>
			<router-link to="/karton">KARTON</router-link><br><br>
			<router-link to="/termini">ZAKAZANI TERMINI</router-link><br><br>
			<router-link to="/bolesti">ISTORIJA BOLESTI</router-link><br><br>

		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/ime/prezime")
		.then(response => {
			this.imePrezime = response.data;
		})
		.catch(reponse => {
			this.$router.push("/");
		});
	}, 
	
	methods: {
		
		odjava: function(){
			axios.get("/user/odjava")
			.then(response => {
				this.$router.push("/");
			})
			.catch(response => {
				alert("SERVER ERROR!!");
			});
		}
		
	}
	
});
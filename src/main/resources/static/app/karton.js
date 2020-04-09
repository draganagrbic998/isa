Vue.component("karton", {
	
	data: function(){
		return{
			karton: {}
		}
	}, 
	
	template: `
	
		<div class="well" id="box">
		
			<h1>Zdravstveni karton</h1><br>
			
			<table class="table">
			
				<tbody>
				
					<tr>
						
						<th scope="row">Broj osiguranika: </th>
						<td><input type="text" v-model="karton.brojOsiguranika" class="form-control" disabled></td>
						
					
					</tr>
					
					<tr>
						
						<th scope="row">Visina: </th>
						<td><input type="text" v-model="karton.visina" class="form-control" disabled></td>
					
					</tr>
					
					<tr>
						
						<th scope="row">Tezina: </th>
						<td><input type="text" v-model="karton.tezina" class="form-control" disabled></td>
					
					</tr>
					
					<tr>
						
						<th scope="row">Leva dioptrija: </th>
						<td><input type="text" v-model="karton.levaDioptrija" class="form-control" disabled></td>
					
					</tr>
					
					<tr>
						
						<th scope="row">Desna dioptrija: </th>
						<td><input type="text" v-model="karton.desnaDioptrija" class="form-control" disabled></td>
					
					</tr>
					
					<tr>
						
						<th scope="row">Krvna grupa: </th>
						<td><input type="text" v-model="karton.krvnaGrupa" class="form-control" disabled></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/karton")
		.then(response => {
			this.karton = response.data
		})
		.catch(response => {
			this.$router.push("/profil");
		});
		
	}
	
});
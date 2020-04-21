Vue.component("karton", {
	
	data: function(){
		return{
			karton: {}
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/pacijentHome">POCETNA STRANICA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home page
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/profil">
          <i class="fa fa-address-book"></i>
          Profil pacijenta
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/karton">
          <i class="fa fa-address-book"></i>
          Karton pacijenta
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/termini">
          <i class="fa fa-line-chart"></i>
          Zakazani termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/bolesti">
          <i class="fa fa-globe"></i>
          Istorija bolesti
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/klinikeSlobodno">
          <i class="fa fa-bell"></i>
          Predefinisani termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#/klinikeLekari">
          <i class="fa fa-hotel"></i>
          Individualni termin
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>
		
		</div>
	
		<div class="card" id="box">
		
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
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/karton")
		.then(response => {
			this.karton = response.data
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}
	
});
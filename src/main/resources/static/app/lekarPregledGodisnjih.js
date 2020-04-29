Vue.component("lekarPregledGodisnjih", {
	
	data: function(){
		return {
			godisnji: []
		}
	}, 
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/lekarPregledGodisnjih">PREGLED GODISNJIH ODMORA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
  </div>
</nav>
		
	<div>
		<div class="card" id="tableBox">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th> Datum Pocetka </th>
					<th> Datum Zavrsetka</th>
					<th> Dani </th>
				</tr>
				
				<tr v-for="g in godisnji" bgcolor="white">
				    <td>{{g.pocetak}}</td>
					<td>{{g.kraj}}</td>
					<td>{{g.trajanje}}</td>
				</tr>
			</table>
		</div>
	
	</div>
	
		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/");
		});
		
		axios.get("/lekar/getGodisnji")
		.then(response => {
			this.godisnji = response.data;
		})
		.catch(response => {
			alert('SERVER ERROR!');
			this.$router.push("/");
		});
		
	}
});
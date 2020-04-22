Vue.component("overaRecepata", {
	data: function(){
		return{
			recepti: [],
			selectedId: '',
			error: false
		}
	},
	
	template: `
	
		<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#/overaRecepata">OVERA RECEPATA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/sestraHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
	  <select v-model="selectedId">
       	<option v-for="r in recepti">{{r.id}}</option>
	  </select>
      <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="overi()">Overi</button>
    </form>
  </div>
</nav>
	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th> ID </th>
			<th> Broj Osiguranika </th>
			<th> Lekovi </th>
			<th> Dijagnoze </th>
		</tr>
		
		<tr v-for="r in recepti">
		    <td>{{r.id}}</td>
			<td>{{r.brOsiguranika}}</td>
			<td>
				<select>
					<option v-for="l in r.lekovi">{{l}}</option>
				</select>
			</td>
			<td>
				<select>
					<option v-for="d in r.dijagnoze">{{d}}</option>
				</select>
			</td>
		</tr>
	</table>
	
		</div>
	
	`, 
	
	mounted(){
		axios.get("/terapija/getNeoverene")
		.then(response => {
			this.recepti = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
	},
	
	methods: {
		this.error = false;
		
		let errorMessage = '';
		
		if (this.selectedId === "") {
			errorMessage = "Morate odabrati recept!";
			this.error = true;
		}
		
		if (this.error) {
			alert(errorMessage);
			return;
		}
		
		overi: function() {
			axios.post("/terapija/overi/" + this.selectedId, {})
			.then(response => {
				alert("Recept uspesno overen!");
				location.reload();
			})
			.catch(response => {
				alert("SERVER ERROR!");
			});
		}
	}
});
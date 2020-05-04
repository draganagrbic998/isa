Vue.component("izvestaj", {
	
	data: function(){
		return{
			ocena: {},
			pocetak: '',
			kraj: '',
			profit: '',
			period: {
				pocetak: '',
				kraj: ''
			},
			nemaRezultata: ''
		}
	},
	template: `
	
	<div>

<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">IZVESTAJ O POSLOVANJU</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminHome">
          <i class="fa fa-home"></i>
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
  </div>
</nav>

	<div class="card" id="box">
		
			<h2>Klinika</h2><br>
			
			<table class="table">
			
				<tr>
						<th scope="row">Prosecna ocena: </th>
						<td><input type="text" v-model ="ocena" class="form-control" disabled></td>
					</tr>
					<tr><th scope="row">PRIHODI KLINIKE</th></tr>
					<tr>
						<td><input type="date" v-model="period.pocetak"></td>
						<td><input type="date" v-model="period.kraj"></td>
						<td><button v-on:click="nadjiPrihode()">PRIKAZI</button></td>
					</tr>
					
					<tr>
						<th scope="row">Profit: </th>
						<td><input type="text" class="form-control" v-model="profit" disabled></td>
					</tr>
					
								
			</table>
		
		</div>
	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`
	,
	mounted() {
		
		axios.get("/klinika/admin/ocena")
		.then(response => {
			this.ocena = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	}, 
	methods: {
		nadjiPrihode:function() {
			axios.post("/klinika/admin/profit", this.period)
			.then(response => {
				this.profit = response.data;
				console.log(this.profit);
			})
			.catch(response => {
				alert("Datum nije validan!");
			});
		}
	}
});
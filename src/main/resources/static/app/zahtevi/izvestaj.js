Vue.component("izvestaj", {
	data: function(){
		return{
			parametar: 'nedeljni',
			graf: {},
			podaci: {},
			ocena: {},
			pocetak: '',
			kraj: '',
			profit: '',
			period: {
				pocetak: '',
				kraj: ''
			}
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
	

	<div class="container"> 
		
		<div class="row">
		
			<div class="col card" id="left">

		
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
		<div class="col-md-5" style="margin-top: 3%">
				<table>
				<tr><h2>Grafikon pregleda</h2></tr>
				<tr><td class="form-control"> <h5>Odaberite nivo:</h5> </td>
				<td> <select v-model="parametar" class="form-control">
						<option>dnevni</option>
						<option>nedeljni</option>
						<option>mesecni</option>
						<option>godisnji</option>
					</select></td></tr>
				</table>
			<column-chart :data="podaci"></column-chart>
				
		</div>
		</div>
		</div>
	</div>
	
	`
	,
	watch: {
		parametar: function(){
			if (this.parametar != '') {
				axios.get("/klinika/admin/graf/" + this.parametar)
				.then(response => {
					this.podaci = response.data;
					
				})
				.catch(response => {
					this.$router.push("/");
				});	
			}
				
		},

	},
	mounted() {
		axios.get("/klinika/admin/ocena")
		.then(response => {
			this.ocena = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		axios.get("/klinika/admin/graf/" + this.parametar)
		.then(response => {
			this.podaci = response.data;
			
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
	},
	
});
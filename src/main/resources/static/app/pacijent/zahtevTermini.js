Vue.component("zahtevTermini", {
	
	data: function(){
		return{
			zahtevi: [], 
			zahteviBackup: [],
			pretraga: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<div>
		
		<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentHome">
          <i class="fa fa-home"></i>
          Home 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
        <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">Search</button>
    </form>
  </div>
</nav>	
			
		
		</div>
		
		<div class="container" id="cosak">
		
			<h2>Zahtevi za termine</h2><br>
			
			<table class="table">
			
				<thead>
				
					<tr>
						<th scope="col">Datum</th>
						<th scope="col">Tip posete</th>
						<th scope="col">Naziv posete</th>
						<th scope="col">Lekar</th>
					
					</tr>
				
				</thead>
				
				<tbody>
				
					<tr v-for="z in zahtevi">
						
						<td>{{formatiraj(z.datum)}}</td>
						<td>{{z.tipPosete}}</td>
						<td>{{z.nazivPosete}}</td>
						<td>{{z.lekar}}</td>
					</tr>
				
				</tbody>
			
			</table>
		
		</div>
		
		</div>
	
	`, 
	
	mounted(){
		
		axios.get("/pacijent/zahtevTermini")
		.then(response => {
			this.zahtevi = response.data;
			this.zahteviBackup = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
		
		
	}, 
	
	
	methods: {
		formatiraj: function (date) {
			
			  date = new Date(date);
			  let year = date.getFullYear();
			  let month = (1 + date.getMonth()).toString();
			  month = month.length > 1 ? month : '0' + month;
			  let day = date.getDate().toString();
			  day = day.length > 1 ? day : '0' + day;
			  let hours = date.getHours().toString();
			  hours = hours.length > 1 ? hours : '0' + hours;
			  let minutes = date.getMinutes().toString();
			  minutes = minutes.length > 1 ? minutes : '0' + minutes;
			  return day + '/' + month + '/' + year + " " + hours + ":" + minutes;
			  
		},
		
		search: function(){
			
			this.zahtevi = [];
			let lowerPretraga = this.pretraga.toLowerCase();
			
			for (let z of this.zahteviBackup){
				let tipPosetePassed = lowerPretraga != '' ? z.tipPosete.toLowerCase().includes(lowerPretraga) : true;
				let nazivPosetePassed = lowerPretraga != '' ? z.nazivPosete.toLowerCase().includes(lowerPretraga) : true;
				let lekarPassed = lowerPretraga != '' ? z.lekar.toLowerCase().includes(lowerPretraga) : true;
				if (tipPosetePassed || nazivPosetePassed || lekarPassed) this.zahtevi.push(z);
			}
			
		}
	}
	
});
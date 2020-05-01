Vue.component("zahtevPosetaObrada", {
	
	data: function(){
		return{
			zahtevi: {},
			backup: {},
			pretraga: '',
			nemaRezultata: ''
		}
	}, 

	template: `
	
	<div>
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">ZAHTEVI ZA POSETU</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminHome">
          <i class="fa fa-home"></i>
          Pocetna stranica
          <span class="sr-only">(current)</span>
          </a>
      </li>
      </ul>
       <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Pretrazite" aria-label="Search" v-model="pretraga">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search()">>Pretrazi</button>
    </form>
  </div>
</nav>

	<table class="table">
		<tr bgcolor="#f2f2f2">
			<th>Lekar</th>
			<th>Pacijent</th>
			<th>Naziv pregleda</th>
			<th>Tip pregleda</th>
			<th>Datum i vreme</th>
		</tr>
		
		<tr v-for="z in zahtevi" >
			<td> {{z.lekar}} </td>
			<td> {{z.pacijent}} </td>
			<td> {{z.naziv}} </td>
			<td v-if="z.pregled">Pregled</td>
			<td v-else>Operacija</td>
			<td> {{formatiraj(z.datum)}} </td> </tr>
		</table>	
		<h3>{{nemaRezultata}}</h3>
	</div>
	
	`, 
	
	mounted(){

		axios.get("/zahtevPoseta/klinika/pregled")
		.then(response => {
			this.zahtevi = response.data;
			this.backup = response.data;
		})
		.catch(reponse => {
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
		/*
		osvezi: function(){
			this.greskaRazlog = "";
			this.greska = false;
		}, */
		
	}
		
});
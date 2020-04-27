Vue.component("lekarKalendar", {
	
	data: function(){
		return {
			obaveze: [],
			dnevni: {},
			nedeljni: {},
			mesecni: {},
			godisnji: {},
			trenutni: {},
			selectedObaveze: []
		}
	}, 
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

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
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarKalendar" v-on:click="prikaziDnevni()">
          <i class="fa fa-calendar"></i>
          Dnevni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarKalendar" v-on:click="prikaziNedeljni()">
          <i class="fa fa-calendar"></i>
          Nedeljni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarKalendar" v-on:click="prikaziMesecni()">
          <i class="fa fa-calendar"></i>
          Mesecni
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarKalendar" v-on:click="prikaziGodisnji()">
          <i class="fa fa-calendar"></i>
          Godisnji
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
  </div>
</nav>
		
	<div>
		<div class="radniKalendarLevaTabelaDiv">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th> Period </th>
					<th> Br. Pregleda </th>
					<th> Br. Operacija </th>
				</tr>
				
				<tr v-for="(value, key) in trenutni" v-on:click="prikaziDetalje(value.obaveze)" bgcolor="white">
				    <td>{{key}}</td>
					<td>{{value.brPregleda}}</td>
					<td>{{value.brOperacija}}</td>
				</tr>
			</table>
		</div>
		
		<div class="radniKalendarDesnaTabelaDiv">
			<table class="table">
				<tr bgcolor="#f2f2f2">
					<th> Pocetak </th>
					<th> Trajanje </th>
					<th> Tip </th>
				</tr>
				
				<tr v-for="obaveza in selectedObaveze" bgcolor="white">
				    <td>{{obaveza.pocetak}}</td>
					<td>{{obaveza.trajanje}}</td>
					<td>{{obaveza.tip}}</td>
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
		
		axios.get("/lekar/getObaveze")
		.then(response => {
			this.obaveze = response.data;
		})
		.catch(response => {
			this.$router.push("/");
		});
		
	},
	
	methods: {
		prikaziDetalje: function(obaveze) {
			this.selectedObaveze = obaveze;
		},
		
		prikaziDnevni: function() {
			this.selectedObaveze = [];
			
			this.dnevni = this.obaveze.reduce(function (acc, obaveza) {
				let period = obaveza.datum;
				
				// check if the week number exists
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);
				
				return acc;

			}, {});
						
			this.trenutni = this.dnevni;
		},
	
		prikaziNedeljni: function() {
			this.selectedObaveze = [];

			this.nedeljni = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).startOf('isoWeek').format("YYYY-MM-DD") + ' - ' + moment(obaveza.datum).endOf('isoWeek').format("YYYY-MM-DD");
				
				// check if the week number exists
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				  
				acc[period].obaveze.push(obaveza);

				return acc;

			}, {});
			
			this.trenutni = this.nedeljni;
		},
		
		prikaziMesecni: function() {
			this.selectedObaveze = [];

			this.mesecni = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).format("YYYY-MM");
				
				// check if the week number exists
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);

				return acc;

			}, {});
						
			this.trenutni = this.mesecni;
		},
		
		prikaziGodisnji: function() {
			this.selectedObaveze = [];

			this.godisnji = this.obaveze.reduce(function (acc, obaveza) {
				let period = moment(obaveza.datum).format("YYYY");
				
				// check if the week number exists
				if (typeof acc[period] === 'undefined') {
				  acc[period] = {
						  'period': '',
						  'brPregleda': 0,
						  'brOperacija': 0,
						  'obaveze': []
				  };
				}
				  
				acc[period].period = period;
				
				if (obaveza.pregled == true)
					acc[period].brPregleda += 1;
				else
					acc[period].brOperacija += 1;
				
				acc[period].obaveze.push(obaveza);

				return acc;

			}, {});
			
			this.trenutni = this.godisnji;
		}
	}
});
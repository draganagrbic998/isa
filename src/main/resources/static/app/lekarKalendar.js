Vue.component("lekarKalendar", {
	
	data: function(){
		return {
			obaveze: [],
			dnevni: {},
			nedeljni: {},
			mesecni: {},
			godisnji: {},
			trenutni: {},
			selectedObaveze: [],
			trenutnaPostoji: false
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
    <ul v-if="trenutnaPostoji" class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/unosIzvestaja">
          <i class="fa fa-stethoscope"></i>
          Trenutni Pregled
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
					<th> Akcija </th>
				</tr>
				
				<tr v-for="obaveza in selectedObaveze" bgcolor="white">
				    <td>{{obaveza.pocetak}}</td>
					<td>{{obaveza.trajanje}}</td>
					<td>{{obaveza.tip}}</td>
					<td>
						<button v-if="can_start(obaveza.pocetak) && !trenutnaPostoji" class="btn btn-outline-success my-2 my-sm-0" v-on:click="zapocni(obaveza.id)">Zapocni</button>
					    <button v-if="can_cancel(obaveza.pocetak)" class="btn btn-outline-success my-2 my-sm-0" v-on:click="otkazi(obaveza.id)">Otkazi</button>
					</td>
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
		
		axios.get("/poseta/proveriUToku")
		.then(response => {
			this.trenutnaPostoji = true;
		})
		.catch(response => {
			this.trenutnaPostoji = false;
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
		can_start: function(pocetak) {
			let datum = Date.parse(pocetak);
			
			if (((datum - Date.now()) / 1000 / 60) > 5)
				return false;
			
			if (((datum - Date.now()) / 1000 / 60) < -60)
				return false;
			
			return false;
		},
		
		can_cancel: function(pocetak) {
			let datum = Date.parse(pocetak);
			
			if (datum < Date.now())
				return false;
			
			if (((datum - Date.now()) / 1000 / 60 / 60) >= 24)
				return true;
			
			return false;
		},
		
		zapocni: function(id) {
			if (id === "")
				return;
			
			axios.get("/poseta/zapocniPosetu/" + id)
			.then(response => {
				this.$router.push("/unosIzvestaja");
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		},
		
		otkazi: function(id) {
			if (id === "")
				return;
			
			axios.delete("/poseta/otkaziPosetuLekar/" + id)
			.then(response => {
				alert("Poseta uspesno otkazana!");
				location.reload();
			})
			.catch(error => {
				alert("SERVER ERROR!");
			});
		},
		
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
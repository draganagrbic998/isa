Vue.component("lekarHome", {
	
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
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/lekarPacijenti">
          <i class="fa fa-list"></i>
          Lista pacijenata
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/zapocetPregled">
          <i class="fa fa-stethoscope"></i>
          Zapocet pregled
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/radniKalendar">
          <i class="fa fa-calendar"></i>
          Radni kalendar
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/godisnjiOdmori">
          <i class="fa fa-calendar"></i>
          Godisnji Odmori
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/zahtevOdmorSlanje">
          <i class="fa fa-plane"></i>
          Zahtev za odmor
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/lekarProfil">
          <i class="fa fa-user-md"></i>
          Profil
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>
		
		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/check/lekar")
		.catch(reponse => {
			this.$router.push("/sestraHome");
		});
		
	}
	
});
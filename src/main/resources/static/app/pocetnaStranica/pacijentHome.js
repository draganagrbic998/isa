Vue.component("pacijentHome", {
	
	template: `
	
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
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/pacijentProfil">
          <i class="fa fa-user"></i>
          Profil 
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 100px;">
        <a class="nav-link" href="#/karton">
          <i class="fa fa-address-book"></i>
          Karton 
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/bolesti">
          <i class="fa fa-line-chart"></i>
          Istorija bolesti
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 130px;">
        <a class="nav-link" href="#/termini">
          <i class="fa fa-calendar"></i>
          Zakazani termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-auto" style="margin: auto;">
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeSlobodno">
          <i class="fa fa-bell"></i>
          Povoljni termini
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item active" style="min-width: 140px;">
        <a class="nav-link" href="#/klinikeLekari">
          <i class="fa fa-hotel"></i>
          Klinike centra
          <span class="sr-only">(current)</span>
          </a>
      </li>
    </ul>
    
  </div>
</nav>
		
		</div>
	
	`, 
	
	mounted(){

		axios.get("/user/check/pacijent")
		.catch(reponse => {
			this.$router.push("/");
		});
	}
	
});
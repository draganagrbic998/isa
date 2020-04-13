Vue.component('adminKCHome', {
	
	template: `
	
		<div>
		
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#/adminKCHome">
          <i class="fa fa-home"></i>
          Home
          <span class="sr-only">(current)</span>
          </a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Administratori Klinickog Centra
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Dodaj Administratora Klinickog Centra</a>
          <a class="dropdown-item" href="#">Pretraga Administratora Klinickog Centra</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-address-book">
          </i>
          Administratori Klinike
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/registracijaAdminaKlinike">Dodaj Administratora Klinike</a>
          <a class="dropdown-item" href="#">Obrisi Administratora Klinike</a>
          <a class="dropdown-item" href="#">Pretraga Administratora Klinike</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-home"></i>
          </i>
          Klinike
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/registracijaKlinike">Dodaj Kliniku</a>
          <a class="dropdown-item" href="#">Pretraga Klinika</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Dijagnoze
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajDijagnozu">Dodaj Dijagnozu</a>
          <a class="dropdown-item" href="#/dijagnozePretraga">Pretraga Dijagnoza</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-line-chart">
          </i>
          Lekovi
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#/dodajLek">Dodaj Lek</a>
          <a class="dropdown-item" href="#/lekoviPretraga">Pretraga Lekova</a>
          <div class="dropdown-divider"></div>
        </div>
      </li>
    </ul>
    <ul class="navbar-nav ">
      <li class="nav-item">
        <a class="nav-link" href="#">
          <i class="fa fa-bell">
            <span class="badge badge-info">11</span>
          </i>
          Test
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <i class="fa fa-globe">
            <span class="badge badge-success">11</span>
          </i>
          Test
        </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
		
		</div>
	
	`, 
	
});
function GerarMatricula(){
	var txt = "ACA";
	var aleatoria = Math.floor(Math.random() * 10000);
	document.getElementById('matricula').value = (txt + aleatoria);
}
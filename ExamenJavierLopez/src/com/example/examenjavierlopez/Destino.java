package com.example.examenjavierlopez;

public class Destino {
	private String zona;
	private String continente;
	private String precio;


public Destino(String Zona, String Continente, String Precio){
	zona = Zona;
    continente = Continente;
    precio = Precio;
}

public String getZona() {
	return zona;
}


public String getContinente() {
	return continente;
}

public String getPrecio() {
	return precio;
}

}

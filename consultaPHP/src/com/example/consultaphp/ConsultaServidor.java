package com.example.consultaphp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConsultaServidor extends Activity {

	private Button boton;
	private TextView texto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_servidor);
        texto = (TextView)findViewById(R.id.texto);
        
        
        //Escuchador para el bot�n
        boton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		try {
        			
        			String resultado = consultaVariable();
        			texto.setText(resultado);
        		} catch (Exception e) {
        			texto.setText("Error al conectar\n");
        			e.printStackTrace();
        		}
        	}
        });
    }
    
    
    String consultaVariable() {
    	String devuelve = "";
    	
    	
    	HttpClient comunicacion = new DefaultHttpClient();
    	String url = "http://192.168.24.53:8080/hola.php";
    	HttpPost peticion = new HttpPost(url);

    	try {
    		//Ejecutamos la petici�n y obtenemos la respuesta en forma de cadena
    		HttpResponse respuesta = comunicacion.execute(peticion);
    		String respuestaCad = EntityUtils.toString(respuesta.getEntity());
    		
    		devuelve = "Direcci�n: " + respuestaCad;
    	} catch(Exception e) {
    		Log.e("Error ies REST", "ERROR!!", e);
    	}
    	
    	return devuelve;
    }
}

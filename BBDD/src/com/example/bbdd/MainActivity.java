package com.example.bbdd;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener{

	Spinner spinner;
    Button btnAdd;
    Button btnEliminar;
    EditText inputName;
    EditText inputCodigo;
    Spinner spinnerId;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaramos variables del spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerId = (Spinner) findViewById(R.id.spinnerId);
        //Variables de los botones
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        //Variables de las entradas de texto
        inputName = (EditText) findViewById(R.id.input_name);
        inputCodigo = (EditText) findViewById(R.id.inputCodigo);

        spinner.setOnItemSelectedListener(this);
        spinnerId.setOnItemSelectedListener(this);
        
        //METODOS para recargar el spinner
        cargarSpinnerNombres();
        cargarSpinnerId();
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                String id = inputCodigo.getText().toString();
                String label = inputName.getText().toString();
                //SOLO INSERTA si el campo ID NO esta vacio
                if(id.trim().length() > 0){
                	DbHelper db = new DbHelper(getApplicationContext());
                	
                	db.insertCentro(id, label);
                	
                	inputCodigo.setText("");
                	inputName.setText("");
                	//METODOS para recargar el spinner
                	cargarSpinnerId();
                	cargarSpinnerNombres();
                } else{
                	Toast.makeText(getApplicationContext(), "ERROR al insertar en la BASE DE DATOS", Toast.LENGTH_SHORT).show();
                	cargarSpinnerId();
                	cargarSpinnerNombres();
                }
            }
        });
        
        btnEliminar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String labelCod = inputCodigo.getText().toString();
				//SOLO ELIMINA si el campo ID no esta vacio
				if(labelCod.trim().length() > 0){
					DbHelper db = new DbHelper(getApplicationContext());
					
					db.eliminarPorId(labelCod);
					
					inputCodigo.setText("");
					//METODOS para recargar el spinner
                    cargarSpinnerId();
                    cargarSpinnerNombres();
				}else{
					Toast.makeText(getApplicationContext(), "Error al eliminar ID", Toast.LENGTH_SHORT).show();
					cargarSpinnerId();
                    cargarSpinnerNombres();
				}
			}
		});
    }
 
    private void cargarSpinnerNombres() {
        DbHelper db = new DbHelper(getApplicationContext());
 
        List<String> lables = db.cogerNombres();
 
        //Adaptador para el spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
 
        //Estilo del layout para el desplazamiento del spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        //Insertamos el adaptador al spinner
        spinner.setAdapter(dataAdapter);
    }
    
    private void cargarSpinnerId(){
    	DbHelper db = new DbHelper(getApplicationContext());
    	
    	List<String> lables = db.cogerIDs();
    	
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lables);
    	
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	spinnerId.setAdapter(dataAdapter);
    }
 
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
    	
        String label = parent.getItemAtPosition(position).toString();
 
        //Texto al tener seleccionado algo en el spinner
        Toast.makeText(parent.getContext(), "Has seleccionado: " + label,
                Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    	cargarSpinnerId();
        cargarSpinnerNombres();
    }

}

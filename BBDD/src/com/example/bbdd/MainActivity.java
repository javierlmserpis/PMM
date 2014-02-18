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
 
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerId = (Spinner) findViewById(R.id.spinnerId);
        
        // add button
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        // new name input field
        inputName = (EditText) findViewById(R.id.input_name);
        inputCodigo = (EditText) findViewById(R.id.inputCodigo);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinnerId.setOnItemSelectedListener(this);
        
        // Loading spinner data from database
        loadSpinnerData();
        cargarSpinnerId();
        /**
         * Add new name button click listener
         * */
        btnAdd.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                String id = inputCodigo.getText().toString();
                String label = inputName.getText().toString();
                
                if(label.trim().length() > 0){
                	DbHelper db = new DbHelper(getApplicationContext());
                	
                	db.guardarId(id);
                	
                	inputCodigo.setText("");
                	cargarSpinnerId();
                } else{
                	Toast.makeText(getApplicationContext(), "Selecciona un nombre", Toast.LENGTH_SHORT);
                }
                
                
                
                if (label.trim().length() > 0) {
                    // database handler
                    DbHelper db = new DbHelper(getApplicationContext());
 
                    // inserting new name into database
                    db.guardarNombre(label);
                    
                    // making input filed text to blank
                    inputName.setText("");
 
                    // loading spinner with newly added data
                    loadSpinnerData();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Selecciona un nombre", Toast.LENGTH_SHORT)
                            .show();
                }
 
            }
        });
        
        btnEliminar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String labelCod = inputCodigo.getText().toString();
				
				if(labelCod.trim().length() > 0){
					DbHelper db = new DbHelper(getApplicationContext());
					
					db.eliminarPorId(labelCod);
					
					inputCodigo.setText("");
                    cargarSpinnerId();
                    loadSpinnerData();
				}else{
					Toast.makeText(getApplicationContext(), "Error al eliminar ID", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        
    }
 
    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        DbHelper db = new DbHelper(getApplicationContext());
 
        // Spinner Drop down elements
        List<String> lables = db.getAllNames();
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    
    private void cargarSpinnerId(){
    	DbHelper db = new DbHelper(getApplicationContext());
    	
    	List<String> lables = db.getAllIds();
    	
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lables);
    	
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	spinnerId.setAdapter(dataAdapter);
    }
 
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Has seleccionado: " + label,
                Toast.LENGTH_LONG).show();
 
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }

}

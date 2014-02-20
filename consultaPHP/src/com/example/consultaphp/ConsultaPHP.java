package com.example.consultaphp;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ConsultaPHP extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_php);
        StrictMode.enableDefaults();
        httpHandler handler = new httpHandler();
        String txt = handler.post("http://192.168.1.16/hola.php");
 
	    TextView t = (TextView)findViewById(R.id.TxtPHP);
        t.setText(txt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.consulta_ph, menu);
        return true;
    }
    
}

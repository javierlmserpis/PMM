package com.example.bbdd;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DbHelper extends SQLiteOpenHelper{
     // Version de la base de datos
    private static final int DB_VERSION = 1;
   
    // Nombre de la base de datos
    private static final String DB_NAME = "bdcentros.db";
   
    // Nombre de la tabla
    private static final String TABLA_1 = "centros";
  //  private static final String TABLA_2 = "personal";
  //  private static final String TABLA_3 = "profesores";
    
    // Nombres de las columnas con las que vamos a trabajar
    private static final String KEY_ID = "cod_centro";
    private static final String KEY_NAME = "nombre";
    
    //Ayudador de la base de datos
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Strings de las sentencias SQL
    	String sqlCreateCentros = "CREATE TABLE centros ( cod_centro   SMALLINT , " +
    			"tipo_centro  CHAR(1)," +
    			"nombre VARCHAR(30)," +
    			"direccion  VARCHAR(26)," +
    			"telefono VARCHAR(10)," +
    			"num_plazas  SMALLINT  UNSIGNED," +
    			"PRIMARY KEY (cod_centro)) ; ";
    	
    	String sqlCreatePersonal = "CREATE TABLE personal (cod_centro   SMALLINT NOT NULL," +
    			"dni INT UNSIGNED NOT NULL," +
    			"apellidos VARCHAR(30)," +
    			"funcion VARCHAR(15)," +
    			"salario FLOAT(7,2),PRIMARY KEY (dni)," +
    			"FOREIGN KEY (cod_centro) " +
    			"REFERENCES centros (cod_centro));";
    	
    	String sqlCreateProfesores = "CREATE TABLE profesores (cod_centro   SMALLINT NOT NULL," +
    			"dni INT UNSIGNED NOT NULL," +
    			"apellidos   VARCHAR(30)," +
    			"especialidad VARCHAR(16),PRIMARY KEY (dni)," +
    			"FOREIGN KEY (dni) REFERENCES personal(dni)," +
    			"FOREIGN KEY (cod_centro) " +
    			"REFERENCES centros (cod_centro));";
    	
    	
        db.execSQL(sqlCreateCentros);
        db.execSQL("INSERT INTO centros VALUES (10,'S','IES El Quijote','Avda. Los Molinos 25', '965-887654',538)");
        db.execSQL("INSERT INTO centros VALUES (15,'P','CP Los Danzantes', 'C/Las Musas s/n','985-112322',250)");
        db.execSQL("INSERT INTO centros VALUES (22,'S', 'IES Planeta Tierra', 'C/Mina 45','925-443400',300)");
        db.execSQL("INSERT INTO centros VALUES (45,'P', 'CP Manuel Hidalgo', 'C/Granada 5','926-202310',220)");
        db.execSQL("INSERT INTO centros VALUES (50,'S', 'IES Antoï¿½ete 1', 'C/Los Toreros 21','989-406090',310)");
        
        db.execSQL(sqlCreatePersonal);
        db.execSQL("INSERT INTO personal VALUES (10,4480099, 'Ruano Cerezo, Manuel','ADMINISTRATIVO', 1800.00)");
        db.execSQL("INSERT INTO personal VALUES (15,1002345, 'Albarrï¿½n Serrano, Alicia', 'ADMINISTRATIVO', 1800.00)");
        db.execSQL("INSERT INTO personal VALUES (15,7002660, 'Munyoz Rey, Felicia', 'ADMINISTRATIVO', 1800.00)");
        db.execSQL("INSERT INTO personal VALUES (22,5502678, 'Marï¿½n Marn, Pedro', 'ADMINISTRATIVO', 1800.00)");
        db.execSQL("INSERT INTO personal VALUES (22,6600980, 'Peinado Gil, Elena','CONSERJE', 1750.00)");
        db.execSQL("INSERT INTO personal VALUES (45,4163222, 'Sarro Molina, Carmen','CONSERJE', 1750.00)");
        db.execSQL("INSERT INTO personal VALUES (10,1112345,'Martnez Salas, Fernando',  'PROFESOR',2200.00)");
        db.execSQL("INSERT INTO personal VALUES (10,4123005,'Bueno Zarco, Elisa', 'PROFESOR',2200.00)");
        db.execSQL("INSERT INTO personal VALUES (10,4122025,'Montes Garca, M.Pilar', 'PROFESOR',2200.00)");
        db.execSQL("INSERT INTO personal VALUES (15,9800990, 'Ramos Ruiz, Luis',     'PROFESOR',2050.00)");
        db.execSQL("INSERT INTO personal VALUES (15,1112355,'Rivera Silvestre, Ana', 'PROFESOR',2050.00)");
        db.execSQL("INSERT INTO personal VALUES (15,8660990, 'De Lucas Fdez, M.Angel',  'PROFESOR',2050.00)");
        db.execSQL("INSERT INTO personal VALUES (22,7650000, 'Ruiz Lafuente, Manuel',  'PROFESOR',2200.00)");
        db.execSQL("INSERT INTO personal VALUES (45,43526789, 'Serrano Lagua, Marï¿½a','PROFESOR',2050.00);");
        
        db.execSQL(sqlCreateProfesores);
        db.execSQL("INSERT INTO profesores VALUES (10,1112345,'Martï¿½nez Salas, Fernando',  'INFORMï¿½TICA')");
        db.execSQL("INSERT INTO profesores VALUES (10,4123005,'Bueno Zarco, Elisa', 'MATEMï¿½TICAS')");
        db.execSQL("INSERT INTO profesores VALUES (10,4122025,'Montes Garcï¿½a, M.Pilar', 'MATEMï¿½TICAS')");
        db.execSQL("INSERT INTO profesores VALUES (15,9800990, 'Ramos Ruiz, Luis',     'LENGUA')");
        db.execSQL("INSERT INTO profesores VALUES (15,1112355,'Rivera Silvestre, Ana', 'DIBUJO')");
        db.execSQL("INSERT INTO profesores VALUES (15,8660990, 'De Lucas Fdez, M.Angel',  'MATEMï¿½TICAS')");
        db.execSQL("INSERT INTO profesores VALUES (22,7650000, 'Ruiz Lafuente, Manuel',  'MATEMï¿½TICAS')");
        db.execSQL("INSERT INTO profesores VALUES (45,43526789, 'Serrano Laguï¿½a, Marï¿½a','INFORMï¿½TICA');");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si hay una nueva version, hacemos DROP de las tablas viejas
        db.execSQL("DROP TABLE IF EXISTS centros");
        db.execSQL("DROP TABLE IF EXISTS personal"); 
        db.execSQL("DROP TABLE IF EXISTS profesores");
        
        // Creamos las tablas de nuevo
        onCreate(db);    
    }
    
     //METODO para insertar un nuevo centro en nuestra base de datos
     public void insertCentro(String id,String nombre){
    	 SQLiteDatabase db = this.getWritableDatabase();
    	 
    	 ContentValues values = new ContentValues();
    	 values.put(KEY_ID, id);
    	 values.put(KEY_NAME,nombre);
    	 
    	 db.insert(TABLA_1, null , values);
    	 db.close();
     }

    //METODO para eliminar un centro (ELIMINAMOS POR LLAVE PRIMARIA) 
    public void eliminarPorId(String labelcod){
    	SQLiteDatabase db = this.getWritableDatabase();
    	String sqlDelete = "DELETE FROM " + TABLA_1 + " where cod_centro=" +labelcod;
    	db.execSQL(sqlDelete);
    	db.close();
    }
    
    public List<String> cogerNombres(){
        List<String> nombresDB = new ArrayList<String>();
        
        String selectQuery = "SELECT  * FROM " + TABLA_1;
       
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
       
        //Bucle sobre las columnas y vamos añadiendo los nombres a la lista de tipo List<>
        if (cursor.moveToFirst()) {
            do {
                nombresDB.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
          
        return nombresDB;
    }
    
    public List<String> cogerIDs(){
        List<String> idDB = new ArrayList<String>();
  
        String selectQuery = "SELECT  * FROM " + TABLA_1;
       
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
       
        //Bucle sobre las columnas y vamos añadiendo los ID'S a la lista de tipo List<>
        if (cursor.moveToFirst()) {
            do {
                idDB.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
          
        return idDB;
    }
 
}

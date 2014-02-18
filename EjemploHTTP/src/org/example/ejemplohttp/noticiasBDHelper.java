package org.example.ejemplohttp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class noticiasBDHelper extends SQLiteOpenHelper{

	public noticiasBDHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String tableNoticias = "CREATE TABLE Noticias (codigo_noticia INTEGER AUTO_INCREMENT PRIMARY KEY, noticia varchar(200))";
		
		db.execSQL(tableNoticias);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

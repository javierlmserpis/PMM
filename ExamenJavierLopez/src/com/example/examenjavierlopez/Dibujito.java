package com.example.examenjavierlopez;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class Dibujito extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(new Monigote(this));
	}
	public class Monigote extends View{
		public Monigote (Context contexto){
			super (contexto);
		}
	
	
		protected void onDraw (Canvas canvas){
		Paint pen = new Paint();
		
		//Dibujamos el tejado
		Path path = new Path();
		path.setFillType(FillType.EVEN_ODD);
		pen.setColor(Color.BLACK);
		pen.setStrokeWidth(2);
		pen.setStyle(Style.STROKE);
		path.moveTo(300, 300);
		path.lineTo(500, 300);
		path.lineTo(400, 140);
		path.lineTo(300, 300);
		path.close();
		canvas.drawPath(path, pen);
		
		//Cuerpo de la casa
		
		pen.setColor(Color.CYAN);
		pen.setStrokeWidth(195);
		pen.setStyle(Style.STROKE);
		canvas.drawRect(400, 400, 400, 400, pen);
		
		//Texto
		
		pen.setStrokeWidth(1);
		pen.setColor(Color.DKGRAY);
		pen.setTextSize(15);
		pen.setTextAlign(Align.CENTER);
		canvas.drawText("Nuestras oficinas", 400, 760, pen);
		
		
		
		
		}
	}
}

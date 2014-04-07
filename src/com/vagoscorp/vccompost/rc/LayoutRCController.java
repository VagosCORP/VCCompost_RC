package com.vagoscorp.vccompost.rc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import com.vagoscorp.vccompost.rc.TabletCMD.OrdenRecibida;

public class LayoutRCController implements Initializable {

	@FXML TextArea PosX;
	@FXML TextArea PosY;
	
	TabletCMD remoto;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		remoto = new TabletCMD();
		remoto.asignarListener(new OrdenRecibida() {
			
			@Override
			public void mover_a(float x, float y) {
//				func de 0-1000 a valores reales:
//				x = (float)(factorX*Float.parseFloat(vals[1])) + limX;
//				y = (float)(factorY*Float.parseFloat(vals[3])) + limY;
//				z = (float)(factorZ*Float.parseFloat(vals[3])) + limZ;
//				a = (float)Math.toRadians(Float.parseFloat(vals[3]));
//				Mueve tu maquina!
			}
			
			@Override
			public void desconectado() {
				
			}
			
			@Override
			public void conectado() {
				
			}
		});
	}
//	func de valores reales a 0-1000:	
//	float xval = (float)((float)xDeseado - limX) / factorX;
//	float yval = (float)((float)yDeseado - limY) / factorY;
//	Has eso por tu lado y luego llamas actualizar
	public void actualizar(float x, float y) { //Valores de 0 a 1000
		remoto.comunic.enviar("X=" + Float.toString(x) +
				"=Y=" + Float.toString(y) + "=/");
	}
}

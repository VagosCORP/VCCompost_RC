package com.vagoscorp.vccompost.rc;

import libraries.vagoscorp.comunication.Eventos.OnComunicationListener;
import libraries.vagoscorp.comunication.Eventos.OnConnectionListener;
import libraries.vagoscorp.comunication.fx.Comunic;

public class TabletCMD implements OnComunicationListener{

	Comunic comunic;
	Thread th;
	String serverip;
	int serverport;
	String tarea = "";
	OrdenRecibida ordenrcv;
	
	public interface OrdenRecibida {
		public void conectado();
		public void mover_a(float x, float y);
		public void desconectado();
	}
	
	public void asignarListener(OrdenRecibida ordr) {
		ordenrcv = ordr;
	}
	
	public TabletCMD() {
		iniciarServer();
	}
	
	private void iniciarServer() {
		comunic = new Comunic(serverport);
		comunic.setComunicationListener(this);
		comunic.setConnectionListener(new OnConnectionListener() {

			@Override
			public void onConnectionstablished() {
//				float xval = (float)((float)xDeseado - limX) / ex;
//				float yval = (float)((float)yDeseado - limY) / ey;
//				comunic.enviar("X=" + Float.toString(xval) +
//						"=Y=" + Float.toString(yval) + "=/");
				if(ordenrcv != null)
					ordenrcv.conectado();
			}

			@Override
			public void onConnectionfinished() {
				if(ordenrcv != null)
					ordenrcv.desconectado();
				iniciarServer();
			}
		});
		th = new Thread(comunic);
		th.setDaemon(true);
		th.start();
	}
	
	@Override
	public void onDataReceived(String dato) {
		tarea += dato;
		if (dato.endsWith("/")) {
			String[] vals = tarea.split("=");
			float x = Float.parseFloat(vals[1]);
			float y = Float.parseFloat(vals[3]);
			if(ordenrcv != null)
				ordenrcv.mover_a(x, y);
			tarea = "";
		}
	}

}

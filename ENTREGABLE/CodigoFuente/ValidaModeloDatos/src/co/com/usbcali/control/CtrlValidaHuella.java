package co.com.usbcali.control;

import java.sql.SQLException;
import java.util.ArrayList;

import co.com.usbcali.db.LogCambio;
import co.com.usbcali.pool.EjecucionBD;

public class CtrlValidaHuella {

public ArrayList<LogCambio> Validar(String usuario, String clave, String conexion) {
		
		EjecucionBD ejecutor = new EjecucionBD(usuario, clave, conexion);
		
	//	ejecutor.executeProcedure("{call PKGV_VAL_DB_MODEL.FNDB_REGISTRA_HUELLA}");
		
		try {
		ArrayList<LogCambio> listaLog = 	ejecutor.consultaLog();
		
		return listaLog;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
}

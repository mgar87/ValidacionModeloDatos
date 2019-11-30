package co.com.usbcali.control;

import co.com.usbcali.pool.EjecucionBD;

public class CtrlRegistrarHuella {
	
	public void Registrar(String usuario, String clave, String conexion) {
		
		EjecucionBD ejecutor = new EjecucionBD(usuario, clave, conexion);
		
		ejecutor.executeProcedure("{call PKGV_VAL_DB_MODEL.FNDB_REGISTRA_HUELLA}");
		
		ejecutor.executeProcedure("{CALL PKGV_VAL_DB_MODEL.PRDB_VALIDAHUELLA}");
		
	}
	
	
	

}

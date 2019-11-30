package co.com.usbcali.db;

public class LogCambio {
	
	  String tabla;
      String columna;
      String huellaOld;
      String fechaHuellaAnt;
      String huellaNueva;
      String fechaHuellaNueva;
      String fechaAud;
      
      
      
      
      
      
	public LogCambio(String tabla, String columna, String huellaOld, String fechaHuellaAnt, String huellaNueva,
			String fechaHuellaNueva, String fechaAud) {
		this.tabla = tabla;
		this.columna = columna;
		this.huellaOld = huellaOld;
		this.fechaHuellaAnt = fechaHuellaAnt;
		this.huellaNueva = huellaNueva;
		this.fechaHuellaNueva = fechaHuellaNueva;
		this.fechaAud = fechaAud;
	}
	
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getColumna() {
		return columna;
	}
	public void setColumna(String columna) {
		this.columna = columna;
	}
	public String getHuellaOld() {
		return huellaOld;
	}
	public void setHuellaOld(String huellaOld) {
		this.huellaOld = huellaOld;
	}
	public String getFechaHuellaAnt() {
		return fechaHuellaAnt;
	}
	public void setFechaHuellaAnt(String fechaHuellaAnt) {
		this.fechaHuellaAnt = fechaHuellaAnt;
	}
	public String getHuellaNueva() {
		return huellaNueva;
	}
	public void setHuellaNueva(String huellaNueva) {
		this.huellaNueva = huellaNueva;
	}
	public String getFechaHuellaNueva() {
		return fechaHuellaNueva;
	}
	public void setFechaHuellaNueva(String fechaHuellaNueva) {
		this.fechaHuellaNueva = fechaHuellaNueva;
	}
	public String getFechaAud() {
		return fechaAud;
	}
	public void setFechaAud(String fechaAud) {
		this.fechaAud = fechaAud;
	}
      
      
      

}

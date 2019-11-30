package co.com.usbcali.pool;

public class Respuesta {
	
	 private boolean boResultado;

	    public Respuesta() {
	        this.boResultado = true;
	        this.strDesError = new String();
	        this.objContenido = null;
	    }

	    public Respuesta(boolean boResultado, String strDesError, Object objContenido) {
	        this.boResultado = boResultado;
	        this.strDesError = strDesError;
	        this.objContenido = objContenido;
	    }

	    public boolean isBoResultado() {
	        return boResultado;
	    }

	    public void setBoResultado(boolean boResultado) {
	        this.boResultado = boResultado;
	    }

	    public Object getObjContenido() {
	        return objContenido;
	    }

	    public void setObjContenido(Object objContenido) {
	        this.objContenido = objContenido;
	    }

	    public String getStrDesError() {
	        return strDesError;
	    }

	    public void setStrDesError(String strDesError) {
	        this.strDesError = strDesError;
	    }
	    private String  strDesError;
	    private Object  objContenido;

}

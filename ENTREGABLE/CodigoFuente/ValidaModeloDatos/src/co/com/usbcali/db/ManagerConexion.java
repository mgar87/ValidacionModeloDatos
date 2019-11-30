package co.com.usbcali.db;

import java.sql.Connection;

public class ManagerConexion {
	
    private Connection conexion;
    private boolean estado;
    private int indice;

    /** Creates a new instance of ManagerConexion */
    public ManagerConexion() {
    }

    public void setConexion(Connection c){
        this.conexion=c;
    }

    public void setEstado(boolean estado){
        this.estado=estado;
    }

    public void setIndice(int indice){
        this.indice=indice;
    }

    public Connection getConexion(){
        return this.conexion;
    }

    public boolean getEstado(){
        return this.estado;
    }

    public int getIndice(){
        return this.indice;
    }

}

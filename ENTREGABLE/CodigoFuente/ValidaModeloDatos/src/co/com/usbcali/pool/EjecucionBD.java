package co.com.usbcali.pool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Logger;

import co.com.usbcali.db.Conexion;
import co.com.usbcali.db.ConnectionPool;
import co.com.usbcali.db.LogCambio;


public class EjecucionBD {
	
	
	String usuario;
	String clave;
	String conexion;
	
	public EjecucionBD(String usuario, String clave, String conexion) {
		this.usuario = usuario;
		this.clave = clave;
		this.conexion = conexion;
	}
	
	public void executeProcedure(String function) {

		Respuesta resultado = new Respuesta();
		ConnectionPool pool = null;
		Conexion objetoConexion = null;
		Connection conn = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManager.getInstance();
		String respuesta = "";

		try {


				pool = ConnectionPool.getInstance(usuario,clave, conexion);
				objetoConexion = pool.getConnection();

				conn = objetoConexion.getConnection();				

				cstmt1 = conn.prepareCall(function);
				
				cstmt1.execute();
				
				cstmt1.close();
				cstmt1 = null;

				} catch (Exception e) {

					resultado.setBoResultado(false);
					resultado
							.setStrDesError("Problemas Ejecutando Funcion Generica");
					
				}
				

	}
	
	
	
	
	
	
	public Respuesta executeFunction(String function) {

		Respuesta resultado = new Respuesta();
		ConnectionPool pool = null;
		Conexion objetoConexion = null;
		Connection conn = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManager.getInstance();
		String respuesta = "";

		try {


				pool = ConnectionPool.getInstance(usuario,clave, conexion);
				objetoConexion = pool.getConnection();

				conn = objetoConexion.getConnection();

//FNDB_REGISTRA_HUELLA				

				cstmt1.clearParameters();
				cstmt1.close();
				cstmt1 = null;

				cstmt1 = conn.prepareCall(function);
				cstmt1.registerOutParameter(1, Types.CLOB);
				cstmt1.setString(2, "");
				
				
				
					resultado.setObjContenido(respuesta);

					if (resultado.getObjContenido() != null) {
						resultado.setBoResultado(true);
						
					} else {
						resultado.setBoResultado(false);
						resultado.setStrDesError("Problemas en BD");
					
					}

				} catch (Exception e) {

					resultado.setBoResultado(false);
					resultado
							.setStrDesError("Problemas Ejecutando Funcion Generica");
					/*
					 * logger.log( Level.SEVERE,
					 * "Error ejecutando la función, Error " + e.toString());
					 */

				

			

		

		} finally {

			try {
				// pool = null;
				cstmt1.clearParameters();
				cstmt1.close();
				cstmt1 = null;
			//	releaseConnection(objetoConexion, "executeFunction", "0");
			} catch (SQLException sql) {
				/*
				 * logger.log( Level.SEVERE,
				 * "==>error no se puede cerrar la conexion, Mensaje:" +
				 * sql.toString());
				 */
				sql.printStackTrace();
			}
		}
		return resultado;
	}
	
	
	
	
	
	public ArrayList<LogCambio> consultaLog()
		    throws SQLException {
		
		
		Respuesta resultado = new Respuesta();
		ConnectionPool pool = null;
		Conexion objetoConexion = null;
		Connection conn = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManager.getInstance();
		String respuesta = "";
		
		ArrayList<LogCambio> listaLog = new ArrayList<LogCambio>();
		
		pool = ConnectionPool.getInstance(usuario,clave, conexion);
		objetoConexion = pool.getConnection();

		conn = objetoConexion.getConnection();

		    Statement stmt = null;
		    String query = "select * " +
		                   "from LOG_CAMBIO_DB";
		    try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		            String tabla = rs.getString("TABLA");
		            String columna = rs.getString("COLUMNA");
		            String huellaOld = rs.getString("HUELLA_ANTIGUA");
		            String fechaHuellaAnt = rs.getString("FECHA_HUELLA_ANTIGUA").substring(0, 10);
		            String huellaNueva = rs.getString("HUELLA_NUEVA");
		            String fechaHuellaNueva = rs.getString("FECHA_HUELLA_NUEVA").substring(0, 10);
		            String fechaAud = rs.getString("FECHA_AUD").substring(0, 10);
		            
		            
		            LogCambio logCambio = new LogCambio(tabla, columna, huellaOld, fechaHuellaAnt, huellaNueva, fechaHuellaNueva, fechaAud);
		            
		            listaLog.add(logCambio);
		            
		        }
		        
		    
		    } catch (SQLException e ) {
		        e.printStackTrace();
		    } finally {
		        if (stmt != null) { stmt.close(); }
		        return listaLog;
		    }
		   
		}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

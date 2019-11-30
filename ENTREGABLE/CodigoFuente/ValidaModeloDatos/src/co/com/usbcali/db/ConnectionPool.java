package co.com.usbcali.db;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;




public class ConnectionPool {
	
	
	private int minConnections = 1;

	private int maxConnections = 10;

	private int connectionsPerPart = 3;

	private boolean boInicializado = false;

	private static ConnectionPool connectionPool = null;

	private BoneCP boneCPPool = null;
	
	

	
	
	private ConnectionPool(String usuario, String clave, String conexion) {

		try {

			
			
			Class.forName("oracle.jdbc.OracleDriver");

			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(conexion);
			config.setUsername(usuario);
			config.setPassword(clave);
			
			

			config.setMinConnectionsPerPartition(minConnections);
			config.setMaxConnectionsPerPartition(maxConnections);
			config.setDisableJMX(true);
			config.setAcquireIncrement(1);
			config.setReleaseHelperThreads(0);
			config.setPartitionCount(connectionsPerPart);
			config.setLazyInit(true);
			config.setCloseConnectionWatch(false);
			config.setIdleConnectionTestPeriodInSeconds(15000);
			config.setIdleMaxAgeInSeconds(30);

			boneCPPool = new BoneCP(config);

				/*

				LogsManager.getInstance().info(
						"pool de conexiones inicializado correctamente");
				LogsManager.getInstance().info(
						"min-connections: " + minConnections);
				LogsManager.getInstance().info(
						"max-connections: " + maxConnections);
				LogsManager.getInstance().info(
						"connections-partition: " + connectionsPerPart);*/
	
		} catch (Exception e) {
			e.printStackTrace();

		/*	LogsManager.getInstance().info(
					"==>ConnectionPool Exception" + e.getMessage());*/

		

		}
	}

	public static void cerrarConexion(ConnectionPool pool, Conexion objcon,
			CallableStatement cstm, PreparedStatement preStm, Statement stmt) {
		Connection con = objcon.getConnection();

		try {
			if (cstm != null) {
				cstm.close();
			}

		} catch (Exception e) {
		/*	CtrlUtilidad.debug("Error cerrando CallableStatement "
					+ e.toString() + "\nTraza de error ");*/

		}

		try {
			if (preStm != null) {
				preStm.close();
			}

		} catch (Exception e) {
			/*CtrlUtilidad.debug("Error cerrando PreparedStatement "
					+ e.toString() + "\nTraza de error ");*/

		}

		try {

			if (stmt != null) {
				stmt.close();
			}

		} catch (Exception e) {
			/*CtrlUtilidad.debug("Error cerrando Statement " + e.toString()
					+ "\nTraza de error ");*/

		}

		try {
			if (pool != null && con != null) {
				con.close();
			}

		} catch (Exception e) {
		/*	CtrlUtilidad.debug("Error cerrando pool de conexiones "
					+ e.toString() + "\nTraza de error ");*/
			e.printStackTrace();
		}

	}

	public static ConnectionPool getInstance(String usuario, String clave, String conexion) {

		if (connectionPool == null) {
			iniciaPool(usuario, clave, conexion);
		}

		return connectionPool;

	}

	private synchronized static void iniciaPool(String usuario, String clave, String conexion) {
		connectionPool = new ConnectionPool(usuario, clave, conexion);
	}

	public boolean estaInit() {
		return boInicializado;
	}

	public synchronized Conexion getConnection() throws SQLException {

		long ini = System.currentTimeMillis();


		Connection con = boneCPPool.getConnection();

		long fin = System.currentTimeMillis();
		
		
		Conexion objcon = null;
		objcon = new Conexion();
		objcon.setConnection(con);

		return objcon;
	}

	public BoneCP getBoneCPPool() {
		return boneCPPool;
	}

	
	
	
}

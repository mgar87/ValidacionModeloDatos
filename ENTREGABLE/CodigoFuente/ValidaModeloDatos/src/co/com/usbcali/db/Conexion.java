package co.com.usbcali.db;


import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
	private String id = null;

	private Connection con = null;

	private long lTime;

	/** Creates a new instance of ObjConexion */
	public Conexion()
	{
		super();
	}

	public Connection getConnection()
	{
		return con;
	}

	public void setConnection(Connection con)
	{
		this.con = con;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public long getTime()
	{
		return lTime;
	}

	public void setTime(long ltime)
	{
		lTime = ltime;
	}
	
	public void closeConnection() throws SQLException{
		con.close();
	}
}

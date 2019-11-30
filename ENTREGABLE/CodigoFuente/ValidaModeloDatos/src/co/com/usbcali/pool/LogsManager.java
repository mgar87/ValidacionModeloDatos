package co.com.usbcali.pool;



import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 *
 * @author Pc
 */
public class LogsManager
{

	private static Logger logger = null;

	private static boolean boEncrypt = false;

	private static String key = "07111996";

	private static Hashtable<String, Logger> hLoggers = new Hashtable<String, Logger>(
			1, 1);

	private static String strPathLog = "D:\\TEMP";

	//private static String strPathServer = CtrlUtilidad.getInstancePath();

	/** Creates a new instance of IpManager */

	public LogsManager()
	{
	}

	public static Logger getInstance()
	{

		logger = hLoggers.get(obtenerFechaHoraActual(false));

		if (logger == null)
		{
		
			 logger = crearArchivo();
		}

		return logger;
	}

	public static boolean isEncrypt()
	{
		return boEncrypt;
	}

	public static String getKey()
	{
		return key;
	}
	
    /**
     * Metodo donde se crea el archivo de log, esta sincronizado para evitar que varios hilos de
     * ejecucion creen varios archivos para la misma hora
     * @return
     */
    public static synchronized Logger crearArchivo(){

    	 
         Logger loggerTmp = (Logger)hLoggers.get(obtenerFechaHoraActual(false));

         if (loggerTmp == null){
        	 try
 			{
        		 loggerTmp = Logger.getLogger(obtenerFechaHoraActual(false));

 				/*if (strPathServer == null)
 				{
 					// strPathServer = PathApplication.getInstance();
 					strPathServer = CtrlUtilidad.getInstancePath();
 				}*/

 				// LogManager.getLogManager( ).readConfiguration( new
 				// FileInputStream( strPathServer+"logging.properties" ) );
 				//InputStream url = LogsManager.class.getResourceAsStream(ServerConstants.LOGGER_PROP);

 				//LogManager.getLogManager().readConfiguration(url);

 				// Comentado imprime por consola los loggs
 				loggerTmp.setUseParentHandlers(false);

 				if (strPathLog == null)
 				{
 					strPathLog = "D:\\TEMP";
 				}

 				// System.out.println("Path log =" + strPathLog);
 				// logger.addHandler( new java.util.logging.FileHandler(
 				// strPathLog + General.obtenerFechaHoraActual( false ) +
 				// ".log", true ) );

 				loggerTmp.addHandler(new FileHandler(strPathLog + obtenerFechaHoraActual(false) + ".log", true));

 				// logger.addHandler( new java.util.logging.FileHandler(
 				// General.retornarValorXML("configserver.xml", "path_log" ) +
 				// General.obtenerFechaHoraActual( false ) + ".log", true ) );
 				hLoggers
 						.put(obtenerFechaHoraActual(false), loggerTmp);

 			}
 			catch (SecurityException e)
 			{
 				System.out
 						.println("SecurityException.Error subiendo el manager del logger, el error es: "
 								+ e.toString());
 				// General.registra_error_log_archivo(General.obtenerFechaHoraActual(
 				// true ) + "==>Error subiendo el manager del logger, el error
 				// es:Traza: "+ General.obtenerTraza(e));
 			}
 			catch (IOException e)
 			{
 				System.out
 						.println("IOException.Error subiendo el manager del logger, el error es: "
 								+ e.toString());
 				// General.registra_error_log_archivo(General.obtenerFechaHoraActual(
 				// true ) + "==>Error subiendo el manager del logger, el error
 				// es:Traza: "+ General.obtenerTraza(e));
 			}



               
        }

        return loggerTmp;

    }
    
    
    
    
    
 // Metodo que retorna la hora actual
 	public static String obtenerFechaHoraActual(boolean separador)
 	{
 		String hora = "";
 		String fecha = "";
 		String mill = "";
 		Calendar calendario = Calendar.getInstance();

 		if (separador)
 		{
 			fecha = (calendario.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "")
 					+ calendario.get(Calendar.DAY_OF_MONTH) + "/"
 					+ ((calendario.get(Calendar.MONTH) + 1) < 10 ? "0" : "")
 					+ (calendario.get(Calendar.MONTH) + 1) + "/"
 					+ calendario.get(Calendar.YEAR);
 			hora = (calendario.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
 					+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
 					+ (calendario.get(Calendar.MINUTE) < 10 ? "0" : "")
 					+ calendario.get(Calendar.MINUTE) + ":"
 					+ (calendario.get(Calendar.SECOND) < 10 ? "0" : "")
 					+ calendario.get(Calendar.SECOND);

 			return fecha + " " + hora;
 		}
 		else
 		{
 			fecha = (calendario.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "")
 					+ calendario.get(Calendar.DAY_OF_MONTH)
 					+ ((calendario.get(Calendar.MONTH) + 1) < 10 ? "0" : "")
 					+ (calendario.get(Calendar.MONTH) + 1)
 					+ calendario.get(Calendar.YEAR);
 			hora = (calendario.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
 					+ calendario.get(Calendar.HOUR_OF_DAY);

 			return fecha + "_" + hora;
 		}

 	}
    
}

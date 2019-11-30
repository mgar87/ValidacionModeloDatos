package co.com.usbcali.principal;

import java.awt.BorderLayout;
import java.util.ArrayList;
//from   ww w .  java  2 s. co m
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import co.com.usbcali.control.CtrlValidaHuella;
import co.com.usbcali.db.LogCambio;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerificacionModeloDatos extends JFrame {
	JTable mitabla1;
	JScrollPane mibarra1;
	String usuario;
	String clave;
	String conexion;
  
  
  ArrayList<LogCambio> lista;

  public VerificacionModeloDatos(String usuario, String clave, String conexion, JFrame principal) {    
   
	  this.usuario = usuario;
	  this.clave = clave;
	  this.conexion = conexion;
	  
		mibarra1 = new JScrollPane();
		mibarra1.setBounds(10, 11, 770, 130);
		getContentPane().add(mibarra1);
    
    mostrarDatosUsandoLogica();

    setSize(800, 237);
	setTitle("Verificacion Modelo Datos");
	setLocationRelativeTo(null);
	setResizable(false);
	getContentPane().setLayout(null);
	
	JButton btnRegresar = new JButton("Regresar");
	btnRegresar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			principal.setVisible(true);
			salir();
			
		}
	});
	btnRegresar.setBounds(10, 159, 89, 23);
	getContentPane().add(btnRegresar);
  }

 
  
  
  public void mostrarDatosUsandoLogica() {

		
		
		
		String titulos[] = { "Tabla", "Columna", "Huella_antigua", "Fecha_huella_antigua",
				"Huella_nueva","Fecha_huella_nueva","Fecha_auditoria" };
		String informacion[][] = obtieneMariz();// obtenemos la informacion de
												// la BD

		mitabla1 = new JTable(informacion, titulos);
		mitabla1.setEnabled(true);
		mitabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		mibarra1.setViewportView(mitabla1);
		mitabla1.setFillsViewportHeight(true);
		
		int[] columnsWidth = {
	            150, 100, 470, 150, 470, 150, 100 
	        };
		
		int i = 0;
      for (int width : columnsWidth) {
          TableColumn column = mitabla1.getColumnModel().getColumn(i++);
          column.setMinWidth(width);
          column.setMaxWidth(width);
          column.setPreferredWidth(width);
      }
		
	}
  
  
  private String[][] obtieneMariz() {
		
		
		 CtrlValidaHuella valida = new CtrlValidaHuella();
		
		ArrayList<LogCambio> lista = valida.Validar(usuario, clave, conexion);
	
		
		String informacion[][] = new String[lista.size()][7];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = lista.get(x).getTabla() + "";
			informacion[x][1] = lista.get(x).getColumna() + "";
			informacion[x][2] = lista.get(x).getHuellaOld() + "";
			informacion[x][3] = lista.get(x).getFechaHuellaAnt() + "";
			informacion[x][4] = lista.get(x).getHuellaNueva() + "";
			informacion[x][5] = lista.get(x).getFechaHuellaNueva() + "";
			informacion[x][6] = lista.get(x).getFechaAud() + "";
		}
		return informacion;
	}
  
  public void salir() {
		this.dispose();
	}

  public static void main(String[] args) {
   // new Main();
  }
}
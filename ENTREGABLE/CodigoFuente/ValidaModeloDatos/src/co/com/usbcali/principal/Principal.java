package co.com.usbcali.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.com.usbcali.control.CtrlRegistrarHuella;
import co.com.usbcali.control.CtrlValidaHuella;
import co.com.usbcali.db.LogCambio;
import co.com.usbcali.pool.Respuesta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Validaci\u00F3n Modelo Datos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel respuesta = new JLabel("");
		respuesta.setBounds(10, 148, 203, 14);
		contentPane.add(respuesta);
		
		
		
		JButton btnNewButton = new JButton("Validar Esquema");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CtrlRegistrarHuella registraHuella = new CtrlRegistrarHuella();
				
				registraHuella.Registrar(textField.getText(), textField_1.getText(), textField_2.getText());
				
				respuesta.setText("Proceso terminado!!");
				
			}
		});
		btnNewButton.setBounds(10, 114, 146, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(133, 21, 128, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 24, 46, 14);
		contentPane.add(lblUsuario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(133, 55, 128, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(10, 58, 46, 14);
		contentPane.add(lblClave);
		
		JLabel lblCadenaConexin = new JLabel("Cadena conexi\u00F3n");
		lblCadenaConexin.setBounds(10, 89, 112, 14);
		contentPane.add(lblCadenaConexin);
		
		textField_2 = new JTextField();
		textField_2.setBounds(133, 86, 269, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnVerificarResulltado = new JButton("Verificar Resulltado");
		btnVerificarResulltado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				verificacion();
				
			}
		});
		btnVerificarResulltado.setBounds(10, 173, 146, 23);
		contentPane.add(btnVerificarResulltado);
		
		
	}
	
	public void verificacion () {
		
		
		VerificacionModeloDatos m = new VerificacionModeloDatos(textField.getText(), textField_1.getText(), textField_2.getText(), this);
		m.setVisible(true);
		this.setVisible(false);
	}
	
	
}

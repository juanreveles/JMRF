package cimat.tesis.sna.visualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cimat.tesis.sna.io.databases.Conexion;

public class config_user {
	
	
	



	//public void 
	
	
		//Create Labels
		JLabel user          = new JLabel("User                     ");
		JLabel database_name = new JLabel("Name database");
		JLabel password      = new JLabel("Password          ");
		JLabel driver_path   = new JLabel("Driver path       ");
		JLabel driver_type   = new JLabel("Driver type       ");
		JLabel root_path     = new JLabel("Root path         ");
	    
			//Create  JTextField
		JTextField user1          = new JTextField (20);
		JTextField database_name1 = new JTextField (20);
		JTextField password1      = new JTextField (20);
		JTextField driver_path1   = new JTextField (20);
		JTextField driver_type1   = new JTextField (20);
		JTextField root_path1  = new JTextField (15);
		String Ruta = null;
		
	public void getdate(){
	String SQL2 = "SELECT conf.database_name, conf.user, conf.password, " +
			"conf.driver_path, conf.driver_type, conf.root_path  FROM config WHERE id_user =1234; ";
	System.out.println(SQL2);
	//Conexion  con = new Conexion();
	/*
	// necesito instancia para hace la consulta y que me de el resultado para así pasarlo a las variables;
	
	ResultSet rs = con.consultar(SQL2);
	this.name_database1.setText(rs.getString(1));
	this.user1.setText(rs.getString(2));
	this.password1.setText(rs.getString(3));
	this.driver_path1.setText(rs.getString(4));
	this.driver_type1.setText(rs.getString(5));
	this.root_path1.setText(rs.getString(6));
	
	*/
	this.user1.setText("Hola ");
	this.password1.setText("Espero");
	this.database_name1.setText("Poder");
	this.driver_path1.setText("Asignar ");
	this.driver_type1.setText("Los ");
	this.root_path1.setText("Datos");
	
	}
	public void modify(){
		Component frame = null;
		
	String user2 = user1.getText().toString();
	
	if ( user2.length() <= 50 && user2.length() > 0){
	String password2 = password1.getText().toString();
	
	if (password2.length() <= 50){
	String database_name2 = database_name1.getText().toString();
	
	if (database_name2.length() <= 50 && database_name2.length() > 0){
	String driver_path2 = driver_path1.getText().toString(); 
	
	if (driver_path2.length() <= 100 && driver_path2.length() > 0){
	String driver_type2 = driver_type1.getText().toString(); 
	
	if (driver_type2.length() <= 100 && driver_type2.length() > 0){
	String root_path2 = root_path1.getText().toString(); 
	
	if (root_path2.length() <= 500 && root_path2.length() > 0) {
	String SQL = "update config set   database_name = \"" + database_name2 + "\" , user = \""+ user2 +"\",password = \""
	+ password2 +"\",driver_path = \""+ driver_path2 +"\", driver_type = \"" + driver_type2 +"\", root_path = \"" 
	+ root_path2 +  "\" where id_user =  1234;";
		
		System.out.println(SQL);
		Clear();
	} else {JOptionPane.showMessageDialog(frame, "The root path has more than 500 characters or is  empty\n Please verify the  information");}
	} else {JOptionPane.showMessageDialog(frame, "The driver type has more than 100 characters or is  empty\n Please verify the  information");}	
	} else {JOptionPane.showMessageDialog(frame, "The driver path has more than 100 characters or is  empty\n Please verify the  information");}
	} else {JOptionPane.showMessageDialog(frame, "The database name has more than 50 characters or is  empty\n Please verify the  information");}
	} else {JOptionPane.showMessageDialog(frame, "The password has more than 50 characters ");}
	} else {JOptionPane.showMessageDialog(frame, "The user has more than 50 characters or is  empty\n Please verify the  information");}
	}
		
	public void Clear(){
		this.user1.setText("");
		this.password1.setText("");
		this.database_name1.setText("");
		this.driver_path1.setText("");
		this.driver_type1.setText("");
		this.root_path1.setText("");
		
	}
	public void open_config (){
		JFrame conf = new JFrame(); 
		JPanel panel1 = new JPanel();
		panel1.setSize(300,350);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,40));
					
		
	
		
		//Button for FileChooser
		
		JButton b = new JButton ("...");
		b.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent evento ){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);// return an int

					if (returnValue == JFileChooser.APPROVE_OPTION) {
					
					 Ruta = fileChooser.getSelectedFile().toString();
					root_path1.setText(Ruta);
			} 
			}
			});
		
		JButton Save = new JButton ("Save configuration");
		Save.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent evento ){
			 modify();
			}
			});
		
		
		// add Labels
		panel1.add(database_name);
		panel1.add(database_name1);
		panel1.add(user);
		panel1.add(user1);
		panel1.add(password);
		panel1.add(password1);
		panel1.add(driver_path);
		panel1.add(driver_path1);
		panel1.add(driver_type);
		panel1.add(driver_type1);
		panel1.add(root_path);
		panel1.add(root_path1);
		panel1.add(b);
		panel1.add(Save);
		
		panel1.setVisible(true);
		
				
		conf.add(panel1,BorderLayout.CENTER);
		conf.setVisible(true);
		conf.setSize(360,500);
		conf.setResizable(false);
		//frame.pack();
		conf.setLocationRelativeTo(null);
		conf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
		
		public static void main (String [] args){
			config_user a = new config_user();
			
			a.open_config();
			a.getdate();
	
		
	}
	
}

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

class prueba {
	static public void main (String args[]) {
		try{
			
			/**********CONSULTA BASE DE DATOS*****************/	
			//Comprueba si existe la clase necesaria, y se conecta a la base de datos
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/servicio_web","david","david");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//Obtiene todos los datos almacenados en la tabla de partidas de la base de datos, ordenadas por partidas ganadas en orden ascendiente
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
				
			/*************FIN DE CONSULTA**********************/
			
			//Obtenemos el numero de filas
			rs.last();
			int num_filas = rs.getRow();
			
			System.out.println("Numero de filas: " + num_filas);
			
			rs.first();	
			
			do{																	//Creamos un bucle que recorra el ResultSet, y almacene los elementos en cadena
				System.out.println("-> " + rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3));

			}while(rs.next());
				
		}
		catch (Exception exth){
			System.out.println(exth.toString());
		}
	}
}
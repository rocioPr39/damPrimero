package data;

import entrada.teclado.Tec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertaDepartamento {

	public static void main(String[] args) {

		Connection conexion = null;
		PreparedStatement sentencia = null;

		try {

			// Solicitamos los datos
			byte numDep = Tec.leerByte("Introduce el número del departamento: ");
			String nombre = Tec.leerCadena("Introduce el nombre del departamento: ");
			String ciudad = Tec.leerCadena("Introduce la ciudad del departamento: ");

			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecemos la conexion con la BD
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplojdbc", "ventura", "ventura");

			// construir orden INSERT
			String sql = "INSERT INTO departamentos VALUES ( ?, ?, ? )";

			sentencia = conexion.prepareStatement(sql);
			sentencia.setShort(1, numDep);
			sentencia.setString(2, nombre);
			sentencia.setString(3, ciudad);

			System.out.println("Filas afectadas: " + sentencia.executeUpdate());

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
			System.out.println("Mensaje:    "+ e.getMessage());
			System.out.println("SQL estado: "+ e.getSQLState());
			System.out.println("Cód error:  "+ e.getErrorCode());
		}
		finally {
			try {
				if (sentencia != null) sentencia.close();
				if (conexion != null) conexion.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}// fin de main
}// fin de la clase
package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia {
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) {
		
		int id = 0;
		String sqlSelect = "SELECT * FROM Clientes";
		
		Connection con = ClientePersistencia.conectar();
		Statement sta;
		try {
			sta = con.createStatement();
			String cadenaSQL = "INSERT INTO Clientes VALUES (null, '" + nombre + "', '" + apellidos + "', '" + email + "', '" + dni + "', '" + clave + "');";
			sta.executeUpdate(cadenaSQL);
			ResultSet rs = sta.executeQuery(sqlSelect);
			while (rs.next()) {
				id = rs.getInt("idClientes");
			}
			rs.close();
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desconectar(con);
		return id;
	}
	
	public static String readCliente(int idCliente, String campo) {
		
		
		String data= "";
		String sqlSelect = "SELECT " + campo + " FROM Clientes WHERE idClientes =" + idCliente;
		
		Connection con = ClientePersistencia.conectar();
		Statement sta;
		
		try {
			sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sqlSelect);
			while (rs.next()) {
				data = rs.getString(campo);
			}
			rs.close();
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String devolucion = idCliente + ", " + data;
		desconectar(con);
		return devolucion;
	}
 
	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
		
		Connection con = ClientePersistencia.conectar();
		Statement sta;
		try {
			sta = con.createStatement();
			String cadenaSQL = "UPDATE Clientes SET " + campo + "= '" + nuevoValor + "' WHERE idClientes = " + idCliente;
			sta.executeUpdate(cadenaSQL);
			sta.close();
			desconectar(con);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			desconectar(con);
			return false;
		}
		
	}
 
	public static boolean deleteCliente(int idCliente) {
		
		Connection con = ClientePersistencia.conectar();
		Statement sta;
		
		try {
			sta = con.createStatement();
			String sql = "DELETE FROM Clientes WHERE idClientes = " + idCliente;
			sta.executeUpdate(sql);
			sta.close();
			desconectar(con);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			desconectar(con);
			return false;
		}
		
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
	}
	
	public static Connection conectar() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "10101998fB";
		Connection con = null;
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD empresa
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Conectado a la base de datos");
			}
		} catch (SQLException ex) {
			System.out.println("ERROR:La dirección no es válida o el usuario y clave");
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		return con;
	}
	
	public static void desconectar(Connection con) {
		try
		{
			con.close();
		}
		catch(Exception e) {}
	}

}

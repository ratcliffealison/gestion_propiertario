package registration_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtilities {
	// JDBC URL, userName and password of MySQL server
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static Connection obtenerConexion() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public static void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static Statement crearStatement(Connection conexion) {
		Statement statement = null;
		try {
			statement = conexion.createStatement();
		} catch (SQLException e) {
			System.out.println("No se ha creado el statement.");
			e.printStackTrace();
		}
		return statement;
	}

	public static void cerrarStatement(Statement miStatement) {
		try {

			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PreparedStatement crearPreparedStatement(Connection conexion, String query) {
		PreparedStatement miPreparedStatement = null;
		try {
			miPreparedStatement = conexion.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miPreparedStatement;
	}

	public static void cerrarPreparedStatement(PreparedStatement miPreparedState) {
		try {
			miPreparedState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cerrarResultSet(ResultSet miResultSet) {
		try {
			miResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(Statement miStatement, String query) {
		ResultSet miResult = null;
		try {
			miResult = miStatement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miResult;
	}

	public static void executeUpdate(Statement miStatement, String query) {
		try {
			miStatement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getHighestID(Statement miStatement) {
		System.out.println();
		int highest = 0;
		String query = "SELECT MAX(id) AS highest_id FROM (SELECT id FROM gastos UNION SELECT id FROM ingresos) AS combined_ids;";
		try {
			ResultSet miResult = executeQuery(miStatement, query);

			if (miResult.next()) {
			
				highest = miResult.getInt("highest_id");
	
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return highest;
	}

	public static void deleteRegistro(Registro registro, int id) {
		// TODO Auto-generated method stub
		String tipo = registro.getTipo().toLowerCase();
		String concepto = registro.getConcepto();
		int cantidad = registro.getCantidad();
		String fecha = registro.getFecha();

		Connection conexion;
		try {
			conexion = obtenerConexion();
			String query = "DELETE FROM " + tipo + "s WHERE id = " + id + " AND concepto = '" + concepto
					+ "' AND cantidad = " + cantidad + " AND fecha = '" + fecha + "'";
			System.out.println(query);
			Statement statement = DatabaseUtilities.crearStatement(conexion);
			DatabaseUtilities.executeUpdate(statement, "USE gestion_movimientos");
			DatabaseUtilities.executeUpdate(statement, query);
			DatabaseUtilities.cerrarStatement(statement);
		
			DatabaseUtilities.cerrarConexion(conexion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	};

}

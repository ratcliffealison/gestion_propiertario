package registration_system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitialiser {

	// Database and table names
	private static final String DATABASE_NAME = "gestion_movimientos";
	private static final String GASTOS_TABLE = "gastos";
	private static final String INGRESOS_TABLE = "ingresos";

	public static void crearDatabase() {
		try (Connection connection = DatabaseUtilities.obtenerConexion()) {

			// Create database if not exists
			createDatabaseIfNotExists(connection, DATABASE_NAME);

			// Use the database
			connection.setCatalog(DATABASE_NAME);

			// Create tables if not exists
			createTableIfNotExists(connection, GASTOS_TABLE);
			createTableIfNotExists(connection, INGRESOS_TABLE);

			System.out.println("Database and tables initialized successfully.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}

	protected static List<Registro> sacarDatos() throws Exception {
		Connection connection = DatabaseUtilities.obtenerConexion();
	
		List<Registro> dataList = new ArrayList<>();

		ResultSet resultSetStart;
		String query = "SELECT id, concepto, cantidad, fecha, 'Gasto' as tipo FROM gastos UNION ALL SELECT id, concepto, cantidad, fecha, 'Ingreso' as tipo FROM INGRESOS ORDER BY id";
		Statement statement = DatabaseUtilities.crearStatement(connection);
		DatabaseUtilities.executeUpdate(statement, "USE " + DATABASE_NAME);
		resultSetStart = DatabaseUtilities.executeQuery(statement, query);

		while (resultSetStart.next()) {

			// Extract data from the ResultSet
			int id = resultSetStart.getInt("id");
			String concepto = resultSetStart.getString("concepto");
			String cantidad = String.valueOf(resultSetStart.getInt("cantidad"));
			String tipo = resultSetStart.getString("tipo");
		
			String fecha = resultSetStart.getString("fecha");
			Registro data = new Registro(id, tipo, concepto, cantidad, fecha);
			dataList.add(data);
		}
		DatabaseUtilities.cerrarConexion(connection);
		return dataList;
	}

	private static void createDatabaseIfNotExists(Connection connection, String databaseName) throws SQLException {
		String query = "CREATE DATABASE IF NOT EXISTS " + databaseName;
		Statement statement = DatabaseUtilities.crearStatement(connection);
		DatabaseUtilities.executeUpdate(statement, query);

	}

	private static void createTableIfNotExists(Connection connection, String tableName) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + tableName
				+ " (id INT AUTO_INCREMENT PRIMARY KEY, table_key int, concepto varchar(50), cantidad int, fecha date)";
		Statement statement = DatabaseUtilities.crearStatement(connection);
		DatabaseUtilities.executeUpdate(statement, query);

	}
	
	
}

package registration_system;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class RegistrosUtilities {
	private static ArrayList<Registro> registros = new ArrayList<Registro>();

	public static ArrayList<Registro> getRegistros() {
		return registros;
	}

	public static void setRegistros(ArrayList<Registro> registros) {
		RegistrosUtilities.registros = registros;
	}

	public static Registro createRegistro(String inputCantidad, String inputFecha, String inputConcepto,
			String inputTipo) throws Exception {
		// llamar a SQL y pedirle numero de ID ACTAL mas alto. Sumarle 1 y crear nuevo
		// registro con eso
		Connection connection = DatabaseUtilities.obtenerConexion();
		Statement statement = DatabaseUtilities.crearStatement(connection);
		DatabaseUtilities.executeUpdate(statement, "USE gestion_movimientos");

		int id = DatabaseUtilities.getHighestID(statement);
		DatabaseUtilities.cerrarConexion(connection);
		Registro newRegistro = new Registro(id + 1, inputTipo, inputConcepto, inputCantidad, inputFecha);
		
		return newRegistro;
	}

	public static boolean agregarRegistro(Registro registro) throws SQLException {

		agregarAArrayList(registro);
		Connection conexion = DatabaseUtilities.obtenerConexion();
		int id;
		String query;
		String fecha;
		int cantidad;
		String concepto;
		String tipo;

		try {
			// Preparar la consulta SQL para insertar el registro en la tabla adecuada
			query = "";
			id = registro.getId();
			tipo = registro.getTipo().toLowerCase();
			concepto = registro.getConcepto();
			cantidad = registro.getCantidad();
			fecha = registro.getFecha();

			query += "INSERT INTO " + tipo + "s(id, concepto, cantidad, fecha) VALUE (" + id + ", '" + concepto + "', "
					+ cantidad + ", '" + fecha + "')";
			Statement statement = DatabaseUtilities.crearStatement(conexion);
			DatabaseUtilities.executeUpdate(statement, "USE gestion_movimientos");
			DatabaseUtilities.executeUpdate(statement, query);

			DatabaseUtilities.cerrarStatement(statement);
			DatabaseUtilities.cerrarConexion(conexion);
		} finally {

		}
		return (true);
	}

	public static boolean eliminarRegistro(int id) throws SQLException {
		for (Registro registro : registros) {
			if (registro.getId() == id) {
				try {
					registros.remove(registro);
					
					DatabaseUtilities.deleteRegistro(registro, id);

				} catch (Exception e) {
					e.printStackTrace();
				}
				;
				return true;
			}
		}
		return false;
	}

	public static void mostrarRegistros(DefaultTableModel model) {

		model.setRowCount(0);

		for (Registro currentRegistro : registros) {
			model.addRow(new Object[] { currentRegistro.getId(), currentRegistro.getTipo(),
					currentRegistro.getConcepto(), currentRegistro.getCantidad(), currentRegistro.getFecha() });
		}

	}

	public static void agregarAArrayList(Registro registroActual) {
		registros.add(registroActual);

	}
}

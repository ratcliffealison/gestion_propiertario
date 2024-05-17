package registration_system;

import java.awt.EventQueue;
import java.util.List;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Connect to the database
					DatabaseInitialiser.crearDatabase();

					// Select all from database if there is anything
					List<Registro> puntoPartida = DatabaseInitialiser.sacarDatos();
					if (puntoPartida != null) {
						for (Registro registroActual : puntoPartida) {
							RegistrosUtilities.agregarAArrayList(registroActual);
						}
					}
						// Open RegistrationSystem
						RegistrationSystem frame = new RegistrationSystem();
						frame.setVisible(true);
						
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

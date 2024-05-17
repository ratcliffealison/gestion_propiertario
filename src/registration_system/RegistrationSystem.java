package registration_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class RegistrationSystem extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnlTitle, pnlInputs, pnlControls, pnlOutput;
	private JFrame frame;
	private JTable table;
	private JComboBox<String> comboBoxTipo, comboBoxConcepto;
	private JTextField tfConceptoOtro, tfCantidad;
	private JLabel lblFecha, lblConcepto, lblCantidad, lblTipo;
	private JScrollPane scrollPane;
	private JButton btnReset, btnAnadirRegistro, btnBorrarRegistro, btnSalir, btnImprimir;
	private String[] ingresoConcepts = { "Selecciona:", "Alquiler", "Seguro", "Otro" };
	private String[] gastoConcepts = { "Selecciona:", "Hipoteca", "Seguro", "IBI", "Comunidad", "Agua y basuras", "Luz",
			"Reparaciones", "Otro" };
	private String[] tipoOptions = { "Selecciona:", "Ingreso", "Gasto" };
	private JTextField tfFecha;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public RegistrationSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(95, 158, 160)));
		panel.setBounds(24, 11, 1350, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		pnlTitle = new JPanel();
		pnlTitle.setBounds(23, 23, 1303, 118);
		panel.add(pnlTitle);
		pnlTitle.setLayout(null);
		pnlTitle.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(95, 158, 160)));
		pnlTitle.setBackground(new Color(176, 224, 230));

		JLabel lblTitle = new JLabel("Ingresos y Gastos");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 139, 139));
		lblTitle.setFont(new Font("Verdana Pro Cond", Font.BOLD, 40));
		lblTitle.setBounds(21, 11, 1245, 96);
		pnlTitle.add(lblTitle);

		pnlInputs = new JPanel();
		pnlInputs.setLayout(null);
		pnlInputs.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(95, 158, 160)));
		pnlInputs.setBackground(new Color(176, 224, 230));
		pnlInputs.setBounds(23, 152, 446, 432);
		panel.add(pnlInputs);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(new Color(0, 139, 139));
		lblFecha.setFont(new Font("Verdana Pro Cond Light", Font.PLAIN, 25));
		lblFecha.setBounds(42, 300, 159, 46);
		pnlInputs.add(lblFecha);

		lblConcepto = new JLabel("Concepto:");
		lblConcepto.setForeground(new Color(0, 139, 139));
		lblConcepto.setFont(new Font("Verdana Pro Cond Light", Font.PLAIN, 25));
		lblConcepto.setBounds(42, 106, 159, 46);
		pnlInputs.add(lblConcepto);

		lblCantidad = new JLabel("Importe:");
		lblCantidad.setForeground(new Color(0, 139, 139));
		lblCantidad.setFont(new Font("Verdana Pro Cond Light", Font.PLAIN, 25));
		lblCantidad.setBounds(42, 221, 159, 46);
		pnlInputs.add(lblCantidad);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(new Color(0, 139, 139));
		lblTipo.setFont(new Font("Verdana Pro Cond Light", Font.PLAIN, 25));
		lblTipo.setBounds(42, 40, 159, 46);
		pnlInputs.add(lblTipo);

		tfCantidad = new JTextField();
		tfCantidad.setBounds(211, 231, 186, 39);
		pnlInputs.add(tfCantidad);
		tfCantidad.setColumns(10);

		tfConceptoOtro = new JTextField();
		tfConceptoOtro.setBounds(211, 166, 186, 37);
		
		tfConceptoOtro.setColumns(10);
		tfConceptoOtro.setEnabled(false);
		pnlInputs.add(tfConceptoOtro);
		
		comboBoxTipo = new JComboBox<>(tipoOptions);
		comboBoxTipo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		comboBoxTipo.setBounds(211, 49, 186, 40);
		pnlInputs.add(comboBoxTipo);

		comboBoxConcepto = new JComboBox<>();
		comboBoxConcepto.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		comboBoxConcepto.setBounds(211, 115, 186, 40);
		comboBoxConcepto.setEnabled(false);

		// Enable or disable Concepto selection box
		comboBoxTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBoxTipo.getSelectedItem();
				if (selectedItem.equals("Gasto")) {
					comboBoxConcepto.setEnabled(true);
					comboBoxConcepto.removeAllItems();
					for (String concept : gastoConcepts) {
						comboBoxConcepto.addItem(concept);
					}
				} else if (selectedItem.equals("Ingreso")) {
					comboBoxConcepto.setEnabled(true);
					comboBoxConcepto.removeAllItems();
					for (String concept : ingresoConcepts) {
						comboBoxConcepto.addItem(concept);
					}
				} else {
					comboBoxConcepto.setEnabled(false);
					comboBoxConcepto.removeAllItems();
				}
			}
		});
		pnlInputs.add(comboBoxConcepto);

		// Enable or disable text field for Otro
		comboBoxConcepto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedConcept = (String) comboBoxConcepto.getSelectedItem();
				if (selectedConcept != null && selectedConcept.equals("Otro")) {
					tfConceptoOtro.setEnabled(true);
				} else {
					tfConceptoOtro.setEnabled(false);
				}
			}
		});

		tfFecha = new JTextField();
		tfFecha.setColumns(10);
		tfFecha.setBounds(211, 357, 186, 37);
		tfFecha.setEnabled(false);
		pnlInputs.add(tfFecha);

		JButton selectDateButton = new JButton("Selecciona fecha");
		selectDateButton.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		selectDateButton.setBounds(211, 305, 186, 41);
		selectDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the button is clicked, create a new DatePick object
				// and set the picked date to the text field
				tfFecha.setText(new DatePick(RegistrationSystem.this).Set_Picked_Date());
			}
		});
		pnlInputs.add(selectDateButton);

		pnlOutput = new JPanel();
		pnlOutput.setLayout(null);
		pnlOutput.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(95, 158, 160)));
		pnlOutput.setBackground(new Color(176, 224, 230));
		pnlOutput.setBounds(478, 151, 848, 432);
		panel.add(pnlOutput);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 25, 788, 378);
		pnlOutput.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Concepto", "Gasto/Ingreso", "Cantidad", "Fecha" }));
		table.getColumnModel().getColumn(4).setPreferredWidth(117);
		scrollPane.setViewportView(table);

		pnlControls = new JPanel();
		pnlControls.setLayout(null);
		pnlControls.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(95, 158, 160)));
		pnlControls.setBackground(new Color(176, 224, 230));
		pnlControls.setBounds(23, 595, 1303, 69);
		panel.add(pnlControls);

		btnAnadirRegistro = new JButton("Añadir Registro");
		btnAnadirRegistro.addActionListener(this);
		btnAnadirRegistro.setBounds(37, 21, 223, 23);
		pnlControls.add(btnAnadirRegistro);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		btnReset.setBounds(263, 21, 89, 23);
		pnlControls.add(btnReset);

		btnBorrarRegistro = new JButton("Borrar registro");
		btnBorrarRegistro.addActionListener(this);
		btnBorrarRegistro.setBounds(457, 21, 214, 23);
		pnlControls.add(btnBorrarRegistro);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(1167, 21, 89, 23);
		pnlControls.add(btnSalir);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(1068, 21, 89, 23);
		pnlControls.add(btnImprimir);

		model = (DefaultTableModel) table.getModel();
		RegistrosUtilities.mostrarRegistros(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnReset) {
			resetFields();
		} else if (e.getSource() == btnSalir) {
			frame = new JFrame();
			if (JOptionPane.showConfirmDialog(frame, "¿Estás seguro que quieres salir?",
					"Sistemas Registro de Transacciones", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				System.exit(0);

			}
		} else if (e.getSource() == btnImprimir) {
			// SAVE TO FILE
			try {
				table.print();
			} catch (java.awt.print.PrinterException ex) {
				System.err.format("No Printer Found", ex.getMessage());
			}
		} else if (e.getSource() == btnAnadirRegistro) {
			
			int rowCount = table.getRowCount();;

			Registro currentRegistro;
			Boolean addedCorrectly;

			String inputCantidad = tfCantidad.getText();
			String inputFecha = tfFecha.getText();
			Object inputConceptoObject = comboBoxConcepto.getSelectedItem();

			if (inputConceptoObject != null) {

				String inputConcepto = comboBoxConcepto.getSelectedItem().toString();
				if (inputConcepto.equals("Otro")) {

					inputConcepto = "Otro " + tfConceptoOtro.getText();
				}

				String inputTipo = comboBoxTipo.getSelectedItem().toString();
 

				if (inputTipo.equals("Selecciona:") || inputCantidad.isBlank() || inputFecha.isBlank()
						|| inputConcepto.equals("Selecciona:")) {
					JOptionPane.showMessageDialog(frame, "Rellena todos los campos",
							"Sistemas Registro de Transacciones", JOptionPane.WARNING_MESSAGE);
				} else {

					try {

						currentRegistro = RegistrosUtilities.createRegistro(inputCantidad, inputFecha, inputConcepto,
								inputTipo);


						addedCorrectly = RegistrosUtilities.agregarRegistro(currentRegistro);
						RegistrosUtilities.mostrarRegistros(model);

					} catch (Exception e1) {

						JOptionPane.showMessageDialog(frame, "Movimiento no se pudo añadir: " + e1.getMessage(),
								"Sistemas Registro de Transacciones", JOptionPane.WARNING_MESSAGE);

						addedCorrectly = false;

					}
					if (addedCorrectly) {
						JOptionPane.showMessageDialog(frame, "Movimiento creado con éxito",
								"Sistemas Registro de Transacciones", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Rellena todos los campos.",
						"Sistemas Registro de Transacciones: Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnBorrarRegistro) {

			if (table.getSelectedRow() == -1) {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "No existen movimientos para borrar ",
							"Sistemas Registro de Transacciones", JOptionPane.WARNING_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "Selecciona el movimiento que quieres borrar ",
							"Membership Management System", JOptionPane.OK_OPTION);
				}
			} else {
				int selectedRowIndex = table.getSelectedRow();
				int selectedID = (int) table.getValueAt(selectedRowIndex, 0);

				// Remove from the ArrayList
				boolean removed = false;
				try {
					removed = RegistrosUtilities.eliminarRegistro(selectedID);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (removed) {
					// Remove from the table
					RegistrosUtilities.mostrarRegistros(model);
					JOptionPane.showMessageDialog(null, "Registro eliminado con éxito",
							"Sistemas Registro de Transacciones: Éxito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro",
							"Sistemas Registro de Transacciones: Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}

	public void resetFields() {
		tfCantidad.setText("");
		tfFecha.setText("");
		comboBoxConcepto.setSelectedItem("Selecciona:");
		comboBoxTipo.setSelectedItem("Selecciona:");
	}

}

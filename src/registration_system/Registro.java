package registration_system;

public class Registro {
	private int id;
	private String tipo;
	private String concepto;
	private int cantidad;
	private String fecha;

	public Registro(int id, String inputTipo, String inputConcepto, String inputCantidad, String inputFecha) throws Exception {
		this.id = id;
		this.tipo = inputTipo;
		this.concepto = inputConcepto;
		try {
			this.cantidad = Integer.parseInt(inputCantidad);
		} catch(Exception e) {
			throw new Exception("La cantidad debe ser numérica");
		}
		this.fecha = inputFecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Registro(int id, String tipo, String concepto, String fecha, int cantidad) {
		this.id = id;
		this.tipo = tipo;
		this.concepto = concepto;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", tipo=" + tipo + ", concepto=" + concepto + ", cantidad=" + cantidad
				+ ", fecha=" + fecha + "]";
	}

}

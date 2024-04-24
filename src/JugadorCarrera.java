
public class JugadorCarrera {
	private int codigo;
	private String nombre;
	private double puntosTotales;

	public JugadorCarrera() {
		// TODO Auto-generated constructor stub
	}

	public JugadorCarrera(int codigo, String nombre, double puntosTotales) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.puntosTotales = puntosTotales;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(double puntosTotales) {
		this.puntosTotales = puntosTotales;
	}
	
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}

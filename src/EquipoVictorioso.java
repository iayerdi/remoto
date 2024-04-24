
public class EquipoVictorioso {
	private String nombre;
	private String temporada;
	private int victorias;

	public EquipoVictorioso() {
		// TODO Auto-generated constructor stub
	}

	public EquipoVictorioso(String nombre, String temporada, int victorias) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.victorias = victorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}
	
}

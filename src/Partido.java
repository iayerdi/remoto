
public class Partido {

	private int codigo;
	private String eLocal;
	private String eVisitante;
	private int puntosLocal;
	private int puntosVisitante;
	private String tenporada;
	
	public Partido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Partido(int codigo, String eLocal, String eVisitante, int puntosLocal, int puntosVisitante,
			String tenporada) {
		super();
		this.codigo = codigo;
		this.eLocal = eLocal;
		this.eVisitante = eVisitante;
		this.puntosLocal = puntosLocal;
		this.puntosVisitante = puntosVisitante;
		this.tenporada = tenporada;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String geteLocal() {
		return eLocal;
	}

	public void seteLocal(String eLocal) {
		this.eLocal = eLocal;
	}

	public String geteVisitante() {
		return eVisitante;
	}

	public void seteVisitante(String eVisitante) {
		this.eVisitante = eVisitante;
	}

	public int getPuntosLocal() {
		return puntosLocal;
	}

	public void setPuntosLocal(int puntosLocal) {
		this.puntosLocal = puntosLocal;
	}

	public int getPuntosVisitante() {
		return puntosVisitante;
	}

	public void setPuntosVisitante(int puntosVisitante) {
		this.puntosVisitante = puntosVisitante;
	}

	public String getTenporada() {
		return tenporada;
	}

	public void setTenporada(String tenporada) {
		this.tenporada = tenporada;
	}
	
	
}

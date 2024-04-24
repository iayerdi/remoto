
public class Estadistica {

	private String temporada;
	private int jugador;
	private double puntosPP;
	private double asistenciasPP;
	private double taponesPP;
	private double rebotesPP;
	/**
	 * 
	 */
	public Estadistica() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param temporada
	 * @param jugador
	 * @param puntosPP
	 * @param asistenciasPP
	 * @param taponesPP
	 * @param rebotesPP
	 */
	public Estadistica(String temporada, int jugador, double puntosPP, double asistenciasPP, double taponesPP,
			double rebotesPP) {
		super();
		this.temporada = temporada;
		this.jugador = jugador;
		this.puntosPP = puntosPP;
		this.asistenciasPP = asistenciasPP;
		this.taponesPP = taponesPP;
		this.rebotesPP = rebotesPP;
	}
	/**
	 * @return the temporada
	 */
	public String getTemporada() {
		return temporada;
	}
	/**
	 * @param temporada the temporada to set
	 */
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	/**
	 * @return the jugador
	 */
	public int getJugador() {
		return jugador;
	}
	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
	/**
	 * @return the puntosPP
	 */
	public double getPuntosPP() {
		return puntosPP;
	}
	/**
	 * @param puntosPP the puntosPP to set
	 */
	public void setPuntosPP(double puntosPP) {
		this.puntosPP = puntosPP;
	}
	/**
	 * @return the asistenciasPP
	 */
	public double getAsistenciasPP() {
		return asistenciasPP;
	}
	/**
	 * @param asistenciasPP the asistenciasPP to set
	 */
	public void setAsistenciasPP(double asistenciasPP) {
		this.asistenciasPP = asistenciasPP;
	}
	/**
	 * @return the taponesPP
	 */
	public double getTaponesPP() {
		return taponesPP;
	}
	/**
	 * @param taponesPP the taponesPP to set
	 */
	public void setTaponesPP(double taponesPP) {
		this.taponesPP = taponesPP;
	}
	/**
	 * @return the rebotesPP
	 */
	public double getRebotesPP() {
		return rebotesPP;
	}
	/**
	 * @param rebotesPP the rebotesPP to set
	 */
	public void setRebotesPP(double rebotesPP) {
		this.rebotesPP = rebotesPP;
	}
	
	
}

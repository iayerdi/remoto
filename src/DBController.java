import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBController {
	
	private Connection conexion;

	/**
	 * 
	 */
	public DBController() {
		super();
		// TODO Auto-generated constructor stub
		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en el constructor del BDConttroller" + e.getMessage());
		}
	}

	/**
	 * @param conexion
	 */
	public DBController(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	/**
	 * @return the conexion
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * @param conexion the conexion to set
	 */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	/*
	 * Realiza un método que devuelva el nombre del jugador que más puntos por
partido ha anotado en todas las temporadas de la base de datos pero que juegue
en el equipo que más partidos haya ganado en 1 temporada, de entre todas las
temporadas.
	 */
	
	
	
	public ArrayList<EquipoVictorioso> equiposMasPartidosGanados() {
		
		ArrayList<EquipoVictorioso> equipo2 = new ArrayList<EquipoVictorioso>();
		String sql = "SELECT e.Nombre, p.temporada FROM equipos AS e, partidos AS p WHERE  (p.equipo_local = e.Nombre AND p.puntos_local > p.puntos_visitante) OR (p.equipo_visitante = e.Nombre AND p.puntos_visitante < p.equipo_local) ORDER BY p.temporada;";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipo2;
	}
	
	public ArrayList<String> dameTemporadas(){
		ArrayList<String> temporadas = new ArrayList<String>();
		String sql = "SELECT distinct temporada from partidos";
		Statement myStatement;
		try {
			myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				temporadas.add(rs.getString("temporada"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return temporadas;
	}
	
	/* 2. Realiza un método que devuelva los jugadores que más puntos por partido han
	anotado en cualquier temporada de cada equipo. Deberás devolver un arraylist
	de jugadores con un jugador por cada equipo. */
	
	public Jugador dameJugadorMasPPPEquipo(String nombreEquipo) {
		Jugador j = new Jugador();
		String sql = "SELECT * FROM jugadores AS j, estadisticas AS e WHERE j.nombre_equipo = '"+nombreEquipo+"' AND e.jugador = j.codigo ORDER BY e.puntos_por_partido DESC LIMIT 1";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				 j = new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), nombreEquipo);
				 break;
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return j;
	}
	
	public Jugador dameJugadorMenosPPPEquipo(String nombreEquipo) {
		Jugador j = new Jugador();
		String sql = "SELECT * FROM jugadores AS j, estadisticas AS e WHERE j.nombre_equipo = '"+nombreEquipo+"' AND e.jugador = j.codigo ORDER BY e.puntos_por_partido ASC LIMIT 1";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				 j = new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), nombreEquipo);
				 break;
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return j;
	}
	public ArrayList<String> damePosiciones(){
		ArrayList<String> posiciones = new ArrayList<String>();
		String sql ="SELECT DISTINCT posicion from jugadores";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				posiciones.add(rs.getString("posicion"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return posiciones;
	}
	
/*
 * 4. Realiza un método que muestre el nombre del jugador con menos ppp que
pertenezca al equipo que más victorias ha conseguido por una diferencia mayor
a la media de la diferencia de todos los partidos de todas las temporadas.
 */
	public ArrayList<String> equiposPartidosGanadosMasMadei(double media){
		ArrayList<String> equipos = new ArrayList<String>();
		String sql ="SELECT e.nombre FROM equipos AS e, partidos AS p WHERE (p.equipo_local = e.nombre AND p.puntos_local - p.puntos_visitante > '"+media+"') OR  (p.equipo_visitante = e.nombre AND  p.puntos_visitante - p.puntos_local  > '"+media+"')";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				equipos.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipos;
	}
	
	
	public ArrayList<Partido> damePartidos(){
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		String sql = "SELECT * FROM partidos";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				partidos.add(new Partido (rs.getInt("codigo"), rs.getString("equipo_local"), rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return partidos;
	}
	
	public Jugador dameJugadorMasPPPPosicion(String posicion) {
		Jugador j = new Jugador();
		String sql = "SELECT * FROM jugadores AS j, estadisticas AS e WHERE j.posicion = '"+posicion+"' AND e.jugador = j.codigo ORDER BY e.puntos_por_partido DESC LIMIT 1";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				 j = new Jugador(rs.getInt("codigo"), rs.getString("nombre"), posicion, Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("Nombre_equipo"));
				 break;
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return j;
	}
	
	public ArrayList<String> dameEquiposVencedoresTemporada(String temporada){
		ArrayList<String> equipos = new ArrayList<String>();
		String sql = "SELECT e.nombre FROM equipos AS e, partidos AS p WHERE ((p.equipo_local = e.Nombre AND p.puntos_local > p.puntos_visitante) OR (p.equipo_visitante = e.Nombre AND p.puntos_visitante> p.puntos_local)) AND p.temporada = '"+temporada+"'";
		Statement myStatement;
		try {
			myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				equipos.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return equipos;
	}
	
	public ArrayList<String> dameEquiposDivisionPrimeraYUltimaVocal(){
		ArrayList<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM equipos WHERE LOWER(SUBSTR(division, 1, 1)) IN ('a', 'e', 'i', 'o', 'u') AND LOWER(SUBSTR(division, -1)) IN ('a', 'e', 'i', 'o', 'u');";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				nombres.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return nombres;
	}
	
	public ArrayList<String> dameDivisiones(){
		ArrayList<String> divisiones = new ArrayList<String>();
		String sql  = "SELECT distinct division FROM equipos";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				divisiones.add(rs.getString("division"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return divisiones;
	}
	
	public ArrayList<String> dameEquiposPorDivision(String division){
		ArrayList<String> equipos = new ArrayList<String>();
		String sql = "SELECT nombre FROM equipos WHERE division = '"+division+"'";
		try {
			Statement myStatemen = this.conexion.createStatement();
			ResultSet rs = myStatemen.executeQuery(sql);
			while(rs.next()) {
				equipos.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	public ArrayList<Jugador> dameJugadores(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		String sql = "SELECT * FROM jugadores";
		
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("nombre_equipo")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public ArrayList<Jugador> dameJugadores(String procedencia, String conferencia, String division, String ciudad){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		String sql = "SELECT * FROM jugadores WHERE procedencia = '"+procedencia+"' AND nombre_equipo in (SELECT nombre FROM equipos WHERE ciudad != '"+ciudad+"' AND conferencia = '"+conferencia+"' AND division = '"+division+"'";
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("nombre_equipo")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public ArrayList<Equipo>  dameEquiposApalizados(){
		ArrayList<Equipo> equipos  = new ArrayList<Equipo>();
		String sql = "SELECT * FROM equipos WHERE nombre IN((SELECT equipo_local FROM partidos WHERE puntos_visitante - puntos_local >40) OR (SELECT equipo_visitante FROM partidos WHERE puntos_local - puntos_visitante > 40))";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				equipos.add(new Equipo (rs.getString("nombre"),rs.getString("ciudad"), rs.getString("conferencia"), rs.getString("division")));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	public ArrayList<Jugador> dameJugadoresConferencia(String conferencia){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		String sql = "SELECT * FROM jugadores WHERE nombre_equipo  in (SELECT nombre FROM equipos WHERE conferencia = '"+conferencia+"'";
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("nombre_equipo")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public boolean existenJugadoresAltos(String equipo) {
		boolean existe = false;
		ArrayList<Jugador> jugadores = dameJugadoresEquipo(equipo);
		
		for (Jugador j : jugadores) {
			if(j.getAltura() >= 7) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	public ArrayList<String> nombreJugadorMayorMediaPuntos() {
		ArrayList<String> datos = new ArrayList<String>();
		String sql = "SELECT jugadores.nombre, estadisticas.temporada, estadisticas.Puntos_por_partido\r\n"
				+ "FROM jugadores, estadisticas \r\n"
				+ "WHERE estadisticas.jugador = jugadores.codigo \r\n"
				+ "AND estadisticas.Puntos_por_partido = (\r\n"
				+ "    SELECT MAX(Puntos_por_partido) \r\n"
				+ "    FROM estadisticas);";
		
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				datos.add(rs.getString("jugadores.nombre"));
				datos.add(rs.getString("estadisticas.temporada"));
				datos.add(String.valueOf(rs.getFloat("estadisticas.puntos_por_partido")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datos;
	}
	
	
	public ArrayList<Jugador> dameJugadoresEquipo(String equipo){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		String sql = "SELECT * FROM jugadores WHERE nombre_equipo = '"+equipo+"'";
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("nombre_equipo")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public ArrayList<String> dameNombresJugadoresEquipo(String equipo){
		ArrayList<String> jugadores = new ArrayList<String>();
		String sql = "SELECT nombre FROM jugadores WHERE nombre_equipo = '"+equipo+"'";
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(rs.getString("nombre"));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public ArrayList<Double> puntosJugador(int codigo){
		ArrayList<Double> puntos = new ArrayList<Double>();
		String sql = "SELECT puntos_por_partido FROM estadisticas WHERE jugador = '"+codigo+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				puntos.add((double) rs.getFloat("puntos_por_partido"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return puntos;
	}
	
	
	
	public int maxPuntosEquipoLocal(String nombre) {
		int puntos = 0;
		int local = 0;
		String sql = "SELECT puntos_local FROM partidos WHERE partidos.equipo_local = '"+nombre+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				local = rs.getInt("puntos_local");
				if(local > puntos) {
					puntos = local;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return puntos;
	}
	
	public int maxPuntosEquipoVisitante(String nombre) {
		int puntos = 0;
		int vis = 0;
		String sql = "SELECT puntos_visitante FROM partidos  WHERE partidos.equipo_visitante = '"+nombre+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				vis = rs.getInt("puntos_visitante");
				if(vis > puntos) {
					puntos = vis;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return puntos;
	}
	
	public ArrayList<Jugador> dameJugadoresDivision(String division){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		String sql = "SELECT * FROM jugadores WHERE nombre_equipo  in (SELECT nombre FROM equipos WHERE division = '"+division+"'";
		try {
			java.sql.Statement myStatement =  this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() != false) {
			jugadores.add(new Jugador(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("procedencia"), Double.parseDouble(rs.getString("altura").replace("-", ".")), rs.getInt("peso"), rs.getString("posicion"), rs.getString("nombre_equipo")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public ArrayList<Partido> damePartidosGanadosEquipo(String nombre){
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		String sql = "SELECT * FROM partidos WHERE (equipo_local = '"+nombre+"' AND puntos_local > puntos_visitante) OR ( equipo_visitante = '"+nombre+"' AND puntos_visitante>puntos_local)";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				partidos.add(new Partido(rs.getInt("codigo"),rs.getString("equipo_local"), rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada")))	;
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partidos;
	}
	
	public ArrayList<Equipo> dameEquipos(){
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		String sql = "SELECT * FROM equipos";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() == true) {
				equipos.add(new Equipo (rs.getString("nombre"),rs.getString("ciudad"), rs.getString("conferencia"), rs.getString("division")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	public ArrayList<String> dameNombresEquipos(){
		ArrayList<String> equipos = new ArrayList<String>();
		String sql = "SELECT nombre FROM equipos";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() == true) {
				equipos.add(rs.getString("nombre"));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	
	
	public ArrayList<Equipo> dameEquiposPorDivision(){
		
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		String sql = "SELECT * FROM equipos ORDER BY division";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() == true) {
				equipos.add(new Equipo (rs.getString("nombre"),rs.getString("ciudad"), rs.getString("conferencia"), rs.getString("division")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipos;
	}
	
	public Equipo dameEquipo(String nombre){
		ArrayList<Jugador> jugadores = dameJugadoresEquipo(nombre);
		Equipo equipo = new Equipo();
		String sql = "SELECT * FROM equipos WHERE nombre = '"+nombre+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				equipo  = new Equipo (rs.getString("nombre"),rs.getString("ciudad"), rs.getString("conferencia"), rs.getString("division"),jugadores);
			}
		
			
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipo;
	}
	
	public  void altaJugador(Jugador j) {
		String sql = "INSERT INTO jugadores VALUES('"+j.getCodigo()+"', '"+j.getNombre()+"', '"+j.getProcedencia()+"', '"+String.valueOf(j.getAltura()).replace(".","-")+"', '"+j.getPeso()+"', '"+j.getPosicion()+"', '"+j.getNombreEquipo()+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existeJugador(int codigo) {
		boolean existe = true;
		String sql = "SELECT * FROM jugadores WHERE codigo = '"+codigo+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if(rs.next() == false) {
				existe = false;
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean existeEquipo(String equipo) {
		boolean existe = false;
		String sql = "SELECT * FROM equipos WHERE nombre = '"+equipo+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if(rs.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return existe;
	}
	
	public boolean existeJugador(String nombre) {
		boolean existe = true;
		String sql = "SELECT * FROM jugadores WHERE nombre = '"+nombre+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if(rs.next() == false) {
				existe = false;
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public ArrayList<String[]> dameDatosJugadoresConMasPuntos( float puntosPP){
		ArrayList<String[]> datosJugadores= new ArrayList<String[]>();
		
		String sql = "SELECT jugadores.nombre, jugadores.Nombre_equipo, equipos.ciudad, estadisticas.temporada, estadisticas.puntos_por_partido FROM estadisticas, jugadores,equipos WHERE estadisticas.Puntos_Por_Partido > '"+puntosPP+"' AND jugadores.codigo = estadisticas.jugador AND  equipos.nombre = jugadores.Nombre_equipo";
		
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next() == true) {
				String datos = "";
				datos = rs.getString("jugadores.nombre") + "-" + rs.getString("jugadores.Nombre_equipo")+ "-" + rs.getString("equipos.ciudad")+ "-"+ rs.getString("estadisticas.temporada") + "-" + rs.getFloat("estadisticas.puntos_por_partido");
				String[] aux = datos.split("-");
				datosJugadores.add(aux);
				
			}
			myStatement.execute(sql);
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosJugadores;
	}
	
	public String crearCadena(ArrayList<String> datos) {
		String resul  ="";
		
		for (String s : datos) {
			resul = resul + " " + s;			
		}
		
		return resul;
	}
	
	public boolean existeEstadisticaJugador(int codigoJugador, String temporada) {
		boolean existe = true;
		String sql = "SELECT * FROM estadisticas WHERE jugador = '"+codigoJugador+"' AND temporada = '"+temporada+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if(rs.next() == false) {
				existe = false;
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void traspasarJugador(int codigo, String equipo) {
		String sql = "UPDATE jugadores SET nombre_equipo = '"+equipo+"' WHERE codigo = '"+codigo+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existeEstadisticaJugador(int codigoJugador) {
		boolean existe = true;
		String sql = "SELECT * FROM estadisticas WHERE jugador = '"+codigoJugador+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if(rs.next() == false) {
				existe = false;
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public ArrayList<Estadistica> dameEstadisticas(int jugador){
		ArrayList<Estadistica> estadisticas  = new ArrayList<Estadistica>();
		
		String sql = "SELECT * FROM estadisticas WHERE jugador = '"+jugador+"'";
		
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			
			while(rs.next()) {
				estadisticas.add(new Estadistica(rs.getString("temporada"), rs.getInt("jugador"),rs.getDouble("puntos_por_partido"),rs.getDouble("asistencias_por_partido"),rs.getDouble("tapones_por_partido"), rs.getDouble("rebotes_por_partido")));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estadisticas;
	}
	
	public void altaEstadisticaJugador(Estadistica estadistica) {
		String sql = "INSERT INTO estadisticas VALUES ('"+estadistica.getTemporada()+"','"+estadistica.getJugador()+"','"+estadistica.getPuntosPP()+"','"+estadistica.getAsistenciasPP()+"','"+estadistica.getTaponesPP()+"','"+estadistica.getRebotesPP()+"')";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bajaEstadisticaJugador(int codigo, String temporada) {
		String sql = "DELETE FROM estadisticas WHERE temporada = '"+temporada+"' AND jugador = '"+codigo+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Partido> damePartidosEquipos(String equipo1, String equipo2){
		ArrayList<Partido> datos  = new ArrayList<Partido>();
		String sql ="SELECT * FROM partidos WHERE (partidos.equipo_visitante ='"+equipo1+"' AND artidos.puntos_local = '"+equipo2+"' ) OR (partidos.equipo_visitante ='"+equipo2+"' AND artidos.puntos_local = '"+equipo1+"' )";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs  = myStatement.executeQuery(sql);
			while(rs.next()) {
				datos.add(new Partido(rs.getInt("codigo"),rs.getString("equipo_local"), rs.getString("equipo_visitante"),rs.getInt("puntos_local"),rs.getInt("puntos_visitante"),rs.getString("temporada")));
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	
	public String dameCiudadEquipo(String nombre) {
		String ciudad = "";
		String sql = "SELECT ciudad FROM equipos WHERE 	nombre = '"+nombre+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while(rs.next()) {
				ciudad = rs.getString("ciudad");
			}
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ciudad;
	}
	
	public int dameCodigoJugador(String nombre) {
		int codigo = 0;
		String sql = "SELECT codigo FROM jugadores WHERE codigo = '"+nombre+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			codigo = rs.getInt("codigo");
			rs.close();
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codigo;
	}
	
	public void eliminarEstadisticasJugador(Jugador j) {
		String sql = "DELETE * FROM estadistica WHERE jugador = '"+j.getCodigo()+"'";
		try {
			Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bajaJugador(int codigo) {
		String sql  ="DELETE FROM jugadores WHERE codigo = '"+codigo+"'";
		try {
			java.sql.Statement myStatement = this.conexion.createStatement();
			myStatement.execute(sql);
			myStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


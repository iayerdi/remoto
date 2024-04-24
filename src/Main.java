import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBController controlador = new DBController();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		do {
			mostrarMenu();
			System.out.println("Introduce la opcion deseada:");
			opcion = sn.nextInt();
			
			switch(opcion) {
			case 1:
				System.out.println("Introduce el codigo del jugador:");
				String codigoJugador = sc.nextLine();
				if(enteroCorrecto(codigoJugador)) {
					if(controlador.existeJugador(Integer.parseInt(codigoJugador)) == false) {
						Jugador jugador = pedirDatosJugador(Integer.parseInt(codigoJugador));
						controlador.altaJugador(jugador);
					}else {
						System.out.println("Ya existe un jugador con ese codigo. No se puede dar de alta");
					}
						
				}else {
					System.out.println("El codigo introducido no esta correcto.");
				}
				break;
			case 2:
				System.out.println("Introduce el codigo del jugador:");
				codigoJugador = sc.nextLine();
				if(enteroCorrecto(codigoJugador)) {
					if(controlador.existeJugador(Integer.parseInt(codigoJugador))) {
						controlador.bajaJugador(Integer.parseInt(codigoJugador));
					}else {
						System.out.println("No existe un jugador con ese codigo. No se puede dar de baja");
					}
						
				}else {
					System.out.println("El codigo introducido no esta correcto.");
				}
				break;
				
			case 3:
				equipos = controlador.dameEquipos();
				for (Equipo e : equipos) {
					System.out.println(e.toString());
				}
				
				break;
				
			case 4:
				jugadores = controlador.dameJugadores();
				for (Jugador j : jugadores) {
					System.out.println(j.toString());
				}
				
				break;
				
			case 5:
				System.out.println("Introduce el codigo del jugador:");
				codigoJugador = sc.nextLine();
				if(enteroCorrecto(codigoJugador)) {
					if(controlador.existeJugador(Integer.parseInt(codigoJugador))) {
						System.out.println("Introduce la temporada");
						String temporada = sc.nextLine();
						while(temporadaCorrecta(temporada) == false) {
							System.out.println("Formato incorrecto (AA/AA)");
							temporada = sc.nextLine();
						}
						Estadistica estadistica = pedirDatosEstadisticaJugador(Integer.parseInt(codigoJugador),temporada);
						controlador.altaEstadisticaJugador(estadistica);
					}else {
						System.out.println("No existe el jugador.");
					}
					
				}else {
					System.out.println("El codigo introducido no esta correcto.");
				}
				break;
				
			case 6:
				System.out.println("Introduce el codigo del jugador:");
				codigoJugador = sc.nextLine();
				if(enteroCorrecto(codigoJugador)) {
					if(controlador.existeJugador(Integer.parseInt(codigoJugador))) {
						System.out.println("Introduce la temporada");
						String temporada = sc.nextLine();
						while(temporadaCorrecta(temporada) == false) {
							System.out.println("Formato incorrecto (AA/AA)");
							System.out.println("Introduce la temporada");
							temporada = sc.nextLine();
						}
						if(controlador.existeEstadisticaJugador(Integer.parseInt(codigoJugador), temporada)) {
							controlador.bajaEstadisticaJugador(Integer.parseInt(codigoJugador), temporada);
						}else {
							System.out.println("No se puede dar de baja. No existe esa estadistica.");
						}
						
					}else {
						System.out.println("No existe el jugador.");
					}
					
				}else {
					System.out.println("El codigo introducido no esta correcto.");
				}
				
			case 7:
				ArrayList<String[]> datosJugadoresMas30 = controlador.dameDatosJugadoresConMasPuntos(30);
				String ruta = "Jugones.txt";
				File archivo = new File(ruta);
				if(archivo.exists() == false) {
					try {
						archivo.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				escribirArchivoJugones(datosJugadoresMas30, archivo);
				break;
			case 8:
				System.out.println("Introduce el nombre del primer equipo");
				String equipo1 = sc.nextLine();
				System.out.println("Introduce el nombre del segundo equipo");
				String equipo2 = sc.nextLine();
				ArrayList<Partido> partidos = new ArrayList<Partido>();
				partidos = controlador.damePartidosEquipos(equipo1, equipo2);
				for (Partido p : partidos) {
					mostrarDatosPartido(p);
				}
				break;
				
			case 9:
				equipos = controlador.dameEquiposPorDivision();
				System.out.println("Equipos ordenados por division:");
				for (Equipo e : equipos) {
					System.out.println(e.toString());
				}
				break;
			case 10:
				System.out.println("Introduce el nombre del equipo");
				String equipo = sc.nextLine();
				ArrayList<Partido> partidosGanados = controlador.damePartidosGanadosEquipo(equipo);
				System.out.println("Partidos ganados por " + equipo);
				for (Partido p : partidosGanados) {
					System.out.println(p.toString());
				}
				break;
				
			case 11:
				System.out.println("Intrdoduce la procedencia:");
				String procedencia = sc.nextLine();
				System.out.println("Introduce la conferencia:");
				String conferencia = sc.nextLine(); 
				System.out.println("Introduce la division: ");
				String division = sc.nextLine();
				System.out.println("Introduce la ciudad de la que no deben ser:");
				String ciudad = sc.nextLine();
				jugadores = controlador.dameJugadores(procedencia, conferencia, division, ciudad);
				for (Jugador jugador : jugadores) {
					System.out.println(jugador.toString());
				}
				
			case 12:
				jugadores = controlador.dameJugadoresConferencia("este");
				mostrarDatosJugadores(jugadores);
				break;
			case 13:
				jugadores = controlador.dameJugadoresDivision("pacifico");
				mostrarDatosJugadores(jugadores);
				break;
			case 14:
				ruta ="altaJugador.txt";
				File archivoAlta = new File(ruta);
				if(archivoAlta.exists() == false) {
					try {
						archivoAlta.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				ArrayList<Jugador> jugadoresBD = controlador.dameJugadores();
				int codigo = creaCodigoJugador(jugadoresBD);
				jugadores = leerJugadoresDeArchivo(archivoAlta, codigo);
				int contador = 0;
				for (Jugador j : jugadores) {
					if(controlador.existeJugador(j.getNombre())) {
						System.out.println("El jugador con nombre " + j.getNombre() + " ya existe en la base");
					}else {
						controlador.altaJugador(j);
						contador++;
					}
				}
				System.out.println("Se han dado de alta satisfactoriamente " + contador + " de los " + jugadores.size() + " que habia en el archivo altajugador.txt");
				break;
			case 15:
				ruta = "apalizados.txt";
				File archivoApalizados = new File(ruta);
				
				if(archivoApalizados.exists() == false) {
					try {
						archivoApalizados.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				ArrayList<Equipo> apalizados = controlador.dameEquiposApalizados();
				quitarEquiposRepetidos(apalizados);
				crearArchivoApalizados(apalizados, archivoApalizados);
				break;
			case 16:
				ruta = "jugadoresparaborrar.txt";
				archivo = new File(ruta);
				jugadores = leerJugadoresDeArchivo(archivo);
				for (Jugador j : jugadores) {
					if(controlador.existeJugador(j.getCodigo())) {
						if(controlador.existeEstadisticaJugador(j.getCodigo())) {
							controlador.eliminarEstadisticasJugador(j);
						}else {
							System.out.println("No tiene estadisticas que eliminar.");
						}
						controlador.bajaJugador(j.getCodigo());
					}else {
						System.out.println("No existe ese jugador en la base de datos.");
					}
				}
				break;
			case 17:
				System.out.println("Introduce el nombre y apellidos del jugador.");
				String nombre = sc.nextLine();
				if(controlador.existeJugador(nombre)) {
					codigo = controlador.dameCodigoJugador(nombre);
					controlador.bajaJugador(codigo);
					controlador.altaJugador(pedirDatosJugador(codigo, nombre));
				}else {
					System.out.println("No se puede modificar.No esta en la base de datos");
				}
				break;
			case 18:
				System.out.println("Introduce el nombre del jugador.");
				nombre = sc.nextLine();
				if(controlador.existeJugador(nombre)) {
					System.out.println("Introduce el equipo al que es traspasado.");
					equipo = sc.nextLine();
					if(controlador.existeEquipo(equipo)) {
						controlador.traspasarJugador(controlador.dameCodigoJugador(nombre), equipo);
					}else {
						System.out.println("No existe ese equipo");
					}
				}else {
					System.out.println("No existe ese jugador.");
				}
				break;
			case 19:
				System.out.println("Introduce la conferencia.");
				conferencia = sc.nextLine();
				ArrayList<Jugador> jugadoresConferencia = controlador.dameJugadoresConferencia(conferencia);
				for (Jugador j : jugadoresConferencia) {
					if(j.getNombre().length()<5) {
						System.out.println("--------------------------------");
					}else {
						if(quintaPorFinalVocal(j.getNombre())) {
							System.out.println(j.toString());
						}
					}
				}
				break;
			case 20:
				ArrayList<String> nombres = new ArrayList<String>();
				nombres = controlador.dameEquiposDivisionPrimeraYUltimaVocal();
				for (String s : nombres) {
					System.out.println(s);
				}
				break;
			case 21:
				ArrayList<String> divisiones = new ArrayList<String>();
				divisiones = controlador.dameDivisiones();
				ArrayList<ArrayList<String>> equiposPDivision = new ArrayList<ArrayList<String>>();
				for (String s : divisiones) {
					equiposPDivision.add(controlador.dameEquiposPorDivision(s));
				}
				for(int i =0 ;i< divisiones.size(); i++) {
					ArrayList<String> equipoDivision = equiposPDivision.get(i);
					System.out.println(divisiones.get(i));
					for (String s : equipoDivision) {
						System.out.print(s + " ");
						
					}
					System.out.println();
				}
				break;
			case 22:
				System.out.println("Introduce el nombre del equipo");
				nombre = sc.nextLine();
				if(controlador.existeEquipo(nombre)) {
					Equipo e = controlador.dameEquipo(nombre);
					System.out.println(e.getNombre());
					for (Jugador j : e.getJugadores()) {
						System.out.println(j.getNombre());
					}
				}else {
					System.out.println("No existe un equipo con ese nombre");
				}
				break;
			case 23:
				equipos = equiposConMasDe15Jugadores();
				for (Equipo e : equipos) {
					System.out.println(e.getNombre());
				}
				break;
			case 24:
				equipos = controlador.dameEquipos();
				for (int i=0; i< equipos.size(); i++) {
					if(controlador.existenJugadoresAltos(equipos.get(i).getNombre()) == false) {
						equipos.remove(i);
					}
				}
				for (Equipo e : equipos) {
					System.out.println(e.getNombre());
				}
				break;
				
			case 25:
				ArrayList<Integer> puntuaciones = new ArrayList<Integer>();
				ArrayList<String> nombresE = controlador.dameNombresEquipos();
				for (String s : nombresE) {
					if(controlador.maxPuntosEquipoLocal(s) > controlador.maxPuntosEquipoVisitante(s)) {
						puntuaciones.add(controlador.maxPuntosEquipoLocal(s));
					}else {
						puntuaciones.add(controlador.maxPuntosEquipoVisitante(s));
					}
					
				}
				ordenarPorAnotacion(puntuaciones, nombresE);
				for(int i=0; i<3 ; i++) {
					System.out.println(nombresE.get(i));
				}
				break;
				
			case 26:
				ArrayList<String> datos = controlador.nombreJugadorMayorMediaPuntos();
				for (String s : datos) {
					System.out.print(s + " ");
				}
				System.out.println();
				break;
			case 27:
				break;
			case 28:
				break;
			case 29:
				ArrayList<EquipoVictorioso> victoriosos = new ArrayList<EquipoVictorioso>();
				ArrayList<String> temporadas = controlador.dameTemporadas();
				ArrayList<String> nomEquipos = controlador.dameNombresEquipos();
				for (String s : temporadas) {
					ArrayList<String> equiVencedores = controlador.dameEquiposVencedoresTemporada(s);
					victoriosos.add(equiposMasVictoriasTemporada(equiVencedores,s,nomEquipos))  ;
					
				}
				String eqMasVic = nombreEquipoConMasVict(victoriosos);
				jugadores = controlador.dameJugadoresEquipo(eqMasVic);
				ArrayList<Double> puntos = new ArrayList<Double>();
				ArrayList<JugadorCarrera> jugadoresCarrera = new ArrayList<JugadorCarrera>();
				for (Jugador j : jugadores) {
					puntos = controlador.puntosJugador(j.getCodigo());
					jugadoresCarrera.add(new JugadorCarrera(j.getCodigo(),j.getNombre(),maximosPuntosPPJugador(puntos)));
					
				}
				System.out.println(nombreJugadorMasPuntosCarrera(jugadoresCarrera));
				break;
			case 30:
				//ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
				equipos = controlador.dameEquipos();
				Jugador jugador = new Jugador();
				for (Equipo e : equipos) {
					jugador = controlador.dameJugadorMasPPPEquipo(e.getNombre());
					jugadores.add(jugador);
				}
				for (Jugador j : jugadores) {
					System.out.print(j.getNombre() + ": ");
					System.out.println(j.getNombreEquipo());
					
				}
				break;
			case 31:
				ArrayList<Jugador> jugadoresPos = new ArrayList<Jugador>();
				ArrayList<String> posiciones = controlador.damePosiciones();
				for (String p : posiciones) {
					jugadoresPos.add(controlador.dameJugadorMasPPPPosicion(p));
				}
				for (Jugador j : jugadoresPos) {
					System.out.println("Para la posicion " + j.getPosicion() + " el jugador con mas PPP es: " + j.getNombre());
				}
				break;
			case 32:
				 partidos = controlador.damePartidos();
				 nomEquipos = controlador.dameNombresEquipos();
				 double diferencia = diferenciaMediaPartidos(partidos);
				 System.out.println(diferencia + " puntos de diferencia de media.");
				 ArrayList<String> ganadores = new ArrayList<String>(); 
				 ganadores = controlador.equiposPartidosGanadosMasMadei(diferencia);
				 nombre = nombreEquipoMasGanaDiferencia(ganadores,nomEquipos );
				 System.out.println(nombre);
				 Jugador jug = controlador.dameJugadorMenosPPPEquipo(nombre);
				 System.out.println(jug.getNombre());
				 
			case 33:
				System.out.println("Fin del programa.");
				break;
				default: 
					System.out.println("opcion incorrecta");
					break;
			}
			
		}while(opcion != 33);
		
		

	}
	
	public static String nombreEquipoMasGanaDiferencia(ArrayList<String> nombres, ArrayList<String> nomEquipos ) {
		String nombre = "";
		ArrayList<Integer> partidosGanados = new ArrayList<Integer>();
		for (String s : nomEquipos) {
			partidosGanados.add(contarVictoriasEquipo(nombres,s));
		}
		int posicion = indiceMayor(partidosGanados); 
		nombre = nomEquipos.get(posicion);
		return nombre;
	}
	
	public static int contarVictoriasEquipo (ArrayList<String> nombres , String nombre) {
		int cont = 0;
		 
		for (String s : nombres) {
			if(s.equalsIgnoreCase(nombre)) {
				cont++;
			}
		}
		return cont;
	}
	
	public static int indiceMayor(ArrayList<Integer> diferencias) {
		int indice = 0;
		int valor = 0;
		for(int i = 0; i<diferencias.size(); i++) {
			if(diferencias.get(i) > valor) {
				indice = i;
				valor = diferencias.get(i);
			}
		}
		System.out.println(valor + " victorias.");
		return indice;
	}
	
	public static double diferenciaMediaPartidos(ArrayList<Partido> partidos) {
		double media =0;
		int sum = 0;
		for (Partido p : partidos) {
			sum = sum + Math.abs(p.getPuntosLocal() - p.getPuntosVisitante());
		}
		media = sum / partidos.size();
		return media;
	}
	
	public static String nombreJugadorMasPuntosCarrera(ArrayList<JugadorCarrera> jugadoresCarrera) {
		double max = 0;
		String nombre ="";
		for (JugadorCarrera j : jugadoresCarrera) {
			
			if(j.getPuntosTotales()>max) {
				max = j.getPuntosTotales();
				nombre = j.getNombre();
			}
		}
		System.out.println("Puntos maximos por partido en su carrera: " + max);
		return nombre;
	}
	
	public static Double maximosPuntosPPJugador(ArrayList<Double> puntos) {
		double max = 0;
		for (Double d : puntos) {
			if(d>max) {
				max = d;
			}
		}
		return max;
	}
	
	public static String nombreEquipoConMasVict(ArrayList<EquipoVictorioso> victoriosos) {
		String eq = "";
		String temp = "";
		int max = 0;
		for (EquipoVictorioso e : victoriosos) {
			if(e.getVictorias()>max) {
				max = e.getVictorias();
				eq = e.getNombre();
				temp = e.getTemporada();
			}
		}
		System.out.println(eq);
		System.out.println("Temporada " + temp);
		System.out.println(max + " victorias");
		return eq;
	}
	
	public static EquipoVictorioso equiposMasVictoriasTemporada(ArrayList<String> equiVence, String temp, ArrayList<String> nomEquipos){
		;
		int max = 0;
		String nombre = "";
		
		for (String s1 : nomEquipos) {
			int cont = 0;
			for (String s2 : equiVence) {
				if(s1.equalsIgnoreCase(s2)) {
					cont++;
				}
			}
			if(cont > max) {
				max = cont;
				nombre = s1;
			}
		}
		EquipoVictorioso eq = new EquipoVictorioso(nombre,temp,max);
		
		return eq;
	}
	
	public static void ordenarPorAnotacion(ArrayList<Integer> puntos, ArrayList<String> nombres) {
		
		for(int i=0; i< puntos.size(); i++) {
			int max = puntos.get(i);
			String nombre = nombres.get(i);
			for(int j=i+1; j< puntos.size(); j++) {
				if(puntos.get(j) > max) {
					int aux = puntos.get(j);
					String nomAux = nombres.get(j);
					puntos.remove(j);
					nombres.remove(j);
					puntos.add(j, max);
					nombres.add(j, nombre);
					puntos.remove(i);
					nombres.remove(i);
					puntos.add(i, aux);
					nombres.add(i, nomAux);
					max = aux;
					nombre = nomAux;
				}
			}
		}
	}
	
	public static boolean quintaPorFinalVocal(String nombre) {
		String letras = "aeiou";
		
		return letras.contains(String.valueOf(nombre.charAt(nombre.length()-5)).toLowerCase());
	}
	
	public static ArrayList<Jugador> leerJugadoresDeArchivo(File archivo, int codigo){
		ArrayList<Jugador> jugadores  = new ArrayList<Jugador>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			try {
				String linea = br.readLine();
				while(linea != null) {
					String[] aux  = linea.split("::");
					linea = br.readLine();
					jugadores.add(new Jugador(codigo,aux[0],aux[1],Double.parseDouble(aux[4].replace("-", ".")),Integer.parseInt( aux[3]) ,aux[2], aux[5]));
					codigo++;
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public static ArrayList<Equipo> equiposConMasDe15Jugadores(){
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		DBController controlador = new DBController();
		equipos = controlador.dameEquipos();
		
		for (int i =0; i< equipos.size(); i++) {
			ArrayList<Jugador> jugadores = controlador.dameJugadoresEquipo(equipos.get(i).getNombre());
			if(jugadores.size()<= 15) {
				equipos.remove(i);
			}else {
				equipos.get(i).setJugadores(jugadores);
			}
		}
		
		return equipos;
	}
	
	
	
	public static ArrayList<Jugador> leerJugadoresDeArchivo(File archivo){
		ArrayList<Jugador> jugadores  = new ArrayList<Jugador>();
		DBController controlador = new DBController();
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			try {
				String linea = br.readLine();
				while(linea != null) {
					String[] aux  = linea.split("::");
					linea = br.readLine();
					int codigo = controlador.dameCodigoJugador(aux[0]);
					jugadores.add(new Jugador(codigo,aux[0],aux[1],Double.parseDouble(aux[4].replace("-", ".")),Integer.parseInt( aux[3]) ,aux[2], aux[5]));
					
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jugadores;
	}
	
	public static void quitarEquiposRepetidos(ArrayList<Equipo> equipos) {
		
		for (int i = 0; i< equipos.size(); i++) {
			for(int j = i+1; j<equipos.size(); j++) {
				if(equipos.get(i).getNombre().equalsIgnoreCase(equipos.get(j).getNombre())) {
					equipos.remove(j);
				}
			}
		}
	}
	
	public static int creaCodigoJugador(ArrayList<Jugador> jugadores) {
		int resul = 0;
		
		for (Jugador j : jugadores) {
			if(j.getCodigo()> resul) {
				resul = j.getCodigo();
			}
		}
		resul ++;
		return resul;
	}
	
	public static void crearArchivoApalizados(ArrayList<Equipo>equipos, File archivo) {
		String linea = "";
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter(archivo,false));
			for (Equipo e : equipos) {
				linea = e.getCiudad() + " " + e.getNombre() + " Conferencia " + e.getConferencia() + "- Division " + e.getDivision();			
				bw.write(linea);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mostrarDatosPartido(Partido p) {
		DBController controlador = new DBController();
		String ciudadLoc = controlador.dameCiudadEquipo(p.geteLocal());
		String ciudadVis = controlador.dameCiudadEquipo(p.geteVisitante());
		System.out.println("Temporada " + p.getTenporada() + " " + ciudadLoc + " " + p.geteLocal() + " " + p.getPuntosLocal() + ":" + p.getPuntosVisitante() + " " + ciudadVis + " " + p.geteVisitante());
	}
	
	public static void escribirArchivoJugones(ArrayList<String[]> datos, File archivo) {
		String aux = "";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false));
			for (String[] d : datos) {
				aux = d[0] + "-" + d[1] + " de " + d[2] + " Temporada " + d[3] + " Puntos por partido: " + d[4] + " ppp";
				bw.write(aux);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mostrarDatosJugadores(ArrayList<Jugador> jugadores) {
		DBController controlador = new DBController();
		for (Jugador j : jugadores) {
			System.out.println(j.getNombre() +" - " + controlador.dameCiudadEquipo(j.getNombreEquipo()) + " " + j.getNombreEquipo() + " - " + j.getAltura()+ " pies - " + j.getPeso() + " libras - " + j.getPosicion());
			ArrayList<Estadistica> estadisticas = controlador.dameEstadisticas(j.getCodigo());
			for (Estadistica e : estadisticas) {
				System.out.print("Temporada " + e.getTemporada() + ": ");
				System.out.print(e.getPuntosPP() + "ppp, ");
				System.out.print(e.getAsistenciasPP() + "app, ");
				System.out.print(e.getRebotesPP() + "rpp, ");
				System.out.print(e.getTaponesPP() + "tpp.");
				System.out.println();
			}
		}
		
	}
	
	public static void mostrarMenu() {
		
		System.out.println("1. Alta Jugador.\r\n"
				+ "2. Baja Jugador.\r\n"
				+ "3. Listado de equipos.\r\n"
				+ "4. Listado de Jugadores.\r\n"
				+ "5. Salir.\r\n"
				+ "6. Baja de estadistica.\r\n"
				+ "7. Genera un archivo 'Jugones.txt' con los jugadores con estdistica de mas"
				+ "de 30 puntos por partido\r\n"
				+ "8. Listado con los enfrentamientos de 2 equipos.\r\n"
				+ "9. Listado de todos los equipos ordenados por division.\r\n");
	}
	
	public static boolean enteroCorrecto(String dato) {
		boolean correcto = true;
		String numeros = ("0123456789");
		
		for(int i = 0; i< dato.length(); i++) {
			if(numeros.contains(String.valueOf(dato.charAt(i))) == false) {
				correcto = false;
				break;
			}
		}
		
		return correcto && (dato.length()<12);
	}
	
	public static boolean doubleCorrecto(String dato) {
		boolean correcto = true;
		int numPuntos = 0; 
		String numeros = ("123456789.");
		
		for(int i = 0; i<dato.length(); i++) {
			if(numeros.contains(String.valueOf(dato.charAt(i))) == false) {
				correcto = false;
				break;
			}else if(String.valueOf(dato.charAt(i)).equals(".")){
				numPuntos ++;
			}
		}
		
		return correcto && (numPuntos <2);
	}
	
	public static boolean temporadaCorrecta(String temporada) {
		boolean correcta = false;
		String valores = "1234567890";
		
		if(valores.contains(String.valueOf(temporada.charAt(0))) && valores.contains(String.valueOf(temporada.charAt(1))) 
					&& valores.contains(String.valueOf(temporada.charAt(3))) && valores.contains(String.valueOf(temporada.charAt(4)))
					&& String.valueOf(temporada.charAt(2)).equals("/") && temporada.length() == 5) {
				
			correcta = true;
		}
		
		return correcta;
	}
	
	public static Jugador pedirDatosJugador(int codigo) {
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		String procedencia ="";
		String altura ="";
		String peso = "";
		String posicion = "";
		String equipo = "";
		
		System.out.println("Introduce el nombre del jugador");
		nombre = sc.nextLine();
		System.out.println("Introduce la procedencia del jugador");
		procedencia = sc.nextLine();
		System.out.println("Introduce la altura del jugador");
		altura = sc.nextLine();
		while(doubleCorrecto(altura) == false) {
			System.out.println("Formato de datos incorrecto. Introduce la altura del jugador (NUMERO PUNTO NUMERO)");
			altura = sc.nextLine();
		}
		System.out.println("Introduce el peso del jugador");
		peso = sc.nextLine();
		while(enteroCorrecto(peso) == false) {
			System.out.println("Formato de datos incorrecto. Introduce el peso del jugador (NUMERO ENTERO)");
			altura = sc.nextLine();
		}
		System.out.println("Introduce la posicion del jugador");
		posicion = sc.nextLine();
		System.out.println("Introduce el equipo del jugador");
		equipo = sc.nextLine();
		sc.close();
		Jugador jugador = new Jugador(codigo, nombre, procedencia, Double.parseDouble(altura), Integer.parseInt(peso),posicion,equipo);
		
		
		return jugador;
	}
	
	public static Jugador pedirDatosJugador(int codigo, String nombre) {
		Scanner sc = new Scanner(System.in);
		String procedencia ="";
		String altura ="";
		String peso = "";
		String posicion = "";
		String equipo = "";
		
		System.out.println("Introduce la procedencia del jugador");
		procedencia = sc.nextLine();
		System.out.println("Introduce la altura del jugador");
		altura = sc.nextLine();
		while(doubleCorrecto(altura) == false) {
			System.out.println("Formato de datos incorrecto. Introduce la altura del jugador (NUMERO PUNTO NUMERO)");
			altura = sc.nextLine();
		}
		System.out.println("Introduce el peso del jugador");
		peso = sc.nextLine();
		while(enteroCorrecto(peso) == false) {
			System.out.println("Formato de datos incorrecto. Introduce el peso del jugador (NUMERO ENTERO)");
			altura = sc.nextLine();
		}
		System.out.println("Introduce la posicion del jugador");
		posicion = sc.nextLine();
		System.out.println("Introduce el equipo del jugador");
		equipo = sc.nextLine();
		sc.close();
		Jugador jugador = new Jugador(codigo, nombre, procedencia, Double.parseDouble(altura), Integer.parseInt(peso),posicion,equipo);
		
		
		return jugador;
	}
	
	public static Estadistica pedirDatosEstadisticaJugador(int codigoJugador, String temporada) {
		Scanner sc = new Scanner(System.in);
		String ppp = "";
		String rpp ="";
		String tpp ="";
		String app = "";
		
		System.out.println("Introduce los puntos por partido del jugador");
		ppp = sc.nextLine();
		while(doubleCorrecto(ppp) == false) {
			System.out.println("Formato de datos incorrecto. Introduce los puntos por partido del jugador (NUMERO PUNTO NUMERO)");
			ppp = sc.nextLine();
		}
		System.out.println("Introduce los rebotes por partido del jugador");
		rpp = sc.nextLine();
		while(doubleCorrecto(ppp) == false) {
			System.out.println("Formato de datos incorrecto. Introduce los rebotes por partido del jugador (NUMERO PUNTO NUMERO)");
			ppp = sc.nextLine();
		}
		System.out.println("Introduce los tapones por partido del jugador");
		tpp = sc.nextLine();
		while(doubleCorrecto(ppp) == false) {
			System.out.println("Formato de datos incorrecto. Introduce los tapones por partido jugador (NUMERO PUNTO NUMERO)");
			ppp = sc.nextLine();
		}
		System.out.println("Introduce las asistencias por partido del jugador");
		app = sc.nextLine();
		while(doubleCorrecto(ppp) == false) {
			System.out.println("Formato de datos incorrecto. Introduce las asistencias por partido del jugador (NUMERO PUNTO NUMERO)");
			ppp = sc.nextLine();
		}
		sc.close();
		Estadistica estadistica = new Estadistica( temporada, codigoJugador,Double.parseDouble(ppp) , Double.parseDouble(app),Double.parseDouble(tpp),Double.parseDouble(rpp));
		
		return estadistica;
	}

}

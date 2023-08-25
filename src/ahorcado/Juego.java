package ahorcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
	// Lista de palabras a adivinar en el juego
	public static List<String> palabrasJuego;

	// Palabra seleccionada para adivinar en el juego
	public static String palabraJuego = "";

	// Palabra seleccionada para adivinar en el juego - permanece invariable a lo
	// largo de cada juego
	public static String solucion = "";

	// Se genera una lista con los caracteres de la palabra a adivinar
	public static char[] caracteresPalabra;

	// Se utiliza para mostrar por pantalla la palabra al jugador. Segun va
	// adivinando letras, se sustituyen las letras por los guiones iniciales
	public static String mostrarPalabra = "";

	// Recoge las letras erroneas para mostrarlas por pantalla
	public static String listaFallos = "";

	// Contador de errores, inicial = 0
	public static int errores = 0;

	// Recoge el caracter introducido por el jugador en cada iteracion
	public static char caracterJugador;

	// String que recoge el conjunto de letras validas en el juego
	public static String[] letrasValidas;

	public static String resolver = "";
	public static String respuesta = "";
	public static int posicionLetra = 99;
	public static boolean fallo = true;
	public static boolean letraValida = false;
	public static boolean terminado = false;

	public static void turno() {
		// Solicitar al jugador que pruebe a adivinar una letra
		System.out.println("Introduce una letra: ");
		Scanner sc = new Scanner(System.in);
		String letraIntroducida = sc.nextLine();
		try {
			// Si el jugador no pasa nada por pantalla, el metodo charAt(0) genera una
			// exception
			caracterJugador = letraIntroducida.charAt(0);
		} catch (IndexOutOfBoundsException e) {
		}

		letrasValidas = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		// Comprobar que el caracter introducido sea una letra valida, sino pedir una
		// nueva letra por pantalla
		for (String l : letrasValidas) {
			if (letraIntroducida.equals(l)) {
				letraValida = true;
				break;
			} else {
				letraValida = false;
				continue;
			}
		}

		if (letraValida) {

			// Comprobar si la letra introducida esta en la palabra a adivinar
			for (char c : caracteresPalabra) {
				if (caracterJugador == c) {
					fallo = false;
					try {
						do {
							// mostrarPalabra comienza y termina por un espacio en blanco y contiene un
							// espacio en blanco entre cada letra para facilitar su lectura
							posicionLetra = palabraJuego.indexOf(caracterJugador) * 2 + 1;
							// Sustituir los '_' por las letras correspondientes para mostrar por pantalla
							mostrarPalabra = mostrarPalabra.substring(0, posicionLetra) + letraIntroducida
									+ mostrarPalabra.substring(posicionLetra + 1);
							// Sustituir el caracter por un guion para seguir iterando el bucle do while,
							// porque hay palabras con una misma letra en diferentes posiciones
							palabraJuego = palabraJuego.substring(0, palabraJuego.indexOf(caracterJugador)) + "_"
									+ palabraJuego.substring(palabraJuego.indexOf(caracterJugador) + 1);
						} while (palabraJuego.contains(letraIntroducida));
					} catch (IndexOutOfBoundsException e) {
						// Las letras ya adivinadas estan presentes en caracteresPalabra, pero generan
						// exception en el metodo substring
						System.out.println("Letra ya adivinada: '" + letraIntroducida.toUpperCase() + "'");
					}
					break;
				} else {
					fallo = true;
					continue;
				}
			}
			// Añadir letras erroneas a la lista y mostrar por pantalla
			if (fallo) {
				System.out.println("La letra introducida no se encuentra en la palabra");
				listaFallos += caracterJugador + " ";
				System.out.println("Letras erroneas: " + listaFallos.toUpperCase());
				// Sumar error al total de errores del jugador y mostrar intentos restantes
				errores += 1;
				System.out.println("Te quedan " + (10 - errores) + " intentos");
				// Pintar el muñeco del ahorcado por consola tras cada error
				switch (errores) {
				case 1:
					System.out.println("***");
					System.out.println("_____");
					System.out.println("***");
					break;
				case 2:
					System.out.println("***");
					System.out.println("  |  \n  |  \n  |  \n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 3:
					System.out.println("***");
					System.out.println("  ______\n  |  \n  |  \n  |  \n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 4:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |  \n  |  \n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 5:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |  \n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 6:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |   |\n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 7:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |  -|\n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 8:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |  -|-\n  |  \n  |  \n_____");
					System.out.println("***");
					break;
				case 9:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |  -|-\n  |   /\n  |  \n_____");
					System.out.println("***");
					break;
				case 10:
					System.out.println("***");
					System.out.println("  ______\n  |   |\n  |   O\n  |  -|-\n  |   A\n  |  \n_____");
					System.out.println("***");
					break;
				}
				if (errores > 9) {
					terminado = true;
					System.out.println("La palabra a adivinar era: " + solucion.toUpperCase());
				} else {
					System.out.println(mostrarPalabra.toUpperCase());
				}
			} else {
				System.out.println(mostrarPalabra.toUpperCase());
			}

			// Comprobar si se han descubierto todas las letras y terminado el juego
			if (!mostrarPalabra.contains("_")) {
				terminado = true;
				System.out.println("¡Enhorabuena, has completado el juego!");
			}

			// Intentar resolver la palabra sin haber descubierto todas las letras de la
			// misma
			if (!terminado) {
				System.out.println("¿Eres capaz de adivinar la respuesta? (SI / NO)");
				resolver = sc.nextLine().toUpperCase();
				if (resolver.equals("SI")) {
					System.out.println("¿Cual crees que es la palabra?");
					respuesta = sc.nextLine();
					if (respuesta.equals(solucion)) {
						// Si la palabra es correcta se termina el juego
						terminado = true;
						System.out.println("¡Has acertado la palabra!");
					} else {
						// Si la palabra no es correcta, sumar error
						System.out.println("¡No es correcto!");
						errores += 1;
						System.out.println("Te quedan " + (10 - errores) + " intentos");
						if (errores > 9) {
							terminado = true;
							System.out.println("La palabra a adivinar era: " + solucion.toUpperCase());
						} else {
							System.out.println(mostrarPalabra.toUpperCase());
						}
					}
				}
			}
		} else {
			System.out.println("El caracter introducido no es un caracter valido");
		}
	}

	public static void main(String[] args) {

		// Se crea la lista de palabras a adivinar en el juego y se añaden las palabras
		palabrasJuego = new ArrayList<>();
		palabrasJuego.add("oposicion");
		palabrasJuego.add("controlar");
		palabrasJuego.add("experiencia");
		palabrasJuego.add("ausente");
		palabrasJuego.add("campaña");

		System.out.println("¡Bienvenido al juego del Ahorcado!");
		Random r = new Random();
		int numeroPalabrasEnArray = palabrasJuego.size();

		// Se genera un numero aleatorio entre 0 y la longitud del array de palabras del
		// juego y se escoge una palabra de forma aleatoria
		int posicionPalabraJuego = r.nextInt(0, numeroPalabrasEnArray);
		palabraJuego = palabrasJuego.get(posicionPalabraJuego);
		solucion = palabraJuego;
		caracteresPalabra = palabraJuego.toCharArray();

		// Mostrar por pantalla los huecos de cada letra a adivinar
		int longitudPalabra = palabraJuego.length();
		mostrarPalabra = " " + "_ ".repeat(longitudPalabra) + " ";
		System.out.println(mostrarPalabra);

		// Turnos hasta terminar el juego de alguna de las siguientes formas:
		// Haber acertado la palabra
		// Haber descubierto todas las letras de la palabra
		// Haber superado los 10 errores sin haber acertado la solucion
		do {
			turno();
		} while (!terminado);
	}
}

import java.util.Scanner;

public class App {

    // Construye las filas de una tabla con las estadísticas de los personajes recibidos.
    private static String[] crearTablaEstadisticas(Personaje... personajes) {
        // Define el borde que se usará para separar el contenido de la tabla.
        String borde = "+----------------+------------+--------+-----------+-------------+";
        // Reserva espacio para los bordes, el encabezado y una fila por personaje.
        String[] filas = new String[personajes.length + 4];
        // Coloca el borde superior de la tabla.
        filas[0] = borde;
        // Crea los títulos de las columnas con el mismo ancho que sus datos.
        filas[1] = String.format("| %-14s | %-10s | %6s | %9s | %11s |",
                                 "Personaje", "Elemento", "Fuerza",
                                 "Velocidad", "Vida");
        // Coloca un borde debajo del encabezado.
        filas[2] = borde;
        // Recorre los personajes para crear una fila de estadísticas para cada uno.
        for (int i = 0; i < personajes.length; i++) {
            // Obtiene el personaje correspondiente a la posición actual.
            Personaje personaje = personajes[i];
            // Formatea y guarda sus estadísticas en la siguiente fila de la tabla.
            filas[3 + i] = String.format(
                "| %-14s | %-10s | %6d | %9d | %4d/%-6d |",
                personaje.nombre, personaje.elemento,
                personaje.fuerza, personaje.velocidad,
                personaje.vida_hp, personaje.vidaMaxima
            );
        }
        // Coloca el borde inferior después de la última fila.
        filas[filas.length - 1] = borde;
        // Devuelve todas las filas ya preparadas para mostrarse.
        return filas;
    }

    // Imprime en consola la tabla de estadísticas de los personajes recibidos.
    private static void mostrarEstadisticas(Personaje... personajes) {
        // Deja una línea en blanco para separar la tabla del texto anterior.
        System.out.println();
        // Crea la tabla y muestra sus filas una por una.
        for (String fila : crearTablaEstadisticas(personajes)) {
            System.out.println(fila);
        }
    }

    // Muestra en paralelo las estadísticas y las opciones disponibles del juego.
    private static void mostrarMenu(Personaje... personajes) {
        // Obtiene la tabla que aparecerá al lado del menú.
        String[] tabla = crearTablaEstadisticas(personajes);
        // Define el texto de cada opción que puede seleccionar el jugador.
        String[] menu = {
            "===== MENU =====",
            "1. Ataque normal",
            "2. Ataque especial",
            "3. Aumentar poder (x3)",
            "4. Defender",
            "5. Recuperar vida",
            "6. Mostrar estadisticas",
            "0. Salir"
        };
        // Usa el ancho del borde para alinear las opciones a la derecha de la tabla.
        int anchoTabla = tabla[0].length();

        // Separa visualmente el menú del texto anterior.
        System.out.println();
        // Recorre hasta cubrir todas las filas de la tabla y todas las opciones.
        for (int i = 0; i < Math.max(tabla.length, menu.length); i++) {
            // Obtiene la fila de estadísticas si existe; si no, deja el espacio vacío.
            String fila = i < tabla.length ? tabla[i] : "";
            // Obtiene la opción correspondiente si existe; si no, deja el espacio vacío.
            String opcion = i < menu.length ? menu[i] : "";
            // Imprime ambas columnas alineadas en una sola línea.
            System.out.printf("%-" + anchoTabla + "s  %s%n", fila, opcion);
        }
        // Alinea el mensaje de entrada con el menú y solicita una opción al jugador.
        System.out.printf("%" + (anchoTabla + 4) + "s", "");
        System.out.print("Seleccione: ");
    }

    public static void main(String[] args) {

        // Crea el lector que recibirá las opciones escritas por el jugador.
        Scanner entrada = new Scanner(System.in);

        // Crea al héroe con su nombre, fuerza, velocidad, vida y elemento.
        SuperHero rover = new SuperHero(
            "Rover", 20, 30, 100, "Espectro"
        );

        // Crea al villano con sus estadísticas iniciales.
        Villano enemigo = new Villano(
            "Threnodian", 15, 20, 120, "Aero"
        );

        // Presenta el título de la batalla al iniciar la partida.
        System.out.println("==============================");
        System.out.println(" BATALLA DE RESONADORES");
        System.out.println("==============================");

        // Compara las velocidades para informar quién es más rápido.
        if (rover.velocidad >= enemigo.velocidad) {
            // Informa que Rover tiene la misma velocidad o más que el villano.
            System.out.println(rover.nombre
                               + " tiene mayor velocidad.");
        } else {
            // Informa que el villano es más rápido que Rover.
            System.out.println(enemigo.nombre
                               + " tiene mayor velocidad.");
        }

        // Mantiene la partida activa hasta que termine o el jugador elija salir.
        boolean jugando = true;

        // Repite los turnos mientras la partida siga activa y ambos personajes vivan.
        while (jugando
                && rover.vida_hp > 0
                && enemigo.vida_hp > 0) {

            // Muestra la tabla y las acciones disponibles en cada turno del jugador.
            mostrarMenu(rover, enemigo);

            // Comprueba que la entrada siguiente sea un número entero.
            if (!entrada.hasNextInt()) {
                // Avisa que el dato no sirve como opción numérica.
                System.out.println("Ingrese un numero valido.");
                // Consume el dato incorrecto para poder leer la siguiente entrada.
                entrada.next();
                // Reinicia el ciclo sin ejecutar una acción del juego.
                continue;
            }

            // Lee la opción numérica elegida por el jugador.
            int opcion = entrada.nextInt();

            // Ejecuta la acción asociada con la opción seleccionada.
            switch (opcion) {
                case 1:
                    // Realiza un ataque normal contra el villano.
                    rover.atacar(enemigo);
                    break;

                case 2:
                    // Usa el ataque especial del héroe contra el villano.
                    rover.ataqueEspecial(enemigo);
                    break;

                case 3:
                    // Ataca al villano con el daño multiplicado por tres.
                    rover.atacar(enemigo, 3);
                    break;

                case 4:
                    // Activa la defensa del héroe para reducir el daño recibido.
                    rover.defender();
                    break;

                case 5:
                    // Recupera parte de la vida del héroe, sin superar su máximo.
                    rover.recuperar();
                    break;

                case 6:
                    // Muestra las estadísticas actuales y vuelve al menú sin turno enemigo.
                    mostrarEstadisticas(rover, enemigo);
                    continue;

                case 0:
                    // Desactiva el ciclo de juego para terminar la partida.
                    jugando = false;
                    // Informa que la partida fue finalizada por el jugador.
                    System.out.println("Juego finalizado.");
                    continue;

                default:
                    // Informa que la opción no corresponde con ninguna acción disponible.
                    System.out.println("Opcion no valida.");
                    // Vuelve a mostrar el menú sin ejecutar el turno del enemigo.
                    continue;
            }

            // Comprueba si el ataque del jugador dejó al villano sin vida.
            if (enemigo.vida_hp <= 0) {
                // Anuncia la victoria del héroe y termina el ciclo de batalla.
                System.out.println("¡Rover ha ganado!");
                break;
            }

            // El villano ataca normalmente si el jugador eligió defenderse.
            if (opcion == 4) {
                enemigo.atacar(rover);
            } else {
                // En las demás acciones, el villano usa su ataque potenciado.
                enemigo.hacerTrampa(rover);
            }

            // Comprueba si el ataque del villano dejó al héroe sin vida.
            if (rover.vida_hp <= 0) {
                // Anuncia la victoria del villano cuando el héroe es derrotado.
                System.out.println("# Threnodian ha ganado.");
            }
        }

        // Señala el cierre de la partida y muestra el resultado final de ambos personajes.
        System.out.println("\n===== ESTADO FINAL =====");
        mostrarEstadisticas(rover, enemigo);

        // Cierra el lector de entrada para liberar el recurso asociado a la consola.
        entrada.close();
    }
}

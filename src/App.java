
import java.util.Scanner;

public class App {

    private static String[] crearTablaEstadisticas(Personaje... personajes) {
        String borde = "+----------------+------------+--------+-----------+-------------+";
        String[] filas = new String[personajes.length + 4];
        filas[0] = borde;
        filas[1] = String.format("| %-14s | %-10s | %6s | %9s | %11s |",
                                 "Personaje", "Elemento", "Fuerza",
                                 "Velocidad", "Vida");
        filas[2] = borde;
        for (int i = 0; i < personajes.length; i++) {
            Personaje personaje = personajes[i];
            filas[3 + i] = String.format(
                "| %-14s | %-10s | %6d | %9d | %4d/%-6d |",
                personaje.nombre, personaje.elemento,
                personaje.fuerza, personaje.velocidad,
                personaje.vida_hp, personaje.vidaMaxima
            );
        }
        filas[filas.length - 1] = borde;
        return filas;
    }

    private static void mostrarEstadisticas(Personaje... personajes) {
        System.out.println();
        for (String fila : crearTablaEstadisticas(personajes)) {
            System.out.println(fila);
        }
    }

    private static void mostrarMenu(Personaje... personajes) {
        String[] tabla = crearTablaEstadisticas(personajes);
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
        int anchoTabla = tabla[0].length();

        System.out.println();
        for (int i = 0; i < Math.max(tabla.length, menu.length); i++) {
            String fila = i < tabla.length ? tabla[i] : "";
            String opcion = i < menu.length ? menu[i] : "";
            System.out.printf("%-" + anchoTabla + "s  %s%n", fila, opcion);
        }
        System.out.printf("%" + (anchoTabla + 4) + "s", "");
        System.out.print("Seleccione: ");
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        SuperHero rover = new SuperHero(
            "Rover", 20, 30, 100, "Espectro"
        );

        Villano enemigo = new Villano(
            "Threnodian", 15, 20, 120, "Aero"
        );

        System.out.println("==============================");
        System.out.println(" BATALLA DE RESONADORES");
        System.out.println("==============================");

        if (rover.velocidad >= enemigo.velocidad) {
            System.out.println(rover.nombre
                               + " tiene mayor velocidad.");
        } else {
            System.out.println(enemigo.nombre
                               + " tiene mayor velocidad.");
        }

        boolean jugando = true;

        while (jugando
                && rover.vida_hp > 0
                && enemigo.vida_hp > 0) {

            mostrarMenu(rover, enemigo);

            if (!entrada.hasNextInt()) {
                System.out.println("Ingrese un numero valido.");
                entrada.next();
                continue;
            }

            int opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    rover.atacar(enemigo);
                    break;

                case 2:
                    rover.ataqueEspecial(enemigo);
                    break;

                case 3:
                    rover.atacar(enemigo, 3);
                    break;

                case 4:
                    rover.defender();
                    break;

                case 5:
                    rover.recuperar();
                    break;

                case 6:
                    mostrarEstadisticas(rover, enemigo);
                    continue;

                case 0:
                    jugando = false;
                    System.out.println("Juego finalizado.");
                    continue;

                default:
                    System.out.println("Opcion no valida.");
                    continue;
            }

            // Verificar si el villano fue derrotado
            if (enemigo.vida_hp <= 0) {
                System.out.println("¡Rover ha ganado!");
                break;
            }

            // Turno del villano
            if (opcion == 4) {
                enemigo.atacar(rover);
            } else {
                enemigo.hacerTrampa(rover);
            }

            // Verificar si el heroe fue derrotado
            if (rover.vida_hp <= 0) {
                System.out.println("# Threnodian ha ganado.");
            }
        }

        System.out.println("\n===== ESTADO FINAL =====");
        mostrarEstadisticas(rover, enemigo);

        entrada.close();
    }
}

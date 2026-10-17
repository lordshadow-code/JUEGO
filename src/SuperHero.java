
public class SuperHero extends Personaje {

    public SuperHero(String nombre, int fuerza,
                     int velocidad, int vida_hp,
                     String elemento) {
        super(nombre, fuerza, velocidad,
              vida_hp, elemento);
    }

    // Ataque especial: doble de fuerza
    public void ataqueEspecial(Personaje objetivo) {
        int danio = fuerza * 2;

        System.out.println(nombre
                           + " utiliza su ataque especial "
                           + elemento + "!");

        objetivo.recibirDanio(danio);
    }

    // Sobrecarga: ataque con multiplicador
    public void atacar(Personaje objetivo, int multiplicador) {
        if (multiplicador < 1) {
            System.out.println("Multiplicador invalido.");
            return;
        }

        int danio = fuerza * multiplicador;

        System.out.println(nombre
                           + " potencia su ataque x"
                           + multiplicador + "!");

        objetivo.recibirDanio(danio);
    }
}

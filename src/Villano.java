
public class Villano extends Personaje {

    public Villano(String nombre, int fuerza,
                   int velocidad, int vida_hp,
                   String elemento) {
        super(nombre, fuerza, velocidad,
              vida_hp, elemento);
    }

    // Duplicar el ataque
    public void hacerTrampa(Personaje objetivo) {
        int danio = fuerza * 2;

        System.out.println(nombre
                           + " hace trampa y duplica "
                           + "su ataque!");

        objetivo.recibirDanio(danio);
    }
}

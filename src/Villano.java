
public class Villano extends Personaje {

    // Crea un villano y entrega sus atributos iniciales a la clase base.
    public Villano(String nombre, int fuerza,
                   int velocidad, int vida_hp,
                   String elemento) {
        // Reutiliza la inicialización común definida en Personaje.
        super(nombre, fuerza, velocidad,
              vida_hp, elemento);
    }

    // Realiza un ataque tramposo que duplica la fuerza del villano.
    public void hacerTrampa(Personaje objetivo) {
        // Calcula el daño especial duplicando la fuerza normal.
        int danio = fuerza * 2;

        // Anuncia que el villano está usando su ataque potenciado.
        System.out.println(nombre
                           + " hace trampa y duplica "
                           + "su ataque!");

        // Aplica el daño calculado al personaje objetivo.
        objetivo.recibirDanio(danio);
    }
}


public class SuperHero extends Personaje {

    // Crea un héroe y entrega sus atributos iniciales a la clase base.
    public SuperHero(String nombre, int fuerza,
                     int velocidad, int vida_hp,
                     String elemento) {
        // Reutiliza la inicialización común definida en Personaje.
        super(nombre, fuerza, velocidad,
              vida_hp, elemento);
    }

    // Ejecuta un ataque especial que causa el doble de la fuerza del héroe.
    public void ataqueEspecial(Personaje objetivo) {
        // Calcula el daño especial duplicando la fuerza normal.
        int danio = fuerza * 2;

        // Anuncia el uso de la habilidad e indica su elemento.
        System.out.println(nombre
                           + " utiliza su ataque especial "
                           + elemento + "!");

        // Aplica el daño calculado al objetivo.
        objetivo.recibirDanio(danio);
    }

    // Ejecuta un ataque cuya fuerza se multiplica por el valor indicado.
    public void atacar(Personaje objetivo, int multiplicador) {
        // Rechaza multiplicadores menores que uno para evitar daño inválido.
        if (multiplicador < 1) {
            // Informa que el valor recibido no puede usarse.
            System.out.println("Multiplicador invalido.");
            // Finaliza el método sin ejecutar el ataque.
            return;
        }

        // Calcula el daño multiplicando la fuerza por el multiplicador válido.
        int danio = fuerza * multiplicador;

        // Anuncia que el héroe aumentó el poder de su ataque.
        System.out.println(nombre
                           + " potencia su ataque x"
                           + multiplicador + "!");

        // Aplica el daño calculado al personaje objetivo.
        objetivo.recibirDanio(danio);
    }
}

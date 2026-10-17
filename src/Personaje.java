
public class Personaje {
    protected String nombre;
    protected int fuerza;
    protected int velocidad;
    protected int vida_hp;
    protected int vidaMaxima;
    protected String elemento;
    protected boolean defendiendo;

    public Personaje(String nombre, int fuerza,
                     int velocidad, int vida_hp,
                     String elemento) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.vida_hp = vida_hp;
        this.vidaMaxima = vida_hp;
        this.elemento = elemento;
        this.defendiendo = false;
    }

    // Ataque normal
    public void atacar(Personaje objetivo) {
        System.out.println(nombre + " ataca a "
                           + objetivo.nombre);
        objetivo.recibirDanio(fuerza);
    }

    // Recibir daño
    public void recibirDanio(int danio) {
        if (defendiendo) {
            danio = danio / 2;
            defendiendo = false;
            System.out.println(nombre
                               + " reduce el daño al 50%.");
        }

        vida_hp = Math.max(0, vida_hp - danio);

        System.out.println(nombre + " recibe "
                           + danio + " de daño.");
        System.out.println("Vida actual: " + vida_hp);
    }

    // Defensa
    public void defender() {
        defendiendo = true;
        System.out.println(nombre + " se defiende.");
    }

    // Recuperar vida
    public void recuperar() {
        int vidaAnterior = vida_hp;
        vida_hp = Math.min(vidaMaxima, vida_hp + 20);

        System.out.println(nombre + " recupera "
                           + (vida_hp - vidaAnterior)
                           + " puntos de vida.");
    }

    // Mostrar estadísticas
    public void mostrarEstadisticas() {
        System.out.println("\n--- ESTADISTICAS ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Elemento: " + elemento);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Vida: " + vida_hp
                           + "/" + vidaMaxima);
    }
}

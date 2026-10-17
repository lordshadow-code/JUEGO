
public class Personaje {
    // Guarda el nombre que se muestra para identificar al personaje.
    protected String nombre;
    // Guarda la cantidad de daño que causa un ataque normal.
    protected int fuerza;
    // Guarda la velocidad usada para comparar a los combatientes.
    protected int velocidad;
    // Guarda los puntos de vida que le quedan al personaje.
    protected int vida_hp;
    // Conserva el límite de vida para impedir recuperar más de lo permitido.
    protected int vidaMaxima;
    // Guarda el elemento asociado con el personaje.
    protected String elemento;
    // Indica si el personaje reducirá el siguiente daño que reciba.
    protected boolean defendiendo;

    // Inicializa los datos y el estado inicial de un personaje.
    public Personaje(String nombre, int fuerza,
                     int velocidad, int vida_hp,
                     String elemento) {
        // Conserva el nombre recibido para identificar al personaje.
        this.nombre = nombre;
        // Conserva la fuerza recibida para calcular sus ataques.
        this.fuerza = fuerza;
        // Conserva la velocidad recibida para compararla con otros personajes.
        this.velocidad = velocidad;
        // Establece la vida actual con el valor inicial recibido.
        this.vida_hp = vida_hp;
        // Usa la vida inicial como límite máximo de recuperación.
        this.vidaMaxima = vida_hp;
        // Conserva el elemento recibido para mostrarlo y usarlo en habilidades.
        this.elemento = elemento;
        // Inicia al personaje sin estar en posición defensiva.
        this.defendiendo = false;
    }

    // Ataca al personaje indicado con la fuerza normal de este personaje.
    public void atacar(Personaje objetivo) {
        // Anuncia quién está atacando a quién.
        System.out.println(nombre + " ataca a "
                           + objetivo.nombre);
        // Aplica al objetivo el daño equivalente a la fuerza del atacante.
        objetivo.recibirDanio(fuerza);
    }

    // Reduce la vida del personaje aplicando la defensa si está activa.
    public void recibirDanio(int danio) {
        // Si está defendiendo, reduce el daño a la mitad y consume esa defensa.
        if (defendiendo) {
            danio = danio / 2;
            defendiendo = false;
            // Informa al jugador que la defensa redujo el daño recibido.
            System.out.println(nombre
                               + " reduce el daño al 50%.");
        }

        // Resta el daño y limita la vida mínima a cero.
        vida_hp = Math.max(0, vida_hp - danio);

        // Informa cuánto daño se aplicó finalmente al personaje.
        System.out.println(nombre + " recibe "
                           + danio + " de daño.");
        // Muestra los puntos de vida que quedan después del impacto.
        System.out.println("Vida actual: " + vida_hp);
    }

    // Activa la protección que reducirá a la mitad el próximo daño recibido.
    public void defender() {
        defendiendo = true;
        // Confirma que el personaje adoptó la posición defensiva.
        System.out.println(nombre + " se defiende.");
    }

    // Recupera hasta 20 puntos de vida sin superar el máximo del personaje.
    public void recuperar() {
        // Guarda la vida actual para calcular cuánto se recuperó realmente.
        int vidaAnterior = vida_hp;
        // Suma 20 puntos y limita el resultado a la vida máxima.
        vida_hp = Math.min(vidaMaxima, vida_hp + 20);

        // Informa la diferencia real entre la vida nueva y la anterior.
        System.out.println(nombre + " recupera "
                           + (vida_hp - vidaAnterior)
                           + " puntos de vida.");
    }

    // Imprime los datos actuales del personaje en la consola.
    public void mostrarEstadisticas() {
        // Encabeza la sección de estadísticas.
        System.out.println("\n--- ESTADISTICAS ---");
        // Muestra el nombre del personaje.
        System.out.println("Nombre: " + nombre);
        // Muestra el elemento del personaje.
        System.out.println("Elemento: " + elemento);
        // Muestra la fuerza de sus ataques normales.
        System.out.println("Fuerza: " + fuerza);
        // Muestra su velocidad.
        System.out.println("Velocidad: " + velocidad);
        // Muestra la vida actual junto con su límite máximo.
        System.out.println("Vida: " + vida_hp
                           + "/" + vidaMaxima);
    }
}

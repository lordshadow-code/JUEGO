# Juego basado en Wuthering Waves

Este proyecto es un pequeño juego de batalla por turnos desarrollado en Java, con una temática inspirada en Wuthering Waves. El jugador controla a un personaje principal y debe enfrentarse a un enemigo, eligiendo acciones como atacar, defender, recuperar vida y usar habilidades especiales.

## Descripción del código

### 1. Clase `App`
La clase `App` es la clase principal del programa. Aquí se inicializan los personajes, se muestra el menú del juego y se controla el flujo principal de la batalla.

Funciones principales:
- Crear y mostrar estadísticas de los personajes en una tabla ASCII.
- Mostrar el menú con opciones del jugador.
- Leer la entrada del usuario mediante `Scanner`.
- Ejecutar el turno del enemigo después de la acción del jugador.
- Determinar si el jugador o el enemigo han ganado.

Opciones del menú:
1. Ataque normal
2. Ataque especial
3. Aumentar poder (x3)
4. Defender
5. Recuperar vida
6. Mostrar estadísticas
0. Salir

### 2. Clase `Personaje`
`Personaje` es la clase base que representa a cualquier combatiente del juego. Contiene los atributos principales del personaje:
- `nombre`
- `fuerza`
- `velocidad`
- `vida_hp`
- `vidaMaxima`
- `elemento`
- `defendiendo`

También incluye los métodos más importantes:
- `atacar(Personaje objetivo)`: realiza un ataque normal.
- `recibirDanio(int danio)`: reduce la vida y aplica la defensa si está activada.
- `defender()`: pone al personaje en estado defensivo.
- `recuperar()`: aumenta la vida hasta un máximo.
- `mostrarEstadisticas()`: imprime sus datos.

### 3. Clase `SuperHero`
`SuperHero` hereda de `Personaje` y representa al protagonista del juego.

Incluye:
- `ataqueEspecial(Personaje objetivo)`: inflige un daño equivalente al doble de la fuerza.
- `atacar(Personaje objetivo, int multiplicador)`: sobrecarga del ataque normal para aumentar el daño según un multiplicador.

Es el personaje principal y se usa para combatir contra el enemigo.

### 4. Clase `Villano`
`Villano` también hereda de `Personaje` y representa al rival.

Su método especial es:
- `hacerTrampa(Personaje objetivo)`: duplica el daño del ataque del villano, reforzando la dificultad del combate.

Esto hace que el enemigo sea más agresivo que el protagonista.

## Cómo funciona la partida

1. Se crean dos personajes: `Rover` y `Threnodian`.
2. Se compara la velocidad para saber quién actúa primero.
3. El jugador elige una acción desde el menú.
4. El enemigo responde con su ataque.
5. El juego continúa hasta que uno de los dos llega a 0 de vida o el usuario decide salir.
6. Al final se muestran las estadísticas del combate final.

## Estructura del proyecto

- `src/App.java`: lógica principal del juego.
- `src/Personaje.java`: clase base de todos los personajes.
- `src/SuperHero.java`: clase del héroe.
- `src/Villano.java`: clase del enemigo.

## Cómo ejecutar el juego

Desde la carpeta del proyecto, se compilan los archivos Java y luego se ejecuta la clase principal:

```bash
javac src/*.java
java -cp src App
```



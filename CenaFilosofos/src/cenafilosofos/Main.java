package cenafilosofos;

public class Main {

    public static void main(String[] args) {

        // Creamos la mesa con sus 5 palillos (5 semaforos).

        CenaFilosofos cena = new CenaFilosofos(5);

        // Vamos a crear y lanzar los 5 filosofos.
        for (int i = 0; i < 5; i++) {

            // Creamos el objeto Filosofo, que implementa Runnable.
            Filosofo f = new Filosofo(i, cena);

            // Creamos el hilo REAL que ejecutará su mrtodo run().
            // Le ponemos nombre para que se vea bien en consola.
            Thread hilo = new Thread(f, "Filosofo-" + i);

            // Lanzamos el hilo. Importante: start() → ejecuta run() en paralelo.
            hilo.start();
        }
    }
}

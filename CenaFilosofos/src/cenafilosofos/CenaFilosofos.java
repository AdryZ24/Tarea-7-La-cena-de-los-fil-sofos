package cenafilosofos;

import java.util.concurrent.Semaphore;

public class CenaFilosofos {

    // Array de semaforos, donde cada uno representa un palillo.
    // Cada palillo solo puede estar en manos de 1 filosofo al mismo tiempo
    private Semaphore[] palillos;

    // Semaforo que actua como "camarero".
    // evita el interbloqueo (deadlock)
    // solo pueden intentar comer N-1 filosofos a la vez.
    private Semaphore camarero;

    public CenaFilosofos(int numPalillos) {

        //array de semaforos/palillos
        palillos = new Semaphore[numPalillos];

        // Inicializamos cada palillo como un semaforo binario (1 → está libre)
        for (int i = 0; i < numPalillos; i++) {
            palillos[i] = new Semaphore(1);
        }


        // Permitimos que solo 4 filosofos puedan intentar coger palillos a la vez.
        // (Si hay 5, se pueden bloquear todos esperando).

        camarero = new Semaphore(numPalillos - 1);
    }

    // Metodo que un filosofo llama para intentar coger sus 2 palillos.
    public void cogerPalillos(int id) throws InterruptedException {

        // Palillo izquierdo: mismo id que el filosofo
        int izq = id;

        // Palillo derecho: (id + 1) modulo total
        // Esto crea la mesa circular
        int der = (id + 1) % palillos.length;

        // Primero pide permiso al camarero.
        // Si ya hay 4 filosofos intentando comer, este se quedará esperando.
        // Esto evita deadlock, porque siempre habrá al menos 1 palillo libre.
        camarero.acquire();

        // Ahora intenta coger el palillo izquierdo.
        // Si está pillado, se queda esperando el semaforo.
        palillos[izq].acquire();

        // Luego el derecho.
        // ningun otro filosofo puede cogerlos.
        palillos[der].acquire();
    }

    // Metodo para devolver los palillos despues de comer
    public void soltarPalillos(int id) {

        // Palillo izquierdo y derecho igual que antes
        int izq = id;
        int der = (id + 1) % palillos.length;

        // Liberamos los palillos para que otros filosofos puedan usarlos
        palillos[izq].release();
        palillos[der].release();

        // Y liberamos el permiso del camarero
        // Otro filosofo podrá entrar a intentar coger palillos
        camarero.release();
    }
}

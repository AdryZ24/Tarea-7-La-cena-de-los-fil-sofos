package cenafilosofos;

public class Filosofo implements Runnable {

    // Identificador del filosofo
    private int id;

    // Referencia a la mesa donde estan los palillos
    private CenaFilosofos cena;

    // Constructor que recibe el id y la mesa
    public Filosofo(int id, CenaFilosofos cena) {
        this.id = id;
        this.cena = cena;
    }

    @Override
    public void run() {
        try {
            // Los filosofos siempre estan pensando o comiendo.
            while (true) {

                // Primero piensa
                pensar();

                System.out.println("Filosofo " + id + " intenta comer...");

                // Intentamos coger los dos palillos (zona controlada con semaforos)
                // Si no estan disponibles, el filosofo espera.
                cena.cogerPalillos(id);

                // Una vez que tiene los dos palillos, puede comer
                comer();

                // Al terminar suelta los palillos para que los otros puedan comer
                cena.soltarPalillos(id);

                System.out.println("Filosofo " + id + " termina de comer y deja los palillos.");

            }

        } catch (InterruptedException e) {
            // Esto solo pasaria si se interrumpe el hilo
            System.out.println("El filosofo " + id + " fue interrumpido :( ");
        }
    }

    // Simula el tiempo que el filosofo pasa pensando
    private void pensar() throws InterruptedException {
        System.out.println("Filosofo " + id + " esta pensando...");
        // sleep es solo pa simular tiempo de espera
        Thread.sleep((long) (Math.random() * 2000 + 500));
    }

    // Simula que esta comiendo
    private void comer() throws InterruptedException {
        System.out.println("Filosofo " + id + " esta comiendo");
        // Simulamos el tiempo de comida
        Thread.sleep((long) (Math.random() * 1500 + 500));
    }
}

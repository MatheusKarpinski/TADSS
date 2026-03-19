import java.util.Random;

public class Exerc3 {
    public static void main(String[] args) {
        short[] numeros = new short[1_000_000_000];
        Random random = new Random();
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (short) random.nextInt(1_000);
        }
        System.out.println("Inicio");
        long ini = System.currentTimeMillis();
        Somador seq = new Somador(numeros);
        seq.run();
        System.out.println("Soma seq: " + seq.getResultado());
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));
        ini = System.currentTimeMillis();
        int size = numeros.length / 10;
        Thread[] threads = new Thread[10];
        Somador[] somadores = new Somador[10];
        long resultado = 0;
        for (int i = 0; i < 10; i++) {
            short[] chunck = new short[size];
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofPlatform().start(somadores[i]);
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 10TP: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis();
        size = numeros.length / 100;
        threads = new Thread[100];
        somadores = new Somador[100];
        resultado = 0;
        for (int i = 0; i < 100; i++) {
            short[] chunck = new short[size];
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofPlatform().start(somadores[i]);
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 100TP: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis();
        size = numeros.length / 10;
        threads = new Thread[10];
        somadores = new Somador[10];
        resultado = 0;
        for (int i = 0; i < 10; i++) {
            short[] chunck = new short[size];
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofVirtual().start(somadores[i]);
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 10TV: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis();
        size = numeros.length / 100;
        threads = new Thread[100];
        somadores = new Somador[100];
        resultado = 0;
        for (int i = 0; i < 100; i++) {
            short[] chunck = new short[size];
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofVirtual().start(somadores[i]);
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 100TV: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

    }
}

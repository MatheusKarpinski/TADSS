import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exerc4 {

    private static final List<Integer> lista = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Adicionar(), "Add-1");
        Thread t2 = new Thread(new Adicionar(), "Add-2");
        Thread t3 = new Thread(new Remover(), "Remove");
        Thread t4 = new Thread(new Impressão(), "Print");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    static class Adicionar implements Runnable {
        @Override
        public void run() {
            while (true) {
                int valor = random.nextInt(100);

                lista.add(valor);
                System.out.println(Thread.currentThread().getName() + " adicionou: " + valor);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Remover implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (!lista.isEmpty()) {
                    int removido = lista.remove(0);
                    System.out.println("Removido: " + removido);
                }

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Impressão implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("Lista atual: " + lista);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
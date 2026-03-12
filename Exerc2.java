public class Exerc2 implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        Thread[] ts = new Thread[10];
        Exerc2 e1 = new Exerc2();
        for (int i = 0; i < 10; i++) {
            ts[i] = Thread.ofVirtual().name("T" + i).start(e1);        
        }
        for (int = 0; i < 10; i++) {
            try {
                ts[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
}

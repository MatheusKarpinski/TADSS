import java.util.Date;

public class Exerc1 extends Thread {
    public void run() {
        System.out.println(getName() + " " + new Date());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Exerc1 t = new Exerc1();
            t.start();
        }
    }
    
}
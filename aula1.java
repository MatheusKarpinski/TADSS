public class aula1 extends Thread {
    private int id;

    public aula1(int id) {
        this.id = id;
    }
    
    public void run(){
        for (int i = 1; i<=10; i++){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        for (int x = 0; x < 10; x++){
        aula1 a1 = new aula1(x);
        a1.start();
        }
        System.out.println("ok");
}
}
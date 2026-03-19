public class Somador implements Runnable {
    private short[] chunck;
    private long resultado;

    public Somador(short[] chunck) {
        this.chunck = chunck;
    }

    public long getResultado() {
        return resultado;
    }

    @Override
    public void run() {
        for (int i = 0; i < chunck.length; i++) {
            resultado += chunck[i];
        }
    }
}

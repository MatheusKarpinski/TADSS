package produtorconsumidor;

public class Cozinha {
    public static void main(String[] args) {
        PilhaPratos sujos = new PilhaPratos(100);
        PilhaPratos escorredor = new PilhaPratos(10);
        PilhaPratos limpos = new PilhaPratos(100);

        for (int i = 1; i <= 100; i++) {
            sujos.addPrato(new Prato(i, Estado.Sujo));
        }

        System.out.println(sujos);

        Lavador lavador = new Lavador(sujos, escorredor);
        Thread.ofPlatform().start(lavador);
    }
}

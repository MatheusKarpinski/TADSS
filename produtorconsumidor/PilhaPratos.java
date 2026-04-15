package produtorconsumidor;

import java.util.Arrays;

public class PilhaPratos {
    private Prato[] pratos;
    private int qtde;

    public PilhaPratos(int tamanho) {
        pratos = new Prato[tamanho];
    }

    public void addPrato(Prato prato) {
        pratos[qtde] = prato;
        qtde++;
    }

    public Prato removePrato() {
        qtde--;
        Prato prato = pratos[qtde];
        pratos[qtde] = null;
        return prato;
    }

    public boolean temPrato() {
        return qtde > 0;
    }

    @Override
    public String toString() {
        return "PilhaPratos [" + Arrays.toString(pratos) + "]";
    }
}

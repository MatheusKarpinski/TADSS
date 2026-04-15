import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Exercicio05 {

    // ============================================================
    // CONFIGURAÇÕES DO TESTE
    // ============================================================
    static final int    QUANTIDADE_DE_CONTAS   = 100;
    static final double SALDO_INICIAL          = 1_000.00;
    static final double VALOR_MAXIMO           = 2_500.00;
    static final int    QUANTIDADE_DE_SISTEMAS = 5;
    static final int    DURACAO_SEGUNDOS       = 180;   // 3 minutos
    static final int    INTERVALO_RELATORIO    = 5;     // em segundos

    // ============================================================
    // CONTAS DO BANCO
    // ============================================================
    // Cada posição do array é o saldo de uma conta
    static double[] saldos = new double[QUANTIDADE_DE_CONTAS];

    // Cada conta tem o seu próprio cadeado para evitar conflitos
    static ReentrantLock[] cadeados = new ReentrantLock[QUANTIDADE_DE_CONTAS];

    // Contador de transferências — AtomicInteger é seguro para uso concorrente
    static AtomicInteger totalTransferencias = new AtomicInteger(0);

    // Variável que controla se o teste ainda está rodando
    static volatile boolean testeRodando = true;

    // ============================================================
    // MÉTODO: calcular o total de dinheiro no cofre
    // ============================================================
    static double calcularTotalNoCofre() {
        double total = 0;
        for (double saldo : saldos) {
            total += saldo;
        }
        return total;
    }

    // ============================================================
    // CLASSE: Sistema que faz transferências (cada um vira uma Thread)
    // ============================================================
    static class SistemaDeTransferencia implements Runnable {

        int idSistema;
        Random sorteio = new Random();

        // Construtor — recebe o número do sistema (0 a 4)
        SistemaDeTransferencia(int idSistema) {
            this.idSistema = idSistema;
        }

        @Override
        public void run() {
            while (testeRodando) {

                // Sorteia duas contas diferentes
                int contaOrigem  = sorteio.nextInt(QUANTIDADE_DE_CONTAS);
                int contaDestino = sorteio.nextInt(QUANTIDADE_DE_CONTAS);

                // Não pode transferir para a mesma conta
                if (contaOrigem == contaDestino) {
                    continue;
                }

                // Sorteia o valor da transferência (entre R$ 0,01 e R$ 2.500,00)
                double valor = Math.round(sorteio.nextDouble() * VALOR_MAXIMO * 100.0) / 100.0;
                if (valor <= 0) continue;

                // Para evitar DEADLOCK, sempre trava os cadeados em ordem crescente
                // (conta de número menor primeiro)
                int primeira = Math.min(contaOrigem, contaDestino);
                int segunda  = Math.max(contaOrigem, contaDestino);

                cadeados[primeira].lock();
                try {
                    cadeados[segunda].lock();
                    try {

                        // Só transfere se houver saldo suficiente
                        if (saldos[contaOrigem] >= valor) {
                            saldos[contaOrigem]  -= valor;
                            saldos[contaDestino] += valor;
                            totalTransferencias.incrementAndGet();
                        }

                    } finally {
                        cadeados[segunda].unlock();   // sempre destrava, mesmo se der erro
                    }
                } finally {
                    cadeados[primeira].unlock();
                }
            }
        }
    }

    // ============================================================
    // CLASSE: Relatório a cada 5 segundos (também vira uma Thread)
    // ============================================================
    static class RelatorioDeStatus implements Runnable {

        @Override
        public void run() {
            long inicio = System.currentTimeMillis();

            while (testeRodando) {
                try {
                    Thread.sleep(INTERVALO_RELATORIO * 1000L);
                } catch (InterruptedException e) {
                    break;
                }

                if (!testeRodando) break;

                long   tempoPassado    = (System.currentTimeMillis() - inicio) / 1000;
                double totalCofre      = calcularTotalNoCofre();
                int    transAteAgora   = totalTransferencias.get();

                System.out.printf("  ⏱  %3ds | 💰 Cofre: R$ %,.2f | 🔄 Transferências: %,d%n",
                        tempoPassado, totalCofre, transAteAgora);
            }
        }
    }

    // ============================================================
    // MÉTODO PRINCIPAL
    // ============================================================
    public static void main(String[] args) throws InterruptedException {

        // --- Inicializa as contas e os cadeados ---
        for (int i = 0; i < QUANTIDADE_DE_CONTAS; i++) {
            saldos[i]   = SALDO_INICIAL;
            cadeados[i] = new ReentrantLock();
        }

        // --- Cabeçalho ---
        System.out.println("=".repeat(60));
        System.out.println("       BANCO FIRMEZA - TESTE DE PERFORMANCE (Java)");
        System.out.println("=".repeat(60));
        System.out.printf("  Contas criadas    : %d%n",           QUANTIDADE_DE_CONTAS);
        System.out.printf("  Saldo inicial     : R$ %,.2f por conta%n", SALDO_INICIAL);
        System.out.printf("  Total no cofre    : R$ %,.2f%n",     calcularTotalNoCofre());
        System.out.printf("  Sistemas rodando  : %d%n",           QUANTIDADE_DE_SISTEMAS);
        System.out.printf("  Duração do teste  : %d segundos%n",  DURACAO_SEGUNDOS);
        System.out.printf("  Valor máx. transf.: R$ %,.2f%n",     VALOR_MAXIMO);
        System.out.println("=".repeat(60));
        System.out.println("\n🚀 Iniciando teste...\n");

        // --- Cria e inicia as threads dos sistemas de transferência ---
        Thread[] threadsSistemas = new Thread[QUANTIDADE_DE_SISTEMAS];
        for (int i = 0; i < QUANTIDADE_DE_SISTEMAS; i++) {
            threadsSistemas[i] = new Thread(new SistemaDeTransferencia(i));
            threadsSistemas[i].start();
        }

        // --- Cria e inicia a thread do relatório ---
        Thread threadRelatorio = new Thread(new RelatorioDeStatus());
        threadRelatorio.start();

        // --- Programa principal espera 3 minutos ---
        Thread.sleep(DURACAO_SEGUNDOS * 1000L);

        // --- Sinaliza para todas as threads pararem ---
        testeRodando = false;

        // --- Aguarda as threads terminarem ---
        for (Thread t : threadsSistemas) {
            t.join(2000);   // espera até 2 segundos cada
        }
        threadRelatorio.join(2000);

        // ============================================================
        // RESULTADO FINAL
        // ============================================================
        double totalEsperado = QUANTIDADE_DE_CONTAS * SALDO_INICIAL;
        double totalFinal    = calcularTotalNoCofre();
        double diferenca     = Math.abs(totalFinal - totalEsperado);
        boolean cofreOk      = diferenca < 0.01;
        int     transferencias = totalTransferencias.get();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ✅ RESULTADO FINAL DO TESTE");
        System.out.println("=".repeat(60));
        System.out.printf("  Total de transferências  : %,d%n",        transferencias);
        System.out.printf("  Média por segundo        : %,.1f transações/s%n",
                (double) transferencias / DURACAO_SEGUNDOS);
        System.out.printf("  Total no cofre ao final  : R$ %,.2f%n",   totalFinal);
        System.out.printf("  Total esperado           : R$ %,.2f%n",   totalEsperado);

        if (cofreOk) {
            System.out.println("\n  💚 APROVADO! O cofre manteve o valor correto de R$ 100.000,00");
        } else {
            System.out.printf("%n  ❌ FALHA! Diferença de R$ %,.2f encontrada no cofre%n", diferenca);
        }
        System.out.println("=".repeat(60));
    }
}

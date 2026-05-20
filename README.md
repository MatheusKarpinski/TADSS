# Latencia e Throughput
- Latência é o atraso no tempo de resposta (ex: ping), medindo o tempo que um dado leva de um ponto a outro. Throughput é a taxa de transferência real, ou volume de dados entregues com sucesso por unidade de tempo. latência é velocidade, throughput é capacidade.

- Principais Diferenças e Características: Latência: Mede o tempo de ida e volta de um pacote. Baixa latência significa resposta rápida. Throughput: Mede a quantidade de dados por segundo (ex: MB/s) ou pacotes por segundo. Crucial para download, streaming, backups.

- Relação e Trade-off: Embora distintos, alta latência pode reduzir o throughput. Otimizar para baixa latência (ex: sem cache) pode diminuir o throughput, e aumentar o throughput (ex: muitos pacotes em lote) pode aumentar a latência.

- Exemplo: Latência: Tempo que um carro leva para ir de A a B. Throughput: Número de carros que passam pelo pedágio por minuto.

# Lei de Moore
- A Lei de Moore é uma observação feita por Gordon Moore em 1965, cofundador da Intel, que prevê que o número de transistores em um chip dobra aproximadamente a cada dois anos, aumentando a capacidade de processamento e reduzindo custos. Essa regra exponencial impulsionou a tecnologia por décadas, resultando em dispositivos menores, mais rápidos e eficientes.

# Banco de Dados Relacional
Um banco de dados relacional organiza dados em tabelas com linhas (registros) e colunas (atributos) interligadas, utilizando chaves primárias e estrangeiras para garantir a consistência. Baseado em SQL é ideal para transações estruturadas que exigem alta precisão e integridade, como sistemas financeiros e de inventário.
- Tabelas (Relações): Estruturas que armazenam dados de entidades específicas.
- Linhas (Registros/Tuplas): Cada entrada única em uma tabela.
- Colunas (Campos/Atributos): Características do dado armazenado.
- Chave Primária (PK): Identificador exclusivo para cada linha em uma tabela.
- Chave Estrangeira (FK): Campo que vincula uma tabela a outra, criando um relacionamento.
- SQL (Structured Query Language): Linguagem padrão para interagir com bancos relacionais.

# Diferença entre Stack e Heap
- Stack: é automática, rápida e organizada em LIFO (último a entrar, primeiro a sair) para dados locais e pequenos / área de memoria da thread (variaveis locais e pilhas de chamada de funções)
- Heap: O Heap é maior, de acesso mais lento, usado para alocação dinâmica e exige gerenciamento manual (C/C++) ou Garbage Collector (Java/C#).

# Tipos de Threads
- Thread de Plataforma (Sistema Operacional)
- Green Thread: Foco em dispositivos com 1 nucleo e é simulada pela aplicação
- Virtual Threads

# Diferença entre Thread de Plataforma (OS) e Thread Virtual (Coroutines)
 - Thread de Plataforma (OS): São gerenciadas pelo sistema operacional, mais pesadas e com maior custo de criação e troca de contexto, mas oferecem paralelismo real.
 - Threads Virtuais (Corrotinas): São leves e gerenciadas pela aplicação, permitindo grande escalabilidade com baixo custo, porém geralmente compartilham threads reais para execução.
   
# Diferença entre Paralelismo e Concorrência
- Concorrência: é a estruturação e gerenciamento de múltiplas tarefas que progridem em períodos sobrepostos (alternando entre elas), frequentemente em um único núcleo. 
- Paralelismo: é a execução física e simultânea de múltiplas tarefas ao mesmo tempo, exigindo múltiplos núcleos de CPU.
- A concorrência lida com várias coisas, o paralelismo faz várias coisas. 
# Diferença entre Mutex e Semaphore (semáforo)
- Mutex: é um mecanismo de exclusão mútua (bloqueio) que garante que apenas uma thread acesse um recurso por vez.
- Semáforo é um mecanismo de sinalização que gerencia o acesso a um número limitado de recursos (contador), permitindo que várias threads acessem simultaneamente.

# Alguns conceitos importantes:
- Processo: um programa em execução
- Thread: um fluxo de execução dentro do processo
- Stack: área de memoria da thread (variaveis locais e pilhas de chamada de funções)
- Processo/thread daemon: processo ou thread que não tem fim
- Prioridade na thread: valor informado ao escalonador para ele tentar levar em conta
- Starvation: Thread que não consegue tempo de CPU
- Condição de corrida: O resultado final torna-se dependente da temporização imprevisível ("corrida") dos eventos, gerando comportamentos não determinísticos, corrupção de dados ou falhas de segurança
- Escalonador Cooperativo e Preemptivo (time slice): A principal diferença é o controle: no escalonamento preemptivo, o sistema operacional interrompe processos à força para dar vez a outros, garantindo responsividade. No cooperativo, o processo mantém a CPU até terminar ou ceder voluntariamente o controle, sendo ideal para sistemas embarcados simples, mas arriscado para multitarefa

# 08/04/2026

- Partes de um nucleo de um processador: Cache, Ula, Registradores, Clock
  
- Cache = memoization é uma técnica de otimização em programação usada para acelerar programas, armazenando os resultados de chamadas de funções dispendiosas e retornando o resultado em cache quando as mesmas entradas ocorrem novamente. É uma forma específica de cache, comumente usada em programação funcional e dinâmica para evitar cálculos repetidos.
  
- Cache Coerence Protocol MESI =
Modified (Modificado - M): A linha de cache foi alterada e é exclusiva deste núcleo. O dado é diferente da memória principal.
Exclusive (Exclusivo - E): A linha de cache está presente apenas neste núcleo, mas é idêntica à memória principal.
Shared (Compartilhado - S): A linha de cache pode ser lida por múltiplos núcleos e é idêntica à memória principal.
Invalid (Inválido - I): A linha de cache não contém dados válidos. 

- Doug Lea criou uma biblioteca para resolver questões cristicas e incorporou dentro do Java para resolver os problemas da programação concorrente

# 15/04/2026
- Synchronized: Permite que apenas uma thread por vez execute aquela parte crítica

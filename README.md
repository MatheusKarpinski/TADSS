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
- Stack: é automática, rápida e organizada em LIFO (último a entrar, primeiro a sair) para dados locais e pequenos.
- Heap: O Heap é maior, de acesso mais lento, usado para alocação dinâmica e exige gerenciamento manual (C/C++) ou Garbage Collector (Java/C#).

# Tipos de Threads
- Thread de Plataforma (Sistema Operacional)
- Green Thread: Foco em dispositivos com 1 nucleo e é simulada pela aplicação
- Virtual Threads

# Trabalho GB - Somando Dominós

Alunos: **Leonardo Meinerz Ramos e Bianca Caye Juchem**

Professora: **Andriele Busatto do Carmo**

## Como executar a solução

Para executar o projeto, é necessario utilizar Java 11 e Maven 3.6.3

para compilar o projeto, rode o seguinte comando na pasta raiz do projeto:

```
mvn clean install
```

para executar a solução principal, rode o seguinte comando:

```
 mvn exec:java -Dexec.mainClass="bootstrap.Solution"
```

Por padrão, serão executadas as soluções para o arquivo `in`, `in1`, `in2` e `in3`.

Se for necessário adicionar algum arquivo para teste, é necessario adicionar o arquivo na pasta `src/main/resources/` e
também adiciona-lo na seguinte lista:
https://github.com/kibutzzz/soma-dominos/blob/4662770e573de8a6b98a20634c9a9cd5671094b0/src/main/java/bootstrap/Solution.java#L13

Para remover um caso de teste, basta remover o nome do arquivo da mesma lista.

> **_ATENÇÃO:_** Após qualquer alteracão, é necessario recompilar o projeto para que as mudanças sejam aplicadas.

### Ferramentas utilizadas para execução

Foram utilizadas especificamente as seguintes versões para o desenvolvimento:

```
$ mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 11.0.9.1, vendor: SAP SE, runtime: /Library/Java/JavaVirtualMachines/sapmachine-jdk-11.0.9.1.jdk/Contents/Home
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"

```

## Definição do problema

Você foi convidado a criar um desafio matemático usando dominós. Depois de pensar por algum tempo, você elaborou o
seguinte jogo:
O jogo inicia com um jogador recebendo um pequeno conjunto de dominós. Esses dominós devem ser escolhidos aleatoriamente
de um outro conjunto de peças, esse último grande e variado. Usando o conjunto de dominós recebido, o jogador deve
encontrar uma combinação na qual os dominós colocados lado a lado na mesa, devem apresentar a mesma soma tanto para a
parte de cima quanto para a parte de baixo dos dominós. Por exemplo, para o conjunto de dominós `[2, 1], [6, 3], [3, 1]`
, uma combinação correta seria:

```
1 6 1
2 3 3
```

Se não for possível encontrar uma combinação usando todos os dominós escolhidos, o jogador pode descartar um deles, mas
o valor da soma na combinação deve ser a maior possível. Além disso, se existir mais de um dominó que possa ser
descartado mantendo a mesma soma (para a parte de baixo e para a parte de cima), o jogador deve descartar o
dominó `[a, b]` de modo que a ≤ b e a é o menor valor possível considerando todos os dominós que podem ser descartados.
Você deve escrever um programa que, dado um conjunto de dominós, tenta encontrar uma combinação que satisfaz as
condições do desafio, descartando um dominó se necessário. Note que um dominó `[a, b]` também pode ser escrito como
`[b, a]`.

## Entrada

Seu programa deve processar diversos casos de testes. A primeira linha de um caso de teste contém um único inteiro N, a
quantidade de dominós no teste (0 ≤ N ≤ 400). Cada uma das N linhas seguintes contém dois inteiros Xi e Yi descrevendo
um dominó que foi dado ao jogador (0 ≤ Xi ≤ 1000 e 0 ≤ Yi ≤ 1000). O valor N = 0 indica o fim da entrada. As informações
devem ser lidas de arquivos.

## Saída

Para cada caso de teste, seu programa deve produzir uma linha descrevendo o resultado. Se não for possível encontrar uma
combinação, imprima a palavra “impossível”. Se for possível encontrar uma combinação, imprima sua soma e a descrição do
dominó descartado (se houver algum). Se você precisou realmente descartar um dominó, descreva na forma: “descartado o
dominó X Y”, onde X ≤ Y; senão, imprima “nenhum dominó descartado”. Todas as mensagens devem sem impressas na saída
padrão: tela.

## Exemplo de entrada

```
4
1 4
2 9
2 1
0 4
2
8 1
9 4
3
6 3
1 2
3 1
0
```

## Exemplo de saída

``` 
10 descartado o dominó 1 2
impossível
8 nenhum dominó descartado
```
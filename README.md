# Relatório de Projeto: Implementação e Análise de Matrizes Esparsas

## INTRODUÇÃO
Este relatório apresenta o projeto desenvolvido como parte da disciplina de Estrutura de Dados, tendo como objetivo a implementação e análise de matrizes esparsas para comparar a eficiência e o desempenho. O projeto envolve duas abordagens: a implementação de matrizes esparsas estáticas, armazenadas em vetores, e a implementação de matrizes esparsas dinâmicas, utilizando estruturas encadeadas por meio de Elos.

1. **Matrizes Esparsas Estáticas**: Armazenadas em vetores.
2. **Matrizes Esparsas Dinâmicas**: Implementadas com estruturas encadeadas (Elos).



---

## 1. IMPLEMENTAÇÃO
Foram desenvolvidas três classes principais:
1. **MatrizEstatica**: Representa uma matriz estática armazenada em um vetor.
2. **MListaEncadeada**: Implementa uma matriz dinâmica usando vetores de listas encadeadas.
3. **Produção**: Gera números aleatórios e insere em posições aleatórias da matriz, garantindo um grau de esparsidade de 60%.

# Análise de Desempenho: MLE vs ME

## Introdução
No início do experimento, esperava-se que a MLE (Matriz Lista Encadeada) fosse mais eficiente do que a ME (Matriz Estática) para matrizes esparsas. A justificativa para essa hipótese era que a MLE, por sua estrutura otimizada para lidar com elementos nulos, realizaria menos operações e, consequentemente, teria um tempo de execução reduzido.

## Resultados Observados
Os resultados dos experimentos mostraram que esse comportamento esperado nem sempre se confirmou. Para entradas de até 200 elementos, ambas as abordagens tiveram tempos de execução semelhantes, porém, a MLE foi mais lenta na maioria dos casos. Para entradas maiores que 200, a MLE apresentou uma queda significativa no desempenho, chegando a triplicar o tempo de execução em métodos como a soma de matrizes.

Por outro lado, a Matriz Estática, apesar de também perder desempenho com o aumento da entrada, manteve-se mais regular mesmo para matrizes grandes, como 1000 x 1000.
Para explicar o imprevisto comportamento da Matriz Lista Encadeada, uma hipótese seria que o grau de esparsidade teria que ser ainda maior, com mais de 90% de elementos nulos.


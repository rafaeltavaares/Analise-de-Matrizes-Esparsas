public class experimentoTransposta {
    public static void experimento(){
        // EXPERIMENTO 1: MATRIZ LISTA ENCADEADA

        int[] tamanhos = {10, 20, 30, 40, 50, 100, 200, 500, 1000};

        float grauEsparcidade = 60.0f; // 60% de zeros

        System.out.println(" ---- >> Testes com Matriz Lista Encadeada << ---- ");
        System.out.println(" ---- >> Calcular Transposta << ---- ");
        System.out.println("---------------------------------------------------");

        for (int tamanho : tamanhos) {
            long tempoTotal = 0;

            System.out.println("Tamanho da Matriz: " + tamanho + " x " + tamanho);
            System.out.print("Vezes que o método foi executado: ");

            for (int i = 0; i < 10; i++) {

                MListaEncadeada matriz = Produção.gerarMatrizListaEncadeada(tamanho, tamanho, grauEsparcidade);

                long tempo1 = System.nanoTime();

                MListaEncadeada restultante = matriz.calcularTransposta(matriz);

                long tempo2 = System.nanoTime();

                long tempofinal = tempo2 - tempo1;
                tempoTotal += tempofinal;

                //System.out.println("Matriz resultante:");
                //resultante.imprime(true);
                System.out.print(i+1 + ", ");
            }

            double tempoMedio = tempoTotal / 10.0 / 1000000;

            System.out.println();
            System.out.println("Tempo de execução em média: " + tempoMedio + " ms");
            System.out.println("---------------------------------------------------");

        }

        // EXPERIMENTO 2: MATRIZ ESTÁTICA
        System.out.println(" ---- >> Testes com Matriz Estática << ---- ");
        System.out.println(" ---- >> Calcular Transposta << ---- ");
        System.out.println("---------------------------------------------------");

        for (int tamanho : tamanhos) {
            long tempoTotal = 0;

            System.out.println("Tamanho da Matriz: " + tamanho + " x " + tamanho);
            System.out.print("Vezes que o método foi executado: ");

            for (int i = 0; i < 10; i++) {

                MatrizEstática matriz = Produção.gerarMatrizEstática(tamanho, tamanho, grauEsparcidade);

                long inicio = System.nanoTime();

                MatrizEstática restultante = matriz.calcularTransposta(matriz);

                long fim = System.nanoTime();

                long tempoExecucao = fim - inicio;
                tempoTotal += tempoExecucao;

                //System.out.println("Matriz resultante:");
                //resultante.imprime();
                System.out.print(i+1 + ", ");
            }

            double tempoMedio = tempoTotal / 10.0 / 1000000;

            System.out.println();
            System.out.println("Tempo de execução em média: " + tempoMedio + " ms");
            System.out.println("---------------------------------------------------");

        }
    }
}

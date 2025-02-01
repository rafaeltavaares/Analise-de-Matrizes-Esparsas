import java.util.Random;
public class Produção {
    public static MatrizEstática gerarMatrizEstática(int lin, int col, float grauEsparcidade) {
        Random r = new Random();
        float parcelaNum = 1 - (grauEsparcidade / 100);

        // Determina o número total de elementos que serão preenchidos
        int totalNumeros = Math.round(lin * col * parcelaNum);


        // Cria a matriz e preenche os valores de acordo com o grau de esparsidade
        MatrizEstática matriz = new MatrizEstática(lin, col);

        while (totalNumeros > 0) {
            int coluna = (int) (Math.random() * col);
            int linha = (int) (Math.random() * lin);
            // Garante que não substituímos valores já inseridos.
            if (matriz.recuperarElemento(linha, coluna) != 1) {
                matriz.inserir(linha, coluna, r.nextInt(10) + 1);
                totalNumeros--;
            }
        }

        return matriz;
    }
    public static boolean isMatrizEsparca(MatrizEstática matriz, int grauEsparcidade) {
        float parcelaZeros = (float) grauEsparcidade / 100;
        int qtdZeros = 0;
        float totalZerosEsperados = (float) (matriz.getLinha() * matriz.getColuna() * parcelaZeros);

        for (int i = 0; i < matriz.getLinha(); i++) {
            for (int j = 0; j < matriz.getColuna(); j++) {
                if (matriz.recuperarElemento(i, j) == 0) {
                    qtdZeros++;
                }
            }
        }

        return qtdZeros == Math.round(totalZerosEsperados);
    }

    public static MListaEncadeada gerarMatrizListaEncadeada(int lin, int col, float grauEsparcidade) {
        Random r = new Random();
        float parcelaNum = 1 - (grauEsparcidade / 100);

        // Determina o número total de elementos que serão preenchidos
        int totalNumeros = Math.round(lin * col * parcelaNum);

        // Cria a matriz e preenche os valores de acordo com o grau de esparsidade
        MListaEncadeada matriz = new MListaEncadeada(lin, col);

        while (totalNumeros > 0) {
            int coluna = (int) (Math.random() * col);
            int linha = (int) (Math.random() * lin);
            // Garante que não substituímos valores já inseridos.
            if (matriz.getElemento(linha, coluna) != 1) {
                matriz.insere(linha, coluna, r.nextInt(10) + 1);
                totalNumeros--;
            }
        }
        return matriz;
    }
}

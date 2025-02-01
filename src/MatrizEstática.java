public class MatrizEstática {
    private int coluna;
    private int linha;
    private int[][] matriz;
    private boolean isVazia;

    public MatrizEstática(int coluna,int linha){
        this.coluna = coluna;
        this.isVazia = true;
        this.linha = linha;
        this.matriz = new int[linha][coluna];
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    private int[][] getMatriz(){
        return matriz;
    }
    public void inserir(int lin,int col, int val){
        if (lin > this.linha || col > this.coluna || lin < 0 || col < 0){
            System.out.println("Linha ou coluna fora do intervalo permitido.");
        } else {
            matriz[lin][col] = val;
            // verificar se pode inserir zero isVazia = false;
        }
    }

    public void remover(int col, int lin){
        if (lin > this.linha || col > this.coluna || lin < 0 || col < 0){
            System.out.println("Linha ou coluna fora do intervalo permitido.");
        } else {
            matriz[lin][col] = 0;
        }
    }

    public int recuperarElemento(int lin, int col) {
        // Verifica se os índices estão dentro dos limites válidos
        if (lin < 0 || lin >= linha || col < 0 || col >= coluna) {
            throw new IllegalArgumentException("Linha ou coluna fora do intervalo permitido.");
        }

        // Retorna o elemento na posição especificada
        return matriz[lin][col];
    }

    public int buscarElem(int val){
        for(int i = 0; i <= linha; i++){
            for(int j = 0; j <= coluna; j++){
                if(val == matriz[i][j]) return val;
            }
        }
        return Integer.MIN_VALUE;
    }

    public void imprimirMatriz(){

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Pula para a próxima linha
        }
    }

    public void representarMatrizVazia(){
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(matriz[i][j] != 0) this.inserir(i,j,0);
            }
        }
        isVazia = true;
    }
    public boolean isMatrizVazia() {
        if (isVazia) {
            return true;
        }

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if (matriz[i][j] != 0) {
                    isVazia = false;
                    return false;
                }
            }
        }
        isVazia = true;
        return true;
    }

    public boolean isMatrizDiagonal(){
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(i != j  && matriz[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isMatrizLinha() {
        int linhasCheias = 0;

        for (int i = 0; i < linha; i++) {
            boolean isLinhaCheia = true;

            for (int j = 0; j < coluna; j++) {
                if (matriz[i][j] == 0) {
                    isLinhaCheia = false;
                    break;
                }
            }

            if (isLinhaCheia) {
                linhasCheias++;
                if (linhasCheias > 1) {
                    return false;
                }
            }
        }

        // Retorna true se exatamente uma linha cheia foi encontrada
        return linhasCheias == 1;
    }
    public boolean isMatrizColuna() {
        int colunasCheias = 0;

        for (int j = 0; j < coluna; j++) {
            boolean isColunaCheia = true;

            for (int i = 0; i < linha; i++) {
                if (matriz[i][j] == 0) {
                    isColunaCheia = false;
                    break;
                }
            }

            if (isColunaCheia) {
                colunasCheias++;
                if (colunasCheias > 1) {
                    return false;
                }
            }
        }

        return colunasCheias == 1;
    }

    public boolean isMatrizTriangularInferior() {
            for (int i = 0; i < linha; i++) {
                for (int j = i + 1; j < coluna; j++) { // Apenas verifica elementos acima da diagonal principal
                if (matriz[i][j] != 0) {
                    return false; // Se encontrar um elemento diferente de zero, não é triangular inferior
                }
            }
        }
        return true; // Retorna true se todos os elementos acima da diagonal principal forem zero
    }
    public boolean isMatrizTriangularSuperior() {
        for (int i = 1; i < linha; i++) {
            for (int j = 0; j < i; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isMatrizSimetrica() {
        // Verifica se a matriz é quadrada
        if (linha != coluna) {
            return false; // Matrizes não quadradas não podem ser simétricas
        }

        // Verifica a condição de simetria
        for (int i = 0; i < linha; i++) {
            for (int j = i + 1; j < coluna; j++) { // Apenas verifica os elementos acima da diagonal principal
                if (matriz[i][j] != matriz[j][i]) {
                    return false; // Se A[i][j] != A[j][i], a matriz não é simétrica
                }
            }
        }

        return true; // Retorna true se todos os elementos forem simétricos
    }

    public MatrizEstática somarMatrizesEsparsas(MatrizEstática matrizA, MatrizEstática matrizB) {
        // Verifica se as dimensões das matrizes são compatíveis
        if (matrizA.coluna != matrizB.coluna || matrizA.linha != matrizB.linha) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }

        int linhas = matrizA.linha;
        int colunas = matrizA.coluna;

        // Cria a matriz resultado
        MatrizEstática matrizResultado = new MatrizEstática(linhas,colunas);

        // Percorre as matrizes e realiza a soma
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                int valor = matrizA.recuperarElemento(i,j) + matrizB.recuperarElemento(i,j);
                matrizResultado.inserir(i,j, valor);
            }
        }

        return matrizResultado;
    }

    public MatrizEstática multiplicarMatrizesEsparsas(MatrizEstática matrizA, MatrizEstática matrizB) {
        int linhasA = matrizA.linha;
        int colunasA = matrizA.coluna;
        int linhasB = matrizB.linha;
        int colunasB = matrizB.coluna;

        // Verifica se as dimensões são compatíveis para multiplicação
        if (colunasA != linhasB) {
            throw new IllegalArgumentException("As matrizes não são compatíveis para multiplicação.");
        }

        // Cria a matriz resultado com dimensões apropriadas
        MatrizEstática matrizResultado = new MatrizEstática(linhasA,colunasB);

        // Realiza a multiplicação apenas para elementos não nulos
        for (int i = 0; i < linhasA; i++) {
            for (int k = 0; k < colunasA; k++) { // Percorre os elementos da linha i da matriz A
                if (matrizA.recuperarElemento(i,k) != 0) { // Ignora elementos nulos
                    for (int j = 0; j < colunasB; j++) { // Percorre os elementos da coluna j da matriz B
                        if (matrizB.recuperarElemento(k,j) != 0) { // Ignora elementos nulos
                            matrizResultado.getMatriz()[i][j] += matrizA.recuperarElemento(i,k) * matrizB.recuperarElemento(k,j);
                        }
                    }
                }
            }
        }

        return matrizResultado;
    }

    public MatrizEstática calcularTransposta(MatrizEstática matriz) {
        int linhas = matriz.linha;
        int colunas = matriz.coluna;

        // Cria a matriz transposta com as dimensões invertidas
        MatrizEstática transposta = new MatrizEstática(colunas,linhas);

        // Percorre a matriz original e preenche a transposta
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                transposta.inserir(j,i,matriz.recuperarElemento(i,j));
            }
        }

        return transposta;
    }
}

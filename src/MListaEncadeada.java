public class MListaEncadeada {

    private Elo[] m; //Array contendo P listas encadeadas (linhas)
    private int linhas;
    private int colunas;

    protected class Elo
    {
        protected int colunaindex;
        protected int dado;
        protected Elo prox;

        public Elo()
        {
            prox = null;
        }

        public Elo(int coluna, int elem)
        {
            colunaindex = coluna;
            dado = elem;
            prox = null;
        }

    }

    public MListaEncadeada(int l, int c){
        linhas = l;
        colunas = c;

        m = new Elo[l];
        for (int i = 0; i < l; i++){
            m[i] = null;
        }
    }

    public void insere(int l, int c, int val){
        if (l >= linhas || l < 0 || c >= colunas || c < 0){
            System.out.println("Index fora do alcance permitido na matriz.");
            return;
        }

        if (val == 0) { // Remove o elemento se o valor for zero
            remove(l, c);
            return;
        }

        //m[l] é o prim
        Elo p, q;
        Elo ant = null;

        q = new Elo(c, val); // cria o elo novo que é uma tupla guardando a coluna (ordenado na lista) e o dado

        for (p = m[l]; ((p != null) && (p.colunaindex < c)); p = p.prox)
            ant = p;

        // Se for inserir um elemento em uma coluna e linha já preenchida, apenas atualiza esse valor
        if (p != null && p.colunaindex == c) {
            p.dado = val;
            return;
        }


        if (ant == null)
            m[l] = q;
        else
            ant.prox = q;

        q.prox = p;

    }
    public boolean remove(int l, int c){
        if (l >= linhas || l < 0 || c >= colunas || c < 0){
            System.out.println("Index fora do alcance permitido na matriz.");
            return false;
        }

        Elo p;
        Elo ant = null; /* referencia para anterior */

        for(p = m[l]; ((p != null) && (p.colunaindex < c)); p = p.prox) // encontrar a coluna a ser removida
            ant = p;

        /* Se p for null ou p.coluna != c, entao nao encontrou a coluna. */
        if ((p == null) || (p.colunaindex != c))
            return false;

        if (p == m[l])
            m[l] = m[l].prox; /* Remove elemento do inicio. */
        else
            ant.prox = p.prox;  /* Remove elemento do meio. */

        return true;
    }

    public int getElemento(int l, int c){
        if (l >= linhas || l < 0 || c >= colunas || c < 0){
            System.out.println("Index fora do alcance permitido na matriz.");
            return Integer.MIN_VALUE;
        }

        Elo p;

        for(p = m[l]; p != null; p = p.prox)
        {
            if(p.colunaindex == c)
                return p.dado;
        }

        return Integer.MIN_VALUE;
    }

    public void imprime() {
        for (int i = 0; i < linhas; i++) {
            Elo p;
            for (p = m[i]; p != null; p = p.prox) {
                System.out.print("(" + i + "," + p.colunaindex + "," + p.dado + ") ");
            }
            System.out.println();
        }
    }

    // MÉTODO ALTERNATIVO PARA IMPRIMIR A MATRIZ DESENHADA E REPRESENTANDO OS ZEROS
    public void imprime(boolean mostrarZeros) {
        for (int i = 0; i < linhas; i++) {
            Elo p = m[i];
            int colunaAtual = 0;

            while (colunaAtual < colunas) {
                if (p != null && p.colunaindex == colunaAtual) {
                    // imprimir elemento caso haja
                    System.out.print(p.dado + " ");
                    p = p.prox;
                } else {
                    // imprime zero se a posição for vazia
                    System.out.print("0 ");
                }
                colunaAtual++;
            }
            System.out.println(); // mudança de linha da matriz
        }
    }


    public boolean vazia(){
        for (int i = 0; i < linhas; i++){
            if (m[i] != null){
                return false;
            }
        }
        return true;
    }

    public void representarVazia(){
        for (int i = 0; i < linhas; i++){
            m[i] = null; // remover referencia para as colunas tornando a matriz vazia
        }
    }

    public boolean matrizLinha(){
        boolean auxachoulinha = false;

        for (int i = 0; i < linhas; i++){
            if (m[i] != null) {
                if (auxachoulinha){
                    return false; //JA FOI ENCONTRADA OUTRA LINHA COM CONTEUDO ANTES
                } else {
                    auxachoulinha = true;

                    // Verificar se a linha achada está completa
                    Elo p;
                    int contadorLinhaCompleta = 0;

                    for (p = m[i]; p != null; p = p.prox) {
                        contadorLinhaCompleta++;
                    }
                    if (contadorLinhaCompleta != this.colunas){
                        // essa linha nao possui todas as colunas preenchidas
                        return false;
                    }
                }
            }
        }
        return auxachoulinha;
    }

    public boolean matrizColuna(){
        int auxIndexColuna = Integer.MIN_VALUE;

        for (int i = 0; i < linhas; i++){
            if (m[i] == null) {
                return false; // toda linha deve ter ao menos um elemento para preencher uma coluna
            }
            if (m[i].prox != null){
                return false; // deve haver apensa uma coluna
            }
            if (auxIndexColuna == Integer.MIN_VALUE){
                auxIndexColuna = m[i].colunaindex; // Pegar a primeira para comparar com o resto, onde todas devem ser iguais
            } else if (m[i].colunaindex != auxIndexColuna) {
                return false;
            }
        }
        return true;
    }
    public boolean matrizDiagonal(){
        for (int i = 0; i < linhas; i++) {
            Elo p;
            for (p = m[i]; p != null; p = p.prox) {
                if (p.colunaindex != i) { // Verifica se há elemento fora da diagonal principal
                    return false;
                }
            }
        }
        return true; //caso nao tenha nada fora da diagonal principal, retorna true
    }

    public boolean isTriangularInferior(){

        for(int i = 0; i < linhas; i++){

            Elo atual = m[i];

            while(atual != null){

                int j = atual.colunaindex;
                if(j > i && atual.dado != 0){
                    return false;
                }

                atual = atual.prox;

            }

        }

        return true;

    }

    public boolean isTriangularSuperior(){

        for(int i = 0; i < linhas; i++){

            Elo atual = m[i];

            while(atual != null){

                int j = atual.colunaindex;
                if(j < i && atual.dado != 0){
                    return false;
                }

                atual = atual.prox;

            }

        }

        return true;

    }

    public boolean isSimetrica(){

        for(int i = 0; i < linhas; i++){

            Elo atual = m[i];

            while (atual != null){
                int j = atual.colunaindex;
                int valor = atual.dado;

                if(!existeValor(j, i, valor)){
                    return false;
                }

                atual = atual.prox;

            }

        }

        return true;

    }

    private boolean existeValor(int linha, int coluna, int valor){

        Elo atual = m[linha];
        while(atual != null){
            if(atual.colunaindex == coluna){
                return atual.dado == valor;
            }
            atual = atual.prox;
        }
        return false;
    }
    public MListaEncadeada somarMatrizesEsparsas(MListaEncadeada matrizA, MListaEncadeada matrizB) {
        if (matrizA.linhas != matrizB.linhas || matrizA.colunas != matrizB.colunas) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }

        MListaEncadeada matrizResultado = new MListaEncadeada(matrizA.linhas, matrizA.colunas);
        for (int i = 0; i < matrizA.linhas; i++) {
            Elo atualA = matrizA.m[i];
            while (atualA != null) {
                matrizResultado.SomarElemento(i, atualA.colunaindex, atualA.dado);
                atualA = atualA.prox;
            }
        }
        for (int i = 0; i < matrizB.linhas; i++) {
            Elo atualB = matrizB.m[i];
            while (atualB != null) {
                matrizResultado.SomarElemento(i, atualB.colunaindex, atualB.dado);
                atualB = atualB.prox;
            }
        }

        return matrizResultado;
    }
    public void SomarElemento(int linha, int coluna, int valor) {
        Elo atual = m[linha];
        Elo anterior = null;

        while (atual != null && atual.colunaindex < coluna) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null && atual.colunaindex == coluna) {
            atual.dado += valor;
        } else {
            Elo novo = new Elo(coluna, valor);
            if (anterior == null) {
                novo.prox = m[linha];
                m[linha] = novo;
            } else {

                novo.prox = atual;
                anterior.prox = novo;
            }
        }
    }
    public void MultiplicarElemento(int linha, int coluna, int valor) {
        if (valor == 0) return;

        Elo atual = m[linha];
        Elo anterior = null;
        while (atual != null && atual.colunaindex < coluna) {
            anterior = atual;
            atual = atual.prox;
        }

        if (atual != null && atual.colunaindex == coluna) {

            atual.dado += valor;
        } else {

            Elo novo = new Elo(coluna, valor);
            if (anterior == null) {
                m[linha] = novo;
            } else {

                anterior.prox = novo;
            }
            novo.prox = atual;
        }
    }

    public MListaEncadeada multiplicarMatrizesEsparsas(MListaEncadeada matrizA, MListaEncadeada matrizB) {
        // Verificar se a multiplicação é válida
        if (matrizA.colunas != matrizB.linhas) {
            throw new IllegalArgumentException("O número de colunas da matriz A deve ser igual ao número de linhas da matriz B.");
        }

        MListaEncadeada matrizResultado = new MListaEncadeada(matrizA.linhas, matrizB.colunas);

        for (int i = 0; i < matrizA.linhas; i++) {
            Elo linhaA = matrizA.m[i];

            while (linhaA != null) {
                int colunaA = linhaA.colunaindex;
                int valorA = linhaA.dado;

                Elo colunaB = matrizB.m[colunaA];
                while (colunaB != null) {
                    int colunaBIndex = colunaB.colunaindex;
                    int valorB = colunaB.dado;


                    int novoValor =(valorA * valorB);

                    matrizResultado.SomarElemento(i, colunaBIndex, novoValor);

                    colunaB = colunaB.prox;
                }

                linhaA = linhaA.prox;
            }
        }

        return matrizResultado;
    }

    public MListaEncadeada calcularTransposta(MListaEncadeada matriz) {
        MListaEncadeada matrizTransposta = new MListaEncadeada(matriz.colunas, matriz.linhas);

        for (int i = 0; i < matriz.linhas; i++) {
            Elo atual = matriz.m[i];

            while (atual != null) {

                matrizTransposta.SomarElemento(atual.colunaindex, i, atual.dado);

                atual = atual.prox;
            }
        }

        return matrizTransposta;
    }
}


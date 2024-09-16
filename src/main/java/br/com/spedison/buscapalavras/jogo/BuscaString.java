package br.com.spedison.buscapalavras.jogo;

import br.com.spedison.buscapalavras.jogo.vo.ResultadoDeUmJogo;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;



public class BuscaString {

    char[][] textoJogo;

    public BuscaString(char[][] textoJogo) {
        this.textoJogo = textoJogo;
    }

    public int getLinhas() {
        return textoJogo.length;
    }

    public int getColunas() {
        return textoJogo[0].length;
    }

    public StringBuilder getText(int inicioLinha, int inicioColuna,
                                 int tamanho, EstrategiaDirecao direcao) {
        StringBuilder sb = new StringBuilder();

        int novaLinha = inicioLinha;
        int novaColuna = inicioColuna;

        for (int i = 0; i < tamanho; i++) {

            if (novaColuna >= 0 &&
                    novaLinha < getLinhas() &&
                    novaColuna < getColunas()) {
                sb.append(textoJogo[novaLinha][novaColuna]);
            }

            switch (direcao) {
                case VERTICAL -> novaLinha++;
                case HORIZONTAL -> novaColuna++;
                case INCLINADA_DECRESCENTE -> {
                    novaLinha++;
                    novaColuna++;
                }

                case INCLINADA_CRESCENTE -> {
                    novaLinha++;
                    novaColuna--;
                }
            }
        }
        return sb;
    }

    public boolean buscaPalavra(String palavra, EstrategiaSentido[] sentidos,
                                EstrategiaDirecao[] direcoes, ResultadoDeUmJogo resultadoDeUmJogo) {

        resultadoDeUmJogo.palavra = palavra;
        resultadoDeUmJogo.encontrado = false;
        int inicioLinha = 0;
        int inicioColuna = 0;
        for (int linha = inicioLinha; linha < getLinhas(); linha++) {
            for (int coluna = inicioColuna; coluna < getColunas(); coluna++) {
                for (EstrategiaDirecao direcao : direcoes) {
                    StringBuilder compara = getText(linha, coluna, palavra.length(), direcao);
                    if (compara.length() != palavra.length()) continue;
                    for (EstrategiaSentido sentido : sentidos) {
                        switch (sentido){
                            case DIRETA -> {
                                if (compara.toString().equalsIgnoreCase(palavra)) {
                                    preencheResultado(palavra, resultadoDeUmJogo, direcao, sentido, linha, coluna);
                                    return true;
                                }
                            }
                            case INVERSA -> {
                                if (compara.reverse().toString().equalsIgnoreCase(palavra)) {
                                    preencheResultado(palavra, resultadoDeUmJogo, direcao, sentido, linha, coluna);
                                    return true;
                                }
                            }
                        } // switch dos sentidos.
                    } // sentidos
                } // direções
            } // colunas
        } // linhas
        return false; //str1.equalsIgnoreCase();
    }

    private static void preencheResultado(String palavra, ResultadoDeUmJogo resultadoDeUmJogo, EstrategiaDirecao direcao, EstrategiaSentido sentido, int linha, int coluna) {
        resultadoDeUmJogo.palavra = palavra;
        resultadoDeUmJogo.inicioLinha = linha;
        resultadoDeUmJogo.inicioColuna = coluna;
                                /*resultado.fimLinha = linha + palavra.length() - 1;
                                resultado.fimColuna = coluna + palavra.length() - 1;*/
        resultadoDeUmJogo.direcao = direcao;
        resultadoDeUmJogo.sentido = sentido;
        resultadoDeUmJogo.encontrado = true;
    }
}

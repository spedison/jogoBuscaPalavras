package br.com.spedison.buscapalavras.jogo.vo;

import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;

public class ResultadoDeUmJogo {
    public String palavra;
    public boolean encontrado;
    public int inicioLinha;
    public int inicioColuna;
    public int fimLinha;
    public int fimColuna;
    public EstrategiaDirecao direcao;
    public EstrategiaSentido sentido;

    @Override
    public String toString() {
        return "Resultado{" +
                "palavra='" + palavra + '\'' +
                ", inicioLinha=" + inicioLinha +
                ", inicioColuna=" + inicioColuna +
                ", fimLinha=" + fimLinha +
                ", fimColuna=" + fimColuna +
                ", direcao=" + direcao +
                ", sentido=" + sentido +
                '}';
    }
}

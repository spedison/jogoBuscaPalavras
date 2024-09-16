package br.com.spedison.buscapalavras.vo;

import br.com.spedison.buscapalavras.jogo.BuscaString;
import br.com.spedison.buscapalavras.jogo.vo.ResultadoDeUmJogo;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBuscaString {



    @Test
    public void testJogo() {
        BuscaString busca = new BuscaString(TestJogosPadrao.jogo);
        int linhas = busca.getLinhas();
        int colunas = busca.getColunas();

        Assertions.assertEquals(15, linhas);
        Assertions.assertEquals(15, colunas);
        ResultadoDeUmJogo resultadoDeUmJogo = new ResultadoDeUmJogo();
        boolean encontrado = busca.buscaPalavra("Romaria",
                new EstrategiaSentido[]{EstrategiaSentido.DIRETA, EstrategiaSentido.INVERSA},
                new EstrategiaDirecao[]{EstrategiaDirecao.VERTICAL, EstrategiaDirecao.HORIZONTAL, EstrategiaDirecao.INCLINADA_CRESCENTE, EstrategiaDirecao.INCLINADA_DECRESCENTE},
                resultadoDeUmJogo);
        Assertions.assertTrue(encontrado);
        System.out.println("Resultado ::" + resultadoDeUmJogo);

        encontrado = busca.buscaPalavra("Borid",
                new EstrategiaSentido[]{EstrategiaSentido.DIRETA, EstrategiaSentido.INVERSA},
                new EstrategiaDirecao[]{EstrategiaDirecao.VERTICAL, EstrategiaDirecao.HORIZONTAL, EstrategiaDirecao.INCLINADA_CRESCENTE, EstrategiaDirecao.INCLINADA_DECRESCENTE},
                resultadoDeUmJogo);
        Assertions.assertTrue(encontrado);
        System.out.println("Resultado ::" + resultadoDeUmJogo);

        encontrado = busca.buscaPalavra("docente",
                new EstrategiaSentido[]{EstrategiaSentido.DIRETA, EstrategiaSentido.INVERSA},
                new EstrategiaDirecao[]{EstrategiaDirecao.VERTICAL, EstrategiaDirecao.HORIZONTAL, EstrategiaDirecao.INCLINADA_CRESCENTE, EstrategiaDirecao.INCLINADA_DECRESCENTE},
                resultadoDeUmJogo);
        Assertions.assertTrue(encontrado);
        System.out.println("Resultado ::" + resultadoDeUmJogo);
    }

}

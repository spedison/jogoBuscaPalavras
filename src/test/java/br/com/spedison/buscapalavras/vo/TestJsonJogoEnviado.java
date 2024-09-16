package br.com.spedison.buscapalavras.vo;

import br.com.spedison.buscapalavras.jogo.vo.EntradaJogo;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

public class TestJsonJogoEnviado {
    @Test
    public void teste() throws JsonProcessingException {

        EntradaJogo ej = new EntradaJogo();

        ej.jogo = TestJogosPadrao.jogo;
        ej.palavrasParaProcurar = new String[]{"Abono", "Autor", "Fascinio", "Esfrega"};
        ej.direcoes = EstrategiaDirecao.values();
        ej.sentidos = EstrategiaSentido.values();

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter wr = mapper.writerWithDefaultPrettyPrinter();
        String out = wr.writeValueAsString(ej);
        System.out.println(out);
    }
}

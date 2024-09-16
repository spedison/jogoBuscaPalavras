package br.com.spedison.buscapalavras.vo;

import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestSerializeVOJogoInicial {
    @Test
    public void testSerializeVOJogoInicial() throws IOException {

        // Testar se a serialização e deserialização do VOJogoInicial funciona corretamente
        List<String> palavras = List.of("Teste","Alegria");
        VOJogoInicial ji = new VOJogoInicial(
                new char[][]{"APtesteTUV".toCharArray(),"halegriakl".toCharArray()},
                EstrategiaDirecao.values(),
                EstrategiaSentido.values(),
                palavras
        );
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter wr = mapper.writerWithDefaultPrettyPrinter();
        String out = wr.writeValueAsString(ji);
        System.out.println(out);
    }
}

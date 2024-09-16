package br.com.spedison.buscapalavras.controller.app;

import br.com.spedison.buscapalavras.controller.ControllerBase;
import br.com.spedison.buscapalavras.controller.enums.HttpResponseCode;
import br.com.spedison.buscapalavras.controller.enums.MetodoHttpEnum;
import br.com.spedison.buscapalavras.controller.enums.TipoRespostaHttpEnum;
import br.com.spedison.buscapalavras.jogo.BuscaString;
import br.com.spedison.buscapalavras.jogo.vo.EntradaJogo;
import br.com.spedison.buscapalavras.jogo.vo.ResultadoDeUmJogo;
import br.com.spedison.buscapalavras.utils.HttpUtils;
import br.com.spedison.buscapalavras.vo.VOUsuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.net.httpserver.HttpExchange;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResolveJogoController extends ControllerBase {

    public ResolveJogoController() {
        super("/jogo", "/" + ResolveJogoController.class.getSimpleName().replaceAll("Controller", "") + "[/]?.*", List.of(MetodoHttpEnum.POST), "Recebe dados do Jogo de Caça Palavras");
        System.out.println(Instant.now() + " - Passei pelo Contrutor " + this.getClass().getSimpleName());
    }

    @Override
    public void doPost(String url, Map<String, String> parameters, String body, HttpExchange retorno) {

        parameters.forEach((String key, String value) -> {
            System.out.println("Parâmetro: " + key + " - Valor: " + value);
        });

        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(Instant.now() + " - Corpo recebido = " + body);
            EntradaJogo entradaJogo = om.readValue(body, EntradaJogo.class);
            BuscaString bs = new BuscaString(entradaJogo.jogo);
            List<ResultadoDeUmJogo> resultados = new ArrayList<>();

            for (String palavras : entradaJogo.palavrasParaProcurar) {
                ResultadoDeUmJogo resultado = new ResultadoDeUmJogo();
                bs.buscaPalavra(palavras, entradaJogo.sentidos, entradaJogo.direcoes, resultado);
                resultados.add(resultado);
            }
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter wr = mapper.writerWithDefaultPrettyPrinter();
            String out = wr.writeValueAsString(resultados);

            HttpUtils.escreveRespostaHttp(out, TipoRespostaHttpEnum.JSON, HttpResponseCode.HTTP_OK, retorno);

            /*if (voUsuario.getUsuario().equals("spedison@gmail.com") && voUsuario.getSenha().equals("0123456789"))
                HttpUtils.escreveRespostaHttp(Instant.now() + " - OK (senha ok)", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_OK, retorno);
            else
                HttpUtils.escreveRespostaHttp(Instant.now() + " - Acesso indevido", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_NAO_AUTORIZADO, retorno);

             */
        } catch (JsonProcessingException e) {
            HttpUtils.escreveRespostaHttp(Instant.now() + " - Formato Usuario incorreto", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_REQUISICAO_RUIM, retorno);
            // throw new RuntimeException(e);
        }
    }
}

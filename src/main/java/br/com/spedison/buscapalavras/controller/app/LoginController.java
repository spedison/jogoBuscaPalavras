package br.com.spedison.buscapalavras.controller.app;

import br.com.spedison.buscapalavras.controller.ControllerBase;
import br.com.spedison.buscapalavras.controller.enums.HttpResponseCode;
import br.com.spedison.buscapalavras.controller.enums.MetodoHttpEnum;
import br.com.spedison.buscapalavras.controller.enums.TipoRespostaHttpEnum;
import br.com.spedison.buscapalavras.utils.HttpUtils;
import br.com.spedison.buscapalavras.vo.VOUsuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class LoginController extends ControllerBase {

    public LoginController() {
        super("/teste", "/" + LoginController.class.getSimpleName().replaceAll("Controller", "") + "[/]?", List.of(MetodoHttpEnum.POST), "Login de Usuário");
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
            VOUsuario voUsuario = om.readValue(body, VOUsuario.class);
            if (voUsuario.getUsuario().equals("spedison@gmail.com") && voUsuario.getSenha().equals("0123456789"))
                HttpUtils.escreveRespostaHttp(Instant.now() + " - OK (senha ok)", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_OK, retorno);
            else
                HttpUtils.escreveRespostaHttp(Instant.now() + " - Acesso indevido", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_NAO_AUTORIZADO, retorno);
        } catch (JsonProcessingException e) {
            HttpUtils.escreveRespostaHttp(Instant.now() + " - Formato Usuario incorreto", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_REQUISICAO_RUIM, retorno);
            // throw new RuntimeException(e);
        }
    }
}

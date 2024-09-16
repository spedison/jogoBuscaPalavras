package br.com.spedison.buscapalavras.controller.app;

import br.com.spedison.buscapalavras.controller.ControllerBase;
import br.com.spedison.buscapalavras.controller.enums.HttpResponseCode;
import br.com.spedison.buscapalavras.controller.enums.MetodoHttpEnum;
import br.com.spedison.buscapalavras.controller.enums.TipoRespostaHttpEnum;
import br.com.spedison.buscapalavras.utils.HttpUtils;
import com.sun.net.httpserver.HttpExchange;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class RecebeNomeController extends ControllerBase {

    public RecebeNomeController() {
        super("/teste", "/" + RecebeNomeController.class.getSimpleName().replaceAll("Controller","")+"[/]?", List.of(MetodoHttpEnum.GET),"Recebe nome");
        System.out.println(Instant.now() + " - Passei pelo Contrutor " + this.getClass().getSimpleName());
    }

    @Override
    public void doGet(String url, Map<String,String> parameters, HttpExchange retorno) {

        parameters.forEach((String key, String value) ->{
            System.out.println("Parâmetro: " + key + " - Valor: " + value);
        });

        HttpUtils.escreveRespostaHttp("OK", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_OK,retorno);
    }
}

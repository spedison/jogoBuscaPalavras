package br.com.spedison.buscapalavras.controller.app;

import br.com.spedison.buscapalavras.controller.ControllerBase;
import br.com.spedison.buscapalavras.controller.enums.HttpResponseCode;
import br.com.spedison.buscapalavras.controller.enums.TipoRespostaHttpEnum;
import br.com.spedison.buscapalavras.utils.HttpUtils;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListaOfControllers extends ArrayList<ControllerBase> {
    Set<String> contextos = new HashSet<>();
    public ListaOfControllers() {
        super(10);
    }

    public void addController(ControllerBase controller) {
        add(controller);
        contextos.add(controller.getContexto());
    }

    public Set<String> getContextos(){
        return contextos;
    }

    public void doHandle(HttpExchange exchange){
        for (ControllerBase controller : this) {
            try {
                if (controller.doHandle(exchange))
                    return;
            }catch (Exception e) {
                HttpUtils.escreveRespostaHttp(e.getMessage(), TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_ERRO_INTERNO_SERVIDOR,exchange);
            }
        }
        // Se não encontrar nenhum controller que responda a requisição, responde com 404 Not Found
        HttpUtils.escreveRespostaHttp("Recurso não localizado", TipoRespostaHttpEnum.TXT, HttpResponseCode.HTTP_NAO_ENCONTRADO,exchange);
    }

}

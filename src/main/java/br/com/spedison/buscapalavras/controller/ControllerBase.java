package br.com.spedison.buscapalavras.controller;

import br.com.spedison.buscapalavras.controller.enums.MetodoHttpEnum;
import br.com.spedison.buscapalavras.utils.HttpUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ControllerBase {
    private String contexto;
    private String regExp;
    private List<MetodoHttpEnum> metodos;
    //TODO: Depois criar um buffer de Objetos em memória para não criar sempre o objeto
    /*private List<HttpAction> listOfControllers = new ArrayList<>(10);
    private int lastGetItem;
    private int maxItens;*/
    private String descricao;

    public ControllerBase(String contexto, String regExp, List<MetodoHttpEnum> metodos, String descricao) {
        this.regExp = regExp;
        this.metodos = metodos;
        this.descricao = descricao;
        this.contexto = contexto;
        System.out.println(Instant.now()+" - Novo controller no contexto : " + contexto + " - Com expressao regular : " + this.regExp + " - com metodos : " + metodos.toString());
    }

    public String getDescricao() {
        return descricao;
    }

    public String getContexto() {
        return contexto;
    }

    public boolean isDoHandle(String url, MetodoHttpEnum metodo) {
        if (!url.startsWith(contexto)) return false;
        boolean math = url.matches(contexto+regExp);
        return metodos.contains(metodo) && math;
    }


    public boolean doHandle(HttpExchange exchange) throws IOException {
        MetodoHttpEnum metodo = MetodoHttpEnum.getFromString(exchange.getRequestMethod());
        String urlPath = exchange.getRequestURI().getPath();
        String urlParans = exchange.getRequestURI().getQuery();

        if (!isDoHandle(urlPath, metodo))
            return false;

        var params = HttpUtils.getParamsFromUrl(Objects.requireNonNullElse(urlParans,""), null);
        var corpo = HttpUtils.leCorpoHttp(exchange);

        if (metodo == MetodoHttpEnum.GET) {
            doGet(urlPath, params, exchange);
            return true;
        } else if (metodo == MetodoHttpEnum.POST) {
            doPost(urlPath, params, corpo, exchange);
            return true;
        } else if (metodo == MetodoHttpEnum.DELETE) {
            doDelete(urlPath, params, corpo, exchange);
            return true;
        } else {
            throw new IllegalArgumentException("Método não suportado: " + metodo);
        }
    }

    public String description() {
        return descricao;
    }

    public void doGet(String url, Map<String, String> parameters, HttpExchange retorno) {

    }

    public void doPost(String url, Map<String, String> parameters, String body, HttpExchange retorno) {

    }

    public void doDelete(String url, Map<String, String> parameters, String body, HttpExchange retorno) {

    }

}
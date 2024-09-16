package br.com.spedison.buscapalavras.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AllowAllFilter extends Filter {

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        chain.doFilter(exchange); // Passa a requisição para o próximo filtro ou manipulador
    }

    @Override
    public String description() {
        return "Filtro Passa Tudo";
    }
}

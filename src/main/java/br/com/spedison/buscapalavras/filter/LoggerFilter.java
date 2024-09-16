package br.com.spedison.buscapalavras.filter;

import br.com.spedison.buscapalavras.utils.HttpUtils;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.time.Instant;

public class LoggerFilter extends Filter {

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Instant.now() + " - " );
        buffer.append(" METHOD:"+exchange.getRequestMethod()+ " - URL:" + exchange.getRequestURI());
        String corpo = HttpUtils.leCorpoHttp(exchange);
        buffer.append(" -- Corpo : " + corpo);
        System.out.println(buffer.toString());
        chain.doFilter(exchange); // Passa a requisição para o próximo filtro ou manipulador
    }

    @Override
    public String description() {
        return "Filtro Passa com logging.";
    }
}

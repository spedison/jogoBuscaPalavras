package br.com.spedison;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

 class IpFilter extends Filter {
    private static final String ALLOWED_IP = "127.0.0.1"; // IP permitido

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getAddress().getHostAddress();

        // Verifica se o IP é permitido
        if (ALLOWED_IP.equals(remoteAddress)) {
            // Se permitido, continue com o próximo filtro ou manipulador
            chain.doFilter(exchange);
        } else {
            // Se não permitido, retorna um erro 403 (Forbidden)
            String response = "Access Denied";
            exchange.sendResponseHeaders(403, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    @Override
    public String description() {
        return "Filtro que permite apenas acessos do IP " + ALLOWED_IP;
    }
}


public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Adiciona o manipulador à rota "/test" com um filtro
        server.createContext("/test", new TestHandler())
                .getFilters().add(new IpFilter());

        server.setExecutor(null); // Cria um executor padrão
        server.start();
        System.out.println("Servidor iniciado na porta 8080...");
    }

    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuffer response = new StringBuffer();
            response.append("Hello, world!\n\n\n");
            response.append("\n");
            response.append(exchange.getRequestURI().toString());
            response.append("\n\n");
            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
            }
        }
    }
}

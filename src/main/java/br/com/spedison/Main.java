package br.com.spedison;

import br.com.spedison.buscapalavras.controller.ControllerBase;
import br.com.spedison.buscapalavras.controller.app.ListaOfControllers;
import br.com.spedison.buscapalavras.controller.app.LoginController;
import br.com.spedison.buscapalavras.controller.app.RecebeNomeController;
import br.com.spedison.buscapalavras.controller.app.ResolveJogoController;
import br.com.spedison.buscapalavras.filter.AllowAllFilter;
import br.com.spedison.buscapalavras.filter.LoggerFilter;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static int porta = 8080;
    static int nThreads = 5;
    static String ip = "127.0.0.1";

    List<ControllerBase> listaDeControllers = new ArrayList<>();


    public static void main(String[] args) {

        // Porta do servidor
        if (args.length >= 1) {
            porta = Integer.parseInt(args[0]);
        }
        // QUantidade de Threads no servidor
        if (args.length >= 2) {
            nThreads = Integer.parseInt(args[1]);
        }
        // IP usado para ouvir o servidor. (Deixei padrão como local)
        if (args.length >= 3) {
            ip = args[2];
        }

        ListaOfControllers controllers = new ListaOfControllers();
        controllers.addController(new RecebeNomeController());
        controllers.addController(new LoginController());
        controllers.addController(new ResolveJogoController());

        HttpHandler httpHandler = controllers::doHandle;

        // Deixa todas as interfaces de rede listando
        InetSocketAddress net = new InetSocketAddress(ip, porta);
        // Cria o servidor HTTP
        HttpServer server;

        try {
            Filter filtroPass = new AllowAllFilter();
            Filter filtroLog = new LoggerFilter();

            server = HttpServer.create(net, 0, "/", httpHandler, filtroLog, filtroPass);
            for (String contexto : controllers.getContextos()) {
                server.createContext(contexto, httpHandler);
                System.out.println(Instant.now() + " - Adicionado o contexto : " + contexto);
            }
        } catch (IOException ioe) {
            System.out.println("Provavelmente a porta já está sendo usada.");
            throw new RuntimeException(ioe.toString());
        }

        ExecutorService executor = Executors.newFixedThreadPool(nThreads); // Cria um pool com nThreads threads
        server.setExecutor(executor); // Para desativa o pool de threads use null
        System.out.println(Instant.now() + " - Servidor Nativo operando em : " + net);
        // Captura o CONTROL+C ou SIGTERM para fechar o servidor WEB.
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    System.out.println(Instant.now() + " - Terminando o Servidor WEB.");
                    server.stop(0);
                }
        ));
        // Inicia o servidor e para até dar um sinal de saida.
        server.start();
    }
}
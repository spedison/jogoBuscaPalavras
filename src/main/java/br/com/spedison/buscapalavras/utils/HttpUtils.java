package br.com.spedison.buscapalavras.utils;

import br.com.spedison.buscapalavras.controller.enums.HttpResponseCode;
import br.com.spedison.buscapalavras.controller.enums.TipoRespostaHttpEnum;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpUtils {

    public static final Charset defaultCharSet = Charset.forName("UTF-8");

    public static String getParansFromBody(String url, Charset charset) {
        if (charset == null) charset = defaultCharSet;
        //Map<String, String> params = new HashMap<>();
        String strRet = URLDecoder.decode(url, charset);
        return strRet;
    }

    public static Map<String, String> getParamsFromUrl(String url, Charset charset) {
        Map<String, String> params = new LinkedHashMap<>();
        if (charset == null) charset = defaultCharSet;
        //String[] parametros = URLDecoder.decode(url,charset).split("\\?");
        //if (parametros.length > 1) {
            String[] pares = URLDecoder.decode(url,charset).split("[&]");
            for (String par : pares) {
                String[] chaveValor = par.split("[=]");
                if (chaveValor.length == 2) {
                    params.put(chaveValor[0], chaveValor[1]);
                }
            }
        //}
        return params;
    }

    public static String leCorpoHttp(HttpExchange t) {
        StringBuilder corpoHttp = new StringBuilder();
        try (InputStream is = t.getRequestBody()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = br.readLine()) != null) {
                corpoHttp.append(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return corpoHttp.toString();
    }

    public static void escreveRespostaHttp(String resposta, TipoRespostaHttpEnum tipoResposta, HttpResponseCode responseCode, HttpExchange t) {
        // Envia a resposta

        Headers headers = t.getResponseHeaders();

        // Prepara o Cliente para receber o texto html
        headers.set("Content-Type", tipoResposta.getContentType());

        // Cuidado com o encoding enviado.
        byte[] saida = resposta.getBytes(StandardCharsets.UTF_8);

        // Volta o tamanho e o código de retorno.
        try {
            t.sendResponseHeaders(responseCode.getResposta(), saida.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Faz a escrita na saida e fecha a conexao.

        try (OutputStream os = t.getResponseBody()) {
            os.write(saida);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
